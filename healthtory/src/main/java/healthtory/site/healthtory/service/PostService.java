package healthtory.site.healthtory.service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import healthtory.site.healthtory.domain.post.Post;
import healthtory.site.healthtory.domain.tag.TagDao;
import healthtory.site.healthtory.web.dto.request.post.WriteReqDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

    private final TagDao tagDao;
    private final JdbcTemplate jdbcTemplate;

    public void write(WriteReqDto writeReqDto) {
        Post post = writeReqDto.toPost();

        // INSERT 쿼리 실행
        String sql = "INSERT INTO post (post_title, post_content, user_id, category_id, status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, post.getPostTitle());
            ps.setString(2, post.getPostContent());
            ps.setInt(3, post.getUserId());
            ps.setInt(4, post.getCategoryId());
            ps.setString(5, post.getStatus());
            ps.setTimestamp(6, post.getCreatedAt());
            ps.setTimestamp(7, post.getUpdatedAt());
            return ps;
        }, keyHolder);

        // 자동 생성된 키 값 가져오기
        int postId = keyHolder.getKey().intValue();
        System.out.println("postId: " + postId);

        // 태그 추가
        List<String> tagList = writeReqDto.getTagList();
        for (String tagName : tagList) {
            tagDao.insert(tagName, postId);
        }
    }
}