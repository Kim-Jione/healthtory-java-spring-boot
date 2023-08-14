package healthtory.site.healthtory.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import healthtory.site.healthtory.domain.post.Post;
import healthtory.site.healthtory.domain.post.PostDao;
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
    
    @Transactional
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
    
    @Transactional
    public PostRespDto write(WriteReqDto writeReqDto, SessionUserDto principal, MultipartFile file) throws Exception {
        String imgName = saveImage(file);
        writeReqDto.setPostThumbnail(imgName);
        Post post = writeReqDto.toPost();
        postDao.insert(post);
        List<String> tagList = writeReqDto.getTagList();
        PostRespDto writeResultDto = postDao.findByPost();
        for (String tagName : tagList) {
            tagDao.insert(tagName, writeResultDto.getPostId());
        }
        writeResultDto.setTagList(tagList);
        return writeResultDto;

    }

    @Transactional
    public PostRespDto update(UpdateReqDto updateReqDto, SessionUserDto principal, MultipartFile file)
            throws Exception {
        String imgName = saveImage(file);
        updateReqDto.setPostThumbnail(imgName);
        Post post = updateReqDto.toPost();
        postDao.update(post);
        List<String> tagList = updateReqDto.getTagList();
        tagDao.delete(updateReqDto.getPostId());
        for (String tagName : tagList) {
            tagDao.insert(tagName, updateReqDto.getPostId());
        }

        Post postPS = postDao.findById(updateReqDto.getPostId());
        PostRespDto updateResultDto = PostRespDto.fromPost(postPS);
        updateResultDto.setTagList(tagList);
        return updateResultDto;
    }

    @Transactional
    public PostRespDto deleteByPost(Integer postId, SessionUserDto principal) {
        Post post = postDao.findById(postId);
        PostRespDto deleteResult = PostRespDto.fromPost(post);
        List<String> tagPS = tagDao.findByTag(postId);
        deleteResult.setTagList(tagPS);
        tagDao.delete(postId);
        postDao.delete(postId);
        return deleteResult;
    }

    @Transactional
    public List<Post> getAllPost() {
        List<Post> postList = postDao.findAll();
        return postList;
    }
}