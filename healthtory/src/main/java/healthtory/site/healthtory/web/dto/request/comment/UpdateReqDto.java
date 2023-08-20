package healthtory.site.healthtory.web.dto.request.comment;

import healthtory.site.healthtory.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UpdateReqDto {
	private Integer commentId;
	private Integer userId;
	private Integer targetId;
    private String targetType;
    private String commentContent;
    private Integer parentCommentId;

    public Comment toComment() {
            return Comment.builder().commentId(this.commentId).userId(this.userId).targetId(this.targetId).targetType(this.targetType).commentContent(this.commentContent).parentCommentId(this.parentCommentId).build();
        }
}
