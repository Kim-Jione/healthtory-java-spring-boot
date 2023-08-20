package healthtory.site.healthtory.web.dto.response.comment;


import java.sql.Timestamp;

import healthtory.site.healthtory.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentRespDto {
    private Integer commentId;
    private Integer targetId;
    private String targetType;
    private Integer userId;
    private String commentContent;
    private Integer parentCommentId;
	private Timestamp createdAt;
    private Timestamp updatedAt;
    
    public static CommentRespDto fromComment(Comment comment) {
        CommentRespDto commentRespDto = new CommentRespDto();
        commentRespDto.setCommentId(comment.getCommentId());
        commentRespDto.setTargetId(comment.getTargetId());
        commentRespDto.setTargetType(comment.getTargetType());
        commentRespDto.setUserId(comment.getUserId());
        commentRespDto.setCommentContent(comment.getCommentContent());
        commentRespDto.setParentCommentId(comment.getParentCommentId());
        commentRespDto.setCreatedAt(comment.getCreatedAt());
        commentRespDto.setUpdatedAt(comment.getUpdatedAt());
        return commentRespDto;
    }
}


