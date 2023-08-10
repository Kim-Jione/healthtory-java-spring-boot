package healthtory.site.healthtory.web.dto.response.comment;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentRespDto {
	private Integer userId;
	private Integer postId;
    private String commentContent;
    private String parentCommentId;
}
