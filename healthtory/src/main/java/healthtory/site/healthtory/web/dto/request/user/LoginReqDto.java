package healthtory.site.healthtory.web.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginReqDto {
   	private String loginId;
	private String password;
}
