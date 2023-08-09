package healthtory.site.healthtory.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import healthtory.site.healthtory.domain.post.Post;
import healthtory.site.healthtory.domain.post.PostDao;
import healthtory.site.healthtory.domain.tag.Tag;
import healthtory.site.healthtory.domain.tag.TagDao;
import healthtory.site.healthtory.web.dto.request.post.UpdateReqDto;
import healthtory.site.healthtory.web.dto.request.post.WriteReqDto;
import healthtory.site.healthtory.web.dto.response.SessionUserDto;
import healthtory.site.healthtory.web.dto.response.post.PostRespDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

    private final TagDao tagDao;
    private final PostDao postDao;
    private final JdbcTemplate jdbcTemplate;
    
    private String saveImage(MultipartFile file) throws Exception {
        Integer pos = file.getOriginalFilename().lastIndexOf(".");
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

        return imgName;
    }
    
    public PostRespDto write(WriteReqDto writeReqDto,SessionUserDto principal, MultipartFile file)throws Exception {
        String imgName = saveImage(file);
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
        Integer postId = keyHolder.getKey().intValue();
        System.out.println("postId: " + postId);

        // 태그 추가
        List<String> tagList = writeReqDto.getTagList();
        for (String tagName : tagList) {
            tagDao.insert(tagName, postId);
        }
        
        PostRespDto writeRespDto = postDao.findByPost(postId, principal.getUserId());
        writeRespDto.setTagList(tagList);
        return writeRespDto;

    }

    public PostRespDto update(UpdateReqDto updateReqDto, SessionUserDto principal, MultipartFile file) throws Exception{
        String imgName = saveImage(file);
        updateReqDto.setPostThumbnail(imgName);
        
        Post post = updateReqDto.toPost();
        postDao.update(post);

       
        // UPDATE 쿼리 실행
        String sql = "UPDATE post SET post_title = ?, post_content = ?, post_thumbnail = ?, user_id = ?, category_id = ?, status = ?, created_at = ?, updated_at = ? WHERE user_id = ? AND post_id = ?";
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
            ps.setInt(9, principal.getUserId()); 
            ps.setInt(10, updateReqDto.getPostId()); 
            return ps;
        }, keyHolder);

        // 태그 수정
        List<String> tagList = updateReqDto.getTagList();
        tagDao.delete(updateReqDto.getPostId());
        for (String tagName : tagList) {
            tagDao.insert(tagName, updateReqDto.getPostId());
        }
        
        PostRespDto updateRespDto = postDao.findByPost(updateReqDto.getPostId(), principal.getUserId());
        updateRespDto.setTagList(tagList);
        return updateRespDto;
    }

    public Post findByPost(Integer postId) {
        Post postPS = postDao.findById(postId);
        return postPS;
    }

    public PostRespDto deleteByPost(Integer postId, SessionUserDto principal) {
        PostRespDto postPS = postDao.findByPost(postId, principal.getUserId());

        List<String> tagPS = tagDao.findByTag(postId);
        postPS.setTagList(tagPS);
        tagDao.delete(postId);
        postDao.delete(postId);
        return postPS;
    }
}