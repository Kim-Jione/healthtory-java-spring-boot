package healthtory.site.healthtory.web.dto.response.comment;


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
}


