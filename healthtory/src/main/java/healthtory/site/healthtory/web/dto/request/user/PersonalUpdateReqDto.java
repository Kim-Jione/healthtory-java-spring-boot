package healthtory.site.healthtory.web.dto.request.user;

import java.util.Date;
import java.util.List;

import healthtory.site.healthtory.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PersonalUpdateReqDto {
    private Integer userId;
    private String email;
    private String nickname;    
    private Integer age;
    private Date birthday;
    private String gender;
    private List<Integer> categoryIdList;
	private List<String> userInterestList;

    public User toUser() {
        return User.builder().userId(this.userId).email(this.email).nickname(this.nickname).age(this.age).birthday(this.birthday).gender(this.gender).build();
    }
}
