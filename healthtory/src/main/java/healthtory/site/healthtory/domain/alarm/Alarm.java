package healthtory.site.healthtory.domain.alarm;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Alarm {
    private Integer alarmId;
	private String alarmContent;
    private String alarmType;
    private Boolean isRead;
	private Integer userId;
	private Integer postId;
	private Integer commentId;
	private Integer subscribeId;
	private Integer qnaId;
	private Timestamp createdAt;
	private Timestamp updatedAt;	
}
