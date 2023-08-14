package healthtory.site.healthtory.domain.love;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface  LoveDao {
          
    public Love findById(Integer loveId);

	public List<Love> findAll();

	public void insert(Love love);

	public void update(Love love);

	public void delete(Integer loveId);

    public Love findByLove(@Param("userId") Integer userId, @Param("postId") Integer postId);  
}
