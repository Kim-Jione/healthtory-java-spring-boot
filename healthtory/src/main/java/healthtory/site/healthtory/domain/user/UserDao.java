package healthtory.site.healthtory.domain.user;

import java.util.List;

public interface  UserDao {
                          
	public User findById(Integer userId);
	
    public User findByUser(String loginId);

	public List<User> findAll();

	public void insert(User user);

	public void update(User user);

	public void delete(Integer userId);
}
