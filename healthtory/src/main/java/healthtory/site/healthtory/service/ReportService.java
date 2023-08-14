package healthtory.site.healthtory.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import healthtory.site.healthtory.domain.report.ReportDao;
import healthtory.site.healthtory.web.dto.request.report.ReportReqDto;
import healthtory.site.healthtory.web.dto.response.SessionUserDto;
import healthtory.site.healthtory.web.dto.response.report.ReportRespDto;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class ReportService {
    
    private final ReportDao reportDao;

    @Transactional
    public ReportRespDto report(ReportReqDto reportReqDto, SessionUserDto principal) {
        reportDao.insert(reportReqDto.toReport());
        ReportRespDto reportRespDto = reportDao.findByReport();
        return reportRespDto;
    }
    
}
