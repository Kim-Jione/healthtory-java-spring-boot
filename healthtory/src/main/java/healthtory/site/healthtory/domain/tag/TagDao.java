package healthtory.site.healthtory.domain.tag;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface  TagDao {
                      
    public Tag findById(Integer tagId);

	public List<Tag> findAll();

    public void insert(@Param("tagName")String tagName, @Param("postId")Integer postId);

	public void update(Tag tag);

	public void delete(Integer tagId);

}
