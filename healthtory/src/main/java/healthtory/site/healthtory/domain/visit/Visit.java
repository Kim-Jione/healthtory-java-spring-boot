package healthtory.site.healthtory.domain.visit;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Visit {
    private Integer visitId;
	private Integer userId;
	private Integer postId;
	private Integer duration;
	private String referrer;
	private String ipAddress;
	private Timestamp createdAt;
	private Timestamp updatedAt;	
}
