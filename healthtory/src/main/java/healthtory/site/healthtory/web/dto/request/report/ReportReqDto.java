package healthtory.site.healthtory.web.dto.request.report;

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
    private Integer postId;
    private Integer userId;
    private Integer commentId;
    private Integer qnaId;
    private Integer subscribeId;

    public Report toReport() {
        return Report.builder().reportContent(reportContent).reportCategory(reportCategory).postId(postId).userId(userId).commentId(commentId).qnaId(qnaId).subscribeId(subscribeId).build();
    }
}
