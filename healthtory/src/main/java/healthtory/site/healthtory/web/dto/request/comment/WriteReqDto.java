package healthtory.site.healthtory.web.dto.request.comment;

import healthtory.site.healthtory.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WriteReqDto {
	private Integer userId;
	private Integer postId;
    private String commentContent;
    private Integer parentCommentId;

    public Comment toComment() {
            return Comment.builder().userId(this.userId).postId(this.postId).commentContent(this.commentContent).parentCommentId(this.parentCommentId).build();
        }
}
