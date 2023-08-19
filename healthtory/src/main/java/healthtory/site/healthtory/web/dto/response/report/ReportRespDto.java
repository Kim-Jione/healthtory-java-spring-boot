package healthtory.site.healthtory.web.dto.response.report;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class ReportRespDto {
    private Integer reportId;
    private String reportContent;
    private String reportCategory;
    private Integer userId;
    private Integer targetId;
    private String targetType;
	private Timestamp createdAt;
    private Timestamp updatedAt;
}
