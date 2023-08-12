package healthtory.site.healthtory.domain.post;

import java.util.List;

import healthtory.site.healthtory.web.dto.response.post.PostRespDto;

public interface  PostDao {
              
    public Post findById(Integer postId);

	public List<Post> findAll();

	public void insert(Post post);

	public void update(Post post);

	public void delete(Integer postId);

	public PostRespDto findByPost();
	
}
