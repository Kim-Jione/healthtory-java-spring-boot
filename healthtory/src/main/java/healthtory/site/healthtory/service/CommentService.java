package healthtory.site.healthtory.service;

import org.springframework.stereotype.Service;

import healthtory.site.healthtory.domain.comment.CommentDao;
import healthtory.site.healthtory.web.dto.request.comment.WriteReqDto;
import healthtory.site.healthtory.web.dto.response.SessionUserDto;
import healthtory.site.healthtory.web.dto.response.comment.CommentRespDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
    
    private final CommentDao commentDao;

    public CommentRespDto write(WriteReqDto commentReqDto, SessionUserDto principal) {
        commentDao.insert(commentReqDto.toComment());
        return null;
    }
    
}
