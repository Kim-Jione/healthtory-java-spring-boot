package healthtory.site.healthtory.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import healthtory.site.healthtory.service.CommentService;
import healthtory.site.healthtory.web.dto.CMRespDto;
import healthtory.site.healthtory.web.dto.request.comment.UpdateReqDto;
import healthtory.site.healthtory.web.dto.request.comment.WriteReqDto;
import healthtory.site.healthtory.web.dto.response.SessionUserDto;
import healthtory.site.healthtory.web.dto.response.comment.CommentRespDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {
    
    private final HttpSession session;
    private final CommentService commentService;


    

    @PostMapping("/comment/write")
    public @ResponseBody CMRespDto<?> write(@RequestBody WriteReqDto writeReqDto) {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if (principal == null || writeReqDto.getUserId() == null) {
            return new CMRespDto<>(-1, "로그인을 진행해주세요.", null);
        }
        if (principal.getUserId() != writeReqDto.getUserId()) {
            return new CMRespDto<>(-1, "로그인 아이디가 다릅니다.", null);
        }
        CommentRespDto writeResultDto = commentService.write(writeReqDto, principal);
        return new CMRespDto<>(1, "댓글 등록에 성공했습니다.", writeResultDto);
    }

    @PutMapping("/comment/update")
    public @ResponseBody CMRespDto<?> update(@RequestBody UpdateReqDto updateReqDto) {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if (principal == null || updateReqDto.getUserId() == null) {
            return new CMRespDto<>(-1, "로그인을 진행해주세요.", null);
        }
        if (principal.getUserId() != updateReqDto.getUserId()) {
            return new CMRespDto<>(-1, "로그인 아이디가 다릅니다.", null);
        }
        CommentRespDto updateResultDto = commentService.update(updateReqDto);
        return new CMRespDto<>(1, "댓글 수정에 성공했습니다.", updateResultDto);
    }
    
    @DeleteMapping("/comment/delete/{commentId}")
    public @ResponseBody CMRespDto<?> delete(@PathVariable Integer commentId) {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if (principal == null || principal.getUserId() == null) {
            return new CMRespDto<>(-1, "로그인을 진행해주세요.", null);
        }
        if (principal.getUserId() != principal.getUserId()) {
            return new CMRespDto<>(-1, "로그인 아이디가 다릅니다.", null);
        }
        CommentRespDto commentPS = commentService.deleteByComment(commentId, principal);
        return new CMRespDto<>(1, "댓글 삭제에 성공했습니다.", commentPS);
    }
}
