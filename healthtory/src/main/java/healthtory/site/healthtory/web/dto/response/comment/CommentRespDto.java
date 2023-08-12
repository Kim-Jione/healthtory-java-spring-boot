package healthtory.site.healthtory.web.dto.response.comment;


import healthtory.site.healthtory.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentRespDto {
    private Integer commentId;
    private Integer postId;
    private Integer userId;
    private String commentContent;
    private Integer parentCommentId;

    public static CommentRespDto fromComment(Comment comment) {
        CommentRespDto commentRespDto = new CommentRespDto();
        commentRespDto.setCommentId(comment.getCommentId());
        commentRespDto.setPostId(comment.getPostId());
        commentRespDto.setUserId(comment.getUserId());
        commentRespDto.setCommentContent(comment.getCommentContent());
        commentRespDto.setParentCommentId(comment.getParentCommentId());
        return commentRespDto;
    }
}


