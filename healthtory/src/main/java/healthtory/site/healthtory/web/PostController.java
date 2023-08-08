package healthtory.site.healthtory.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import healthtory.site.healthtory.service.PostService;
import healthtory.site.healthtory.web.dto.CMRespDto;
import healthtory.site.healthtory.web.dto.request.post.WriteReqDto;
import healthtory.site.healthtory.web.dto.response.SessionUserDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostController {
    
    private final HttpSession session;
    private final PostService postService;
    
    @PostMapping("/post/write")
    public @ResponseBody CMRespDto<?> write(@RequestBody WriteReqDto writeReqDto) {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if (principal == null) {
             return new CMRespDto<>(-1, "로그인을 진행해주세요.", null);
        }
        postService.write(writeReqDto);
        return null;
    }
}
