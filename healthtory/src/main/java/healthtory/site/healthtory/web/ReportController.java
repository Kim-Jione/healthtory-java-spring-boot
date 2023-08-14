package healthtory.site.healthtory.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import healthtory.site.healthtory.service.ReportService;
import healthtory.site.healthtory.web.dto.CMRespDto;
import healthtory.site.healthtory.web.dto.request.report.ReportReqDto;
import healthtory.site.healthtory.web.dto.response.SessionUserDto;
import healthtory.site.healthtory.web.dto.response.report.ReportRespDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReportController {

    private final HttpSession session;
    private final ReportService reportService;

    @PostMapping("/report/write")
    public @ResponseBody CMRespDto<?> write(@RequestBody ReportReqDto reportReqDto){
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if (principal == null || reportReqDto.getUserId() == null) {
            return new CMRespDto<>(-1, "로그인을 진행해주세요.", null);
        }
        if (principal.getUserId() != reportReqDto.getUserId()) {
            return new CMRespDto<>(-1, "로그인 아이디가 다릅니다.", null);
        }
        ReportRespDto reportResultDto = reportService.report(reportReqDto, principal);
        return new CMRespDto<>(1, "신고에 성공했습니다.", reportResultDto);
    }   
}
