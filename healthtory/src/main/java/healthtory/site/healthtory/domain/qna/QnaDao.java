package healthtory.site.healthtory.domain.qna;

import java.util.List;

import healthtory.site.healthtory.web.dto.response.qna.QnaRespDto;

public interface  QnaDao {
          
    public Qna findById(Integer qnaId);

	public List<Qna> findAll();

	public void insert(Qna qna);

	public void update(Qna qna);

	public void delete(Integer qnaId);

    public QnaRespDto findByQna();
}
