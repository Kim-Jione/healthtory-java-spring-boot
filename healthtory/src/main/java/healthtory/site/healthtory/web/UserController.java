package healthtory.site.healthtory.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import healthtory.site.healthtory.domain.user.User;
import healthtory.site.healthtory.service.UserService;
import healthtory.site.healthtory.web.dto.CMRespDto;
import healthtory.site.healthtory.web.dto.request.user.JoinReqDto;
import healthtory.site.healthtory.web.dto.request.user.LoginReqDto;
import healthtory.site.healthtory.web.dto.request.user.PersonalUpdateReqDto;
import healthtory.site.healthtory.web.dto.response.SessionUserDto;
import healthtory.site.healthtory.web.dto.response.user.UserRespDto;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class UserController {
    private final HttpSession session;
    private final UserService userService;

    @PostMapping("/user/join")
    public @ResponseBody CMRespDto<?> join(@RequestBody JoinReqDto joinReqDto) {
        User joinByUser = userService.findByUser(joinReqDto.getLoginId());
        if (joinByUser != null) {
            return new CMRespDto<>(-1, "이미 존재하는 사용자입니다.", joinByUser);
        }
        UserRespDto joinResultDto = userService.join(joinReqDto);
        return new CMRespDto<>(1, "회원가입 성공했습니다.", joinResultDto);
    }

    @PostMapping("/user/login")
    public @ResponseBody CMRespDto<?> login(@RequestBody LoginReqDto loginReqDto) {
        User joinByUser = userService.findByUser(loginReqDto.getLoginId());
        if (joinByUser == null) {
            return new CMRespDto<>(-1, "존재하지 않는 사용자입니다.", joinByUser);
        }
        SessionUserDto principal = userService.login(loginReqDto);
        session.setAttribute("principal", principal);
        return new CMRespDto<>(1, "로그인 성공했습니다.", principal);
    }

    @GetMapping("/user/logout")
    public CMRespDto<?> logout() {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if (principal == null) {
            return new CMRespDto<>(-1, "로그인 상태가 아닙니다.", null);
        }
        session.removeAttribute("principal");
        return new CMRespDto<>(1, "로그아웃 되었습니다.", principal);
    }

    @PutMapping("/user/update")
    public @ResponseBody CMRespDto<?> update(
            @RequestBody PersonalUpdateReqDto personalUpdateReqDto) {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if (principal == null) {
            return new CMRespDto<>(-1, "로그인을 진행해주세요.", null);
        }
        if (principal.getUserId() != personalUpdateReqDto.getUserId()) {
            return new CMRespDto<>(-1, "로그인 아이디가 다릅니다.", null);
        }
        UserRespDto updateResult = userService.update(personalUpdateReqDto, principal);
        return new CMRespDto<>(1, "개인정보 수정에 성공했습니다.", updateResult);
    }
}   
