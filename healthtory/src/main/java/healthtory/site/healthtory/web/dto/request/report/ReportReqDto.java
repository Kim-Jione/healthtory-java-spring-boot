package healthtory.site.healthtory.web.dto.request.report;

import java.sql.Timestamp;

import healthtory.site.healthtory.domain.report.Report;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReportReqDto {
    private String reportContent;
    private String reportCategory;
    private Integer userId;
	private Integer targetId;
    private String targetType;
	private Timestamp createdAt;
    private Timestamp updatedAt;

    public Report toReport() {
        return Report.builder().reportContent(reportContent).reportCategory(reportCategory).userId(userId).targetId(targetId).targetType(targetType).build();
    }
}
