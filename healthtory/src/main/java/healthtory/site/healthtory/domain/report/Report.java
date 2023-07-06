package healthtory.site.healthtory.domain.report;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Report {
    private Integer reportId;
    private String reportContent;
    private String reportCategory;
	private Integer userId;
	private Integer postId;
    private Integer categoryId;
	private Timestamp createdAt;
	private Timestamp updatedAt;	
}
