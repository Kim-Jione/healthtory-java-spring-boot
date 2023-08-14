package healthtory.site.healthtory.domain.subscribe;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Subscribe {

    private Integer subscribeId;
	private Integer subscriberId;
	private Integer creatorId;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	public Subscribe(Integer subscriberId, Integer creatorId) {
		this.subscriberId = subscriberId;
		this.creatorId = creatorId;
	}
}
