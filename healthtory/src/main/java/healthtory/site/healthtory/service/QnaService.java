package healthtory.site.healthtory.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import healthtory.site.healthtory.domain.comment.CommentDao;
import healthtory.site.healthtory.domain.qna.Qna;
import healthtory.site.healthtory.domain.qna.QnaDao;
import healthtory.site.healthtory.web.dto.request.qna.UpdateReqDto;
import healthtory.site.healthtory.web.dto.request.qna.WriteReqDto;
import healthtory.site.healthtory.web.dto.response.SessionUserDto;
import healthtory.site.healthtory.web.dto.response.qna.QnaRespDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QnaService {

    private final QnaDao qnaDao;
    private final CommentDao commentDao;

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
    public QnaRespDto write(WriteReqDto writeReqDto, SessionUserDto principal, MultipartFile file) throws Exception {
        String imgName = saveImage(file);
        writeReqDto.setQnaImg(imgName);
        Qna qna = writeReqDto.toQna();
        qnaDao.insert(qna);
        QnaRespDto writeResultDto = qnaDao.findByQna();
        return writeResultDto;
    }

    @Transactional
    public QnaRespDto update(UpdateReqDto updateReqDto, SessionUserDto principal, MultipartFile file) throws Exception {
        String imgName = saveImage(file);
        updateReqDto.setQnaImg(imgName);
        Qna qna = updateReqDto.toQna();
        qnaDao.update(qna);
        Qna qnaPS = qnaDao.findById(updateReqDto.getQnaId());
        QnaRespDto qnaRespDto = QnaRespDto.fromQna(qnaPS);
        return qnaRespDto;
    }
    
    @Transactional
    public QnaRespDto deleteByQna(Integer qnaId, SessionUserDto principal) {
        Qna qna = qnaDao.findById(qnaId);
        QnaRespDto delteResult = QnaRespDto.fromQna(qna);
        qnaDao.delete(qnaId);
        commentDao.deleteByComment(qnaId);
        return delteResult;
    }
    
}
