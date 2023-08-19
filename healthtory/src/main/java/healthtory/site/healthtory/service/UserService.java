package healthtory.site.healthtory.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import healthtory.site.healthtory.domain.post.PostDao;
import healthtory.site.healthtory.domain.user.User;
import healthtory.site.healthtory.domain.user.UserDao;
import healthtory.site.healthtory.domain.user_interest.UserInterestDao;
import healthtory.site.healthtory.util.SHA256;
import healthtory.site.healthtory.web.dto.request.user.JoinReqDto;
import healthtory.site.healthtory.web.dto.request.user.LoginReqDto;
import healthtory.site.healthtory.web.dto.request.user.PersonalUpdateReqDto;
import healthtory.site.healthtory.web.dto.response.SessionUserDto;
import healthtory.site.healthtory.web.dto.response.user.UserRespDto;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class UserService {
    private final SHA256 sha256;
    private final UserDao userDao;
    private final PostDao postDao;
    private final UserInterestDao userInterestDao;
    
    @Transactional
    public UserRespDto join(JoinReqDto joinReqDto) {
        String enPassword = sha256.encrypt(joinReqDto.getPassword()); // 비밀번호 암호화
        joinReqDto.setPassword(enPassword); // 암호화된 비밀번호로 회원가입
        userDao.insert(joinReqDto.toUser());
        Integer userId = userDao.findByUser(joinReqDto.getLoginId()).getUserId();
        List<String> interestTitleList = joinReqDto.getUserInterestList();
        List<Integer> categoryIdList = joinReqDto.getCategoryIdList();

        for (int i = 0; i < interestTitleList.size(); i++) {
            userInterestDao.insertJoin(userId, categoryIdList.get(i), interestTitleList.get(i));
        }

        User user = userDao.findByUser(joinReqDto.getLoginId());
        UserRespDto userRespDto = UserRespDto.fromUser(user);
        userRespDto.setCategoryIdList(categoryIdList);
        userRespDto.setInterestTitleList(interestTitleList);
        return userRespDto;
    }

    @Transactional
    public User findByUser(String loginId) {
        return userDao.findByUser(loginId);
    }

    @Transactional
    public SessionUserDto login(LoginReqDto loginReqDto) {
		// String encPassword = sha256.encrypt(loginReqDto.getPassword());
		User userPS = userDao.findByUser(loginReqDto.getLoginId());
	
		if (userPS.getPassword().equals(loginReqDto.getPassword())) {
			return new SessionUserDto(userPS);
		}
		throw new RuntimeException("아이디 혹은 패스워드가 잘못 입력되었습니다.");
    }

    public UserRespDto update(PersonalUpdateReqDto personalUpdateReqDto, SessionUserDto principal) {
        User user = personalUpdateReqDto.toUser();
        userDao.update(user);
        List<String> interestTitleList = personalUpdateReqDto.getUserInterestList();
        List<Integer> categoryIdList = personalUpdateReqDto.getCategoryIdList();
        userInterestDao.delete(principal.getUserId());
        for (int i = 0; i < interestTitleList.size(); i++) {
            userInterestDao.insertJoin(principal.getUserId(), categoryIdList.get(i), interestTitleList.get(i));
        }
        User userPS = userDao.findById(principal.getUserId()); 
        UserRespDto updateResult = UserRespDto.fromUser(userPS);
        updateResult.setCategoryIdList(categoryIdList);
        updateResult.setInterestTitleList(interestTitleList);
        return updateResult;
    }

    public void deleteByUser(Integer userId, SessionUserDto principal) {
        userDao.delete(userId);
    }

}
