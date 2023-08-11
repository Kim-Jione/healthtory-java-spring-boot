package healthtory.site.healthtory.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import healthtory.site.healthtory.domain.comment.CommentDao;
import healthtory.site.healthtory.web.dto.request.comment.WriteReqDto;
import healthtory.site.healthtory.web.dto.response.SessionUserDto;
import healthtory.site.healthtory.web.dto.response.comment.CommentRespDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
    
    private final CommentDao commentDao;

    @Transactional
    public CommentRespDto write(WriteReqDto writeReqDto, SessionUserDto principal) {
        commentDao.insert(writeReqDto.toComment());
        CommentRespDto writeResultDto = commentDao.findByComment();
        return writeResultDto;
    }
    
}
