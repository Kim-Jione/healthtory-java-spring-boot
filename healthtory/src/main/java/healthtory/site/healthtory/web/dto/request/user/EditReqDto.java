package healthtory.site.healthtory.web.dto.request.user;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EditReqDto {
    private String password;
    private String name;
    private String email;
    private String nickname;
    private Integer age;
    private String phoneNumber;
    private Date birthday;
    private String gender;
}