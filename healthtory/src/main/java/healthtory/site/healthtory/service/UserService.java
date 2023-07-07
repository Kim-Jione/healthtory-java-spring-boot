package healthtory.site.healthtory.service;

import org.springframework.stereotype.Service;

import healthtory.site.healthtory.domain.user.User;
import healthtory.site.healthtory.domain.user.UserDao;
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
    
    public JoinReqDto join(JoinReqDto joinReqDto) {
        String enPassword = sha256.encrypt(joinReqDto.getPassword());
        joinReqDto.setPassword(enPassword);
        userDao.insert(joinReqDto.toUser());
        User userPS = userDao.findByUser(joinReqDto.getLoginId());
        JoinReqDto joinRespDto = new JoinReqDto(userPS);
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
