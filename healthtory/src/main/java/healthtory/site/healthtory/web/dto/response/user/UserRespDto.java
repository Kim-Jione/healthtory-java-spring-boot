package healthtory.site.healthtory.web.dto.response.user;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import healthtory.site.healthtory.domain.user.User;
import healthtory.site.healthtory.web.dto.response.user.UserRespDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class UserRespDto {
    private Integer userId;
    private String loginId;
    private String password;
    private String email;
    private String nickname;
    private Integer age;
    private Date birthday;
    private String gender;
    private String profileImg;
    private List<String> interestTitleList;
    private List<Integer> categoryIdList;
	private Timestamp createdAt;
    private Timestamp updatedAt;

    public static UserRespDto fromUser(User user) {
        UserRespDto userRespDto = new UserRespDto();
        userRespDto.setUserId(user.getUserId());
        userRespDto.setLoginId(user.getLoginId());
        userRespDto.setPassword(user.getPassword());
        userRespDto.setEmail(user.getLoginId());
        userRespDto.setNickname(user.getNickname());
        userRespDto.setAge(user.getAge());
        userRespDto.setBirthday(user.getBirthday());
        userRespDto.setGender(user.getGender());
        userRespDto.setProfileImg(user.getProfileImg());
        userRespDto.setCreatedAt(user.getCreatedAt());
        userRespDto.setUpdatedAt(user.getUpdatedAt());
        return userRespDto;
    }
}
