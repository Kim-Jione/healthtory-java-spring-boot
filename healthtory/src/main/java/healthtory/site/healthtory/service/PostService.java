package healthtory.site.healthtory.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import healthtory.site.healthtory.domain.post.Post;
import healthtory.site.healthtory.domain.tag.TagDao;
import healthtory.site.healthtory.web.dto.request.post.WriteReqDto;
import healthtory.site.healthtory.web.dto.response.SessionUserDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

    private final TagDao tagDao;
    private final JdbcTemplate jdbcTemplate;

    public void write(WriteReqDto writeReqDto, SessionUserDto principal, MultipartFile file)throws Exception {
        int pos = file.getOriginalFilename().lastIndexOf(".");
		String extension = file.getOriginalFilename().substring(pos + 1);
		String filePath = "C:\\temp\\img\\";
        
		// 랜덤 키 생성
		String imgSaveName = UUID.randomUUID().toString();
        
		// 랜덤 키와 파일명을 합쳐 파일명 중복을 피함
		String imgName = imgSaveName + "." + extension;
        
		// 파일이 저장되는 폴더가 없으면 폴더를 생성
		File makeFileFolder = new File(filePath);
		if (!makeFileFolder.exists()) {
            if (!makeFileFolder.mkdirs()) {
                throw new Exception("File.mkdirs():Fail.");
			}
		}
        
		// 이미지 저장
		File dest = new File(filePath, imgName);
		try {
            Files.copy(file.getInputStream(), dest.toPath());
		} catch (IOException e) {
            e.printStackTrace();
			System.out.println("사진저장 실패");
		}
        
        writeReqDto.setPostThumbnail(imgName);
        
        Post post = writeReqDto.toPost();
        
        // INSERT 쿼리 실행
        String sql = "INSERT INTO post (post_title, post_content, post_thumbnail, user_id, category_id, status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, post.getPostTitle());
            ps.setString(2, post.getPostContent());
            ps.setString(3, post.getPostThumbnail());
            ps.setInt(4, post.getUserId());
            ps.setInt(5, post.getCategoryId());
            ps.setString(6, post.getStatus());
            ps.setTimestamp(7, post.getCreatedAt());
            ps.setTimestamp(8, post.getUpdatedAt());
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