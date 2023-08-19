package healthtory.site.healthtory.web.dto.response.post;

import java.sql.Timestamp;
import java.util.List;

import healthtory.site.healthtory.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class PostRespDto {
	private Integer postId;
	private Integer userId;
    private String postTitle;
    private String postContent;
    private String postThumbnail;
    private Integer categoryId;
    private String status;
    private List<String> tagList;
	private Timestamp createdAt;
    private Timestamp updatedAt;

    public static PostRespDto fromPost(Post post) {
        PostRespDto postRespDto = new PostRespDto();
        postRespDto.setPostId(post.getPostId());
        postRespDto.setPostTitle(post.getPostTitle());
        postRespDto.setPostContent(post.getPostContent());
        postRespDto.setPostThumbnail(post.getPostThumbnail());
        postRespDto.setUserId(post.getUserId());
        postRespDto.setCategoryId(post.getCategoryId());
        postRespDto.setStatus(post.getStatus());
        postRespDto.setCreatedAt(post.getCreatedAt());
        postRespDto.setUpdatedAt(post.getUpdatedAt());
        return postRespDto;
    }
}
