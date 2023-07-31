package healthtory.site.healthtory.web.dto.request.user;

import java.util.Date;
import java.util.List;

import healthtory.site.healthtory.domain.user.User;
import healthtory.site.healthtory.domain.user_interest.UserInterest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class JoinReqDto {
    private String loginId;
    private String password;
    private String email;
    private String interest;
    private String nickname;
    private String interestTitle;
    private Integer age;
    private Date birthday;
    private String gender;
    private List<Integer> categoryIdList;
	private List<String> userInterestList;


    public User toUser() {
        return User.builder().loginId(this.loginId).password(this.password).email(this.email).insert(this.interest)
                .nickname(this.nickname).age(this.age).birthday(this.birthday)
                .gender(this.gender).build();
    }

    public JoinReqDto(User userPS) {
        this.loginId = userPS.getLoginId();
        this.password = userPS.getPassword();
        this.email = userPS.getEmail();
        this.interest = userPS.getInterest();
        this.nickname = userPS.getNickname();
        this.age = userPS.getAge();
        this.birthday = userPS.getBirthday();
        this.gender = userPS.getGender();
    }

}
