package healthtory.site.healthtory.domain.love;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Love {
    private Integer loveId;
	private Integer userId;
	private Integer postId;
	private Timestamp createdAt;
	private Timestamp updatedAt;	
}
