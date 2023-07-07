package healthtory.site.healthtory.service;

import org.springframework.stereotype.Service;

import healthtory.site.healthtory.domain.user.User;
import healthtory.site.healthtory.domain.user.UserDao;
import healthtory.site.healthtory.util.SHA256;
import healthtory.site.healthtory.web.dto.request.user.JoinReqDto;
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

    
}
