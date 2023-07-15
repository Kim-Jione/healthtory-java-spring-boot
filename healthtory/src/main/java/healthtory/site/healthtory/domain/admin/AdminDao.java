package healthtory.site.healthtory.domain.admin;

import java.util.List;

public interface  AdminDao {
                          
	public Admin findById(Integer adminId);
	
	public List<Admin> findAll();

	public void insert(Admin admin);

	public void update(Admin admin);

	public void delete(Integer adminId);
}
