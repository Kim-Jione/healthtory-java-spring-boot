package healthtory.site.healthtory.domain.admin;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Admin {
	private Integer adminId;
    private String name;
    private String loginId;
    private String password;
    private String email;
    private String permissions;
	private Timestamp createdAt;
    private Timestamp updatedAt;    
}