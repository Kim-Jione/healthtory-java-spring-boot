package healthtory.site.healthtory.domain.admin;

import java.util.List;

public interface  AdminDao {
                          
	public Admin findById(Integer loginId);
	
    public Admin findByUser(String loginId);

	public List<Admin> findAll();

	public void insert(Admin user);

	public void update(Admin user);

	public void delete(Integer userId);
}
