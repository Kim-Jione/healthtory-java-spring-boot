package healthtory.site.healthtory.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import healthtory.site.healthtory.service.QnaService;
import healthtory.site.healthtory.web.dto.CMRespDto;
import healthtory.site.healthtory.web.dto.request.qna.UpdateReqDto;
import healthtory.site.healthtory.web.dto.request.qna.WriteReqDto;
import healthtory.site.healthtory.web.dto.response.SessionUserDto;
import healthtory.site.healthtory.web.dto.response.qna.QnaRespDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class QnaController {
 
    private final QnaService qnaService;
    private final HttpSession session;

    @PostMapping("/qna/write")
    public @ResponseBody CMRespDto<?> write(@RequestPart("file") MultipartFile file,
            @RequestPart("writeReqDto") WriteReqDto writeReqDto) throws Exception {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if (principal == null || writeReqDto.getUserId() == null) {
            return new CMRespDto<>(-1, "로그인을 진행해주세요.", null);
        }
        if (principal.getUserId() != writeReqDto.getUserId()) {
            return new CMRespDto<>(-1, "로그인 아이디가 다릅니다.", null);
        }
        QnaRespDto writeResult = qnaService.write(writeReqDto, principal, file);
        return new CMRespDto<>(1, "Q&A 등록에 성공했습니다.", writeResult);
    }
    
    @PutMapping("/qna/update")
    public @ResponseBody CMRespDto<?> update(@RequestPart("file") MultipartFile file,
            @RequestPart("updateReqDto") UpdateReqDto updateReqDto) throws Exception {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if (principal == null || updateReqDto.getUserId() == null) {
            return new CMRespDto<>(-1, " 로그인을 진행해주세요.", null);
        }
        if (principal.getUserId() != updateReqDto.getUserId()) {
            return new CMRespDto<>(-1, "로그인 아이디가 다릅니다.", null);
        }
        QnaRespDto updateResult = qnaService.update(updateReqDto, principal, file);
        return new CMRespDto<>(1, "Q&A 수정에 성공했습니다.", updateResult);
    }

    @DeleteMapping("/qna/delete/{qnaId}")
    public @ResponseBody CMRespDto<?> delete(@PathVariable Integer qnaId) {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if (principal == null || principal.getUserId() == null) {
            return new CMRespDto<>(-1, "로그인을 진행해주세요.", null);
        }
        if (principal.getUserId() != principal.getUserId()) {
            return new CMRespDto<>(-1, "로그인 아이디가 다릅니다.", null);
        }
        QnaRespDto deleteResult = qnaService.deleteByQna(qnaId, principal);
        return new CMRespDto<>(1, "게시글 삭제에 성공했습니다.", deleteResult);
    }
}
