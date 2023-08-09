package healthtory.site.healthtory.domain.post;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import healthtory.site.healthtory.web.dto.request.post.WriteReqDto;
import healthtory.site.healthtory.web.dto.response.post.WriteRespDto;

public interface  PostDao {
              
    public Post findById(Integer postId);

	public List<Post> findAll();

	public void insert(Post post);

	public void update(Post post);

	public void delete(Integer postId);

	public WriteReqDto findByPost(@Param("postId")Integer postId, @Param("userId")Integer userId);
}
