package healthtory.site.healthtory.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import healthtory.site.healthtory.service.QnaService;
import healthtory.site.healthtory.web.dto.CMRespDto;
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
    public @ResponseBody CMRespDto<?> write(@RequestPart("file") MultipartFile file, @RequestPart("writeReqDto") WriteReqDto writeReqDto) throws Exception {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if (principal == null || writeReqDto.getUserId() == null) {
            return new CMRespDto<>(-1, "로그인을 진행해주세요.", null);
        }
        if (principal.getUserId() != writeReqDto.getUserId()) {
            return new CMRespDto<>(-1, "로그인 아이디가 다릅니다.", null);
        }
        QnaRespDto writeResultDto = qnaService.write(writeReqDto, principal, file);
        return new CMRespDto<>(1, "Q&A 등록에 성공했습니다.", writeResultDto);
    }   
}
