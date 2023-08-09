package healthtory.site.healthtory.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import healthtory.site.healthtory.service.PostService;
import healthtory.site.healthtory.web.dto.CMRespDto;
import healthtory.site.healthtory.web.dto.request.post.UpdateReqDto;
import healthtory.site.healthtory.web.dto.request.post.WriteReqDto;
import healthtory.site.healthtory.web.dto.response.SessionUserDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final HttpSession session;
    private final PostService postService;
    
    @PostMapping("/post/write")
    public @ResponseBody CMRespDto<?> write(@RequestPart("file") MultipartFile file,
            @RequestPart("writeReqDto") WriteReqDto writeReqDto) throws Exception {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if (principal == null) {
            return new CMRespDto<>(-1, "로그인을 진행해주세요.", null);
        }
        if (principal.getUserId() != writeReqDto.getUserId()) {
            return new CMRespDto<>(-1, "로그인 아이디가 다릅니다.", null);
        }
        WriteReqDto writeRespDto = postService.write(writeReqDto, principal, file);
        return new CMRespDto<>(1, "게시글 등록에 성공했습니다.", writeRespDto);
    }

    @PostMapping("/post/update")
    public @ResponseBody CMRespDto<?> update(@RequestPart("file") MultipartFile file,@RequestPart("updateReqDto") UpdateReqDto updateReqDto)throws Exception {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if (principal == null) {
            return new CMRespDto<>(-1, "로그인을 진행해주세요.", null);
        }
         if (principal.getUserId() != updateReqDto.getUserId()) {
             return new CMRespDto<>(-1, "로그인 아이디가 다릅니다.", null);
        }
        UpdateReqDto updateRespDto = postService.update(updateReqDto, principal, file);
        return new CMRespDto<>(1, "게시글 등록에 성공했습니다.", updateRespDto);
    }
}
