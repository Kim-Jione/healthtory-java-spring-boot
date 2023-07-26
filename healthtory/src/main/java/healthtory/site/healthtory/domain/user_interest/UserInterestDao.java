package healthtory.site.healthtory.domain.visit;

import java.util.List;

public interface  UserInterestDao {
                              
    public UserInterest findById(Integer userInterestId);

	public List<UserInterest> findAll();

	public void insert(UserInterest userInterest);

	public void update(UserInterest userInterest);

	public void delete(Integer userInterestId);
}
