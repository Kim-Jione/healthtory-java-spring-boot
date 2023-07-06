package healthtory.site.healthtory.domain.category;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Category {
    private Integer categoryId;
	private String categoryTitle;
	private Integer userId;
	private Timestamp createdAt;
	private Timestamp updatedAt;	
}
