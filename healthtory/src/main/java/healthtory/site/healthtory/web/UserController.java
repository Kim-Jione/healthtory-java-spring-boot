package healthtory.site.healthtory.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import healthtory.site.healthtory.domain.user.User;
import healthtory.site.healthtory.domain.user.UserDao;
import healthtory.site.healthtory.service.UserService;
import healthtory.site.healthtory.web.dto.CMRespDto;
import healthtory.site.healthtory.web.dto.request.user.JoinReqDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {
    
    private final UserService userService;
    private final UserDao userDao;

    @PostMapping("/user/join")
    public @ResponseBody CMRespDto<?> join(@RequestBody JoinReqDto joinReqDto) {
        User joinByUser = userDao.findByUser(joinReqDto.getLoginId());
        if (joinByUser!=null) {
            return new CMRespDto<>(-1, "이미 존재하는 사용자입니다.", joinByUser);
        }
        JoinReqDto joinRespDto = userService.join(joinReqDto);
        return new CMRespDto<>(1, "회원가입 성공", joinRespDto);
    }
    
}
