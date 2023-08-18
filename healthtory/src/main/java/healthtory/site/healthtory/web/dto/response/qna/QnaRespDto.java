package healthtory.site.healthtory.web.dto.response.qna;

import java.sql.Timestamp;

import healthtory.site.healthtory.domain.qna.Qna;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class QnaRespDto {
    private Integer qnaId;
    private String qnaTitle;
    private String qnaContent;
    private String qnaImg;
    private Integer userId;
    private Integer categoryId;
	private Timestamp createdAt;
    private Timestamp updatedAt;

    public static QnaRespDto fromQna(Qna qna) {
        QnaRespDto qnaRespDto = new QnaRespDto();
        qnaRespDto.setQnaId(qna.getQnaId());
        qnaRespDto.setQnaTitle(qna.getQnaTitle());
        qnaRespDto.setQnaContent(qna.getQnaContent());
        qnaRespDto.setQnaImg(qna.getQnaImg());
        qnaRespDto.setUserId(qna.getUserId());
        qnaRespDto.setCategoryId(qna.getCategoryId());
        return qnaRespDto;
    }
}
