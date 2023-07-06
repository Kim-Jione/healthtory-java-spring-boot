package healthtory.site.healthtory.domain.tag;

import java.util.List;

public interface  TagDao {
                      
    public Tag findById(Integer tagId);

	public List<Tag> findAll();

	public void insert(Tag tag);

	public void update(Tag tag);

	public void delete(Integer tagId);
}
