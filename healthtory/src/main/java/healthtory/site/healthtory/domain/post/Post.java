package healthtory.site.healthtory.domain.post;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Post {
    private Integer postId;
    private String postTitle;
    private String postContent;
    private String postThumbnail;
	private Integer userId;
    private Integer categoryId;
    private String status;
	private Timestamp createdAt;
    private Timestamp updatedAt;
    
    @Builder
    public Post(Integer userId,String postTitle, String postContent, String postThumbnail, Integer categoryId, String status) {
        super();
        this.userId = userId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postThumbnail = postThumbnail;
        this.categoryId = categoryId;
        this.status = status;
    }
}
