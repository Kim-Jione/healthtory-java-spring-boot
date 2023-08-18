package healthtory.site.healthtory.web.dto.request.qna;

import java.sql.Timestamp;

import healthtory.site.healthtory.domain.qna.Qna;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WriteReqDto {
    private String qnaTitle;
    private String qnaContent;
    private String qnaImg;
    private Integer userId;
    private Integer categoryId;
	private Timestamp createdAt;
    private Timestamp updatedAt;

    public Qna toQna() {
        return Qna.builder().qnaTitle(this.qnaTitle).qnaContent(this.qnaContent).qnaImg(this.qnaImg).userId(this.userId).categoryId(this.categoryId).build();
    }
}
