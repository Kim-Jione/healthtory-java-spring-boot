package healthtory.site.healthtory.domain.subscribe;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Subscribe {
    private Integer subscribeId;
	private Integer subscriber_id;
	private Integer creator_id;
	private Timestamp createdAt;
	private Timestamp updatedAt;	
}
