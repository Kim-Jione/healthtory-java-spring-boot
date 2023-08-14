package healthtory.site.healthtory.web.dto.response.report;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class ReportRespDto {
    private String reportContent;
    private String reportCategory;
    private Integer postId;
    private Integer userId;
    private Integer commentId;
    private Integer qnaId;
    private Integer subscribeId;
}
