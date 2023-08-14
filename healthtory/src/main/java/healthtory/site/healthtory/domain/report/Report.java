package healthtory.site.healthtory.domain.report;

import java.sql.Timestamp;

import lombok.Builder;
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
    private Integer qnaId;
    private Integer commentId;
    private Integer subscribeId;
	private Timestamp createdAt;
    private Timestamp updatedAt;
    
    @Builder
    public Report(String reportContent, String reportCategory, Integer userId, Integer postId, Integer qnaId, Integer commentId, Integer subscribeId) {
        super();
        this.reportContent = reportContent;    
        this.reportCategory = reportCategory;
        this.userId = userId;
        this.postId = postId;
        this.qnaId = qnaId;
        this.commentId = commentId;
        this.subscribeId = commentId;
    }
}
