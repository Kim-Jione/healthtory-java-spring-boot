package healthtory.site.healthtory.domain.user;

import java.sql.Timestamp;

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
    private Integer birthday;
    private String role;
    private String gender;
    private String profileImg;
	private Timestamp createdAt;
	private Timestamp updatedAt;	
}