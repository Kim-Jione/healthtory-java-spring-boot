package healthtory.site.healthtory.domain.user_interest;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface  UserInterestDao {
                              
    public UserInterest findById(Integer userInterestId);

	public List<UserInterest> findAll();

	public void insert(@Param("userId") Integer userId, @Param("categoryId") Integer categoryId, @Param("interestTitle") String interestTitle);

	public void update(UserInterest userInterest);

	public void delete(Integer userInterestId);

}
