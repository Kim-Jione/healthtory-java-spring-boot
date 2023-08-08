package healthtory.site.healthtory.web.dto.request.post;

import java.util.List;

import healthtory.site.healthtory.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WriteReqDto {
	private Integer userId;
    private String postTitle;
    private String postContent;
    private Integer categoryId;
    private String status;
    private List<String> tagList;

    public Post toPost() {
        return Post.builder().userId(this.userId).postTitle(this.postTitle).postContent(this.postContent).categoryId(this.categoryId).status(this.status).build();
    }
}
