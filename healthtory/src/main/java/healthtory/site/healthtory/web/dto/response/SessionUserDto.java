package healthtory.site.healthtory.web.dto.response;

import healthtory.site.healthtory.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionUserDto {
    private Integer userId;
	private String loginId;

    public SessionUserDto(User userPS) {
        this.userId = userPS.getUserId();
        this.loginId = userPS.getLoginId();
    }
}
