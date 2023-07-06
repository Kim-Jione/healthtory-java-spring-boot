package healthtory.site.healthtory.domain.post;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Post {
    private Integer postId;
    private String postTitle;
    private String postContent;
	private Integer userId;
    private Integer categoryId;
    private String status;
	private Timestamp createdAt;
	private Timestamp updatedAt;	
}
