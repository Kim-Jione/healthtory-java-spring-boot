package healthtory.site.healthtory.domain.comment;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Comment {
    private Integer commentId;
	private String commentContent;
    private Integer parentCommentId;
	private Integer userId;
	private Integer postId;
	private Integer qnaId;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	@Builder
    public Comment(Integer userId,Integer postId, String commentContent,Integer parentCommentId) {
        super();
        this.userId = userId;
        this.postId = postId;
        this.commentContent = commentContent;
        this.parentCommentId = parentCommentId;
    }
}
