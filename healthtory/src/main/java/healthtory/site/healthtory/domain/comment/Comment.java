package healthtory.site.healthtory.domain.comment;

import java.sql.Timestamp;

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
}
