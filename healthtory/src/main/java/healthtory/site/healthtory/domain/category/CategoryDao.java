package healthtory.site.healthtory.domain.category;

import java.util.List;

public interface  CategoryDao {
   
    public Category findById(Integer categoryId);

	public List<Category> findAll();

	public void insert(Category category);

	public void update(Category category);

	public void delete(Integer categoryId); 
}
