package healthtory.site.healthtory.domain.comment;

import java.util.List;

import healthtory.site.healthtory.web.dto.response.comment.CommentRespDto;

public interface  CommentDao {
      
    public Comment findById(Integer commentId);

	public CommentRespDto findByComment();
	
	public List<Comment> findAll();

	public void insert(Comment comment);

	public void update(Comment comment);

	public void delete(Integer commentId);

    public void deleteByComment(Integer qnaId);  
}
