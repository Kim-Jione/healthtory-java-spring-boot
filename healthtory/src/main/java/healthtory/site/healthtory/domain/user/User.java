package healthtory.site.healthtory.domain.user;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class User {
	private Integer userId;
    private String loginId;
    private String password;
    private String name;
    private String email;
    private String nickname;
    private String age;
    private String phoneNumber;
    private Date birthday;
    private String role;
    private String gender;
    private String profileImg;
	private Timestamp createdAt;
    private Timestamp updatedAt;
    
    @Builder
    public User(String loginId, String password, String name, String email, String nickname, String age,
            String phoneNumber, Date birthday, String gender) {
                super();
                this.loginId = loginId;
                this.password = password;
                this.name = name;
                this.email = email;
                this.nickname = nickname;
                this.age = age;
                this.phoneNumber = phoneNumber;
                this.birthday = birthday;
                this.gender = gender;
            }
}