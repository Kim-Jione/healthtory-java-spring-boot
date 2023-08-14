package healthtory.site.healthtory.domain.qna;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Qna {
    private Integer qnaId;
    private String qnaTitle;
    private String qnaContent;
    private String qnaImg;
	private Integer userId;
    private Integer categoryId;
	private Timestamp createdAt;
    private Timestamp updatedAt;
    
    @Builder
    public Qna(String qnaTitle, String qnaContent, String qnaImg, Integer userId,Integer categoryId) {
        super();
        this.qnaTitle = qnaTitle;
        this.qnaContent = qnaContent;
        this.qnaImg = qnaImg;
        this.userId = userId;
        this.categoryId = categoryId;
    }
}
