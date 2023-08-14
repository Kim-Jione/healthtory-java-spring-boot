package healthtory.site.healthtory.domain.subscribe;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface  SubscribeDao {
                  
    public Subscribe findById(Integer subscribeId);

	public List<Subscribe> findAll();

	public void insert(Subscribe subscribe);

	public void update(Subscribe subscribe);

	public void delete(Integer subscribeId);

    public Integer findByUserId(@Param("subscriberId") Integer subscriberId,
			@Param("creatorId") Integer creatorId);
}
