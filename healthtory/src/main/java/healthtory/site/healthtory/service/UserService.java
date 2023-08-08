package healthtory.site.healthtory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import healthtory.site.healthtory.domain.user.User;
import healthtory.site.healthtory.domain.user.UserDao;
import healthtory.site.healthtory.domain.user_interest.UserInterestDao;
import healthtory.site.healthtory.util.SHA256;
import healthtory.site.healthtory.web.dto.request.user.JoinReqDto;
import healthtory.site.healthtory.web.dto.request.user.LoginReqDto;
import healthtory.site.healthtory.web.dto.response.SessionUserDto;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class UserService {
    private final SHA256 sha256;
    private final UserDao userDao;
    private final UserInterestDao userInterestDao;
    
    public JoinReqDto join(JoinReqDto joinReqDto) {
        String enPassword = sha256.encrypt(joinReqDto.getPassword()); // 비밀번호 암호화
        joinReqDto.setPassword(enPassword); // 암호화된 비밀번호로 회원가입
        userDao.insert(joinReqDto.toUser());
        Integer userId = userDao.findByUser(joinReqDto.getLoginId()).getUserId();
        List<String> userInterestList = joinReqDto.getUserInterestList();
        List<Integer> categoryIdList = joinReqDto.getCategoryIdList();


        for (int i = 0; i < userInterestList.size(); i++) {
            userInterestDao.insert(userId, categoryIdList.get(i), userInterestList.get(i));
        }

        User userPS = userDao.findByUser(joinReqDto.getLoginId());
        JoinReqDto joinRespDto = new JoinReqDto(userPS);
        joinRespDto.setCategoryIdList(categoryIdList);
        joinRespDto.setUserInterestList(userInterestList);
        return joinRespDto;
    }

    public User findByUser(String loginId) {
        return userDao.findByUser(loginId);
    }

    public SessionUserDto login(LoginReqDto loginReqDto) {
		// String encPassword = sha256.encrypt(loginReqDto.getPassword());
		User userPS = userDao.findByUser(loginReqDto.getLoginId());
	
		if (userPS.getPassword().equals(loginReqDto.getPassword())) {
			return new SessionUserDto(userPS);
		}
		throw new RuntimeException("아이디 혹은 패스워드가 잘못 입력되었습니다.");
    }

}
