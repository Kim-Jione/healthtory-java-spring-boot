package healthtory.site.healthtory.domain.qna;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Qna {
    private Integer qnaId;
    private String qnaTitle;
    private String qnaContent;
	private Integer userId;
    private Integer categoryId;
	private Timestamp createdAt;
	private Timestamp updatedAt;	
}
