package healthtory.site.healthtory.web.dto.request.user;

import java.sql.Timestamp;

import healthtory.site.healthtory.domain.user.User;
import healthtory.site.healthtory.web.dto.response.user.UserRespDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PasswordUpdateReqDto {
    private Integer userId;
    private String password;
	private Timestamp createdAt;
    private Timestamp updatedAt;


    public User toUser() {
        return User.builder().userId(this.userId).password(this.password).createdAt(this.createdAt).updatedAt(this.updatedAt).build();
    }


    public static UserRespDto fromUser(User userPS) {
        UserRespDto passwordUpdateReqDto = new UserRespDto();
        passwordUpdateReqDto.setUserId(userPS.getUserId());
        passwordUpdateReqDto.setPassword(userPS.getPassword());
        passwordUpdateReqDto.setCreatedAt(userPS.getCreatedAt());
        passwordUpdateReqDto.setUpdatedAt(userPS.getUpdatedAt());
        return passwordUpdateReqDto;
    }
}
