package healthtory.site.healthtory.web.dto.request.user;

import java.util.Date;

import healthtory.site.healthtory.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class JoinReqDto {
    private String loginId;
    private String password;
    private String name;
    private String email;
    private String nickname;
    private Integer age;
    private String phoneNumber;
    private Date birthday;
    private String gender;

    public User toUser() {
        return User.builder().loginId(this.loginId).password(this.password).name(this.name).email(this.email)
                .nickname(this.nickname).age(this.age).phoneNumber(this.phoneNumber).birthday(this.birthday)
                .gender(this.gender).build();
    }

    public JoinReqDto(User userPS) {
                this.loginId = userPS.getLoginId();
                this.password = userPS.getPassword();
                this.name = userPS.getName();
                this.email = userPS.getEmail();
                this.nickname = userPS.getNickname();
                this.age = userPS.getAge();
                this.phoneNumber = userPS.getPhoneNumber();
                this.birthday = userPS.getBirthday();
                this.gender = userPS.getGender();
    }
}
