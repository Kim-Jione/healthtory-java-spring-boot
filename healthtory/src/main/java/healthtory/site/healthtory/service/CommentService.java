package healthtory.site.healthtory.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import healthtory.site.healthtory.domain.comment.Comment;
import healthtory.site.healthtory.domain.comment.CommentDao;
import healthtory.site.healthtory.web.dto.request.comment.UpdateReqDto;
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
    
    @Transactional
    public CommentRespDto update(UpdateReqDto updateReqDto) {
        Comment comment = updateReqDto.toComment();
        commentDao.update(comment);
        Comment commentPS = commentDao.findById(updateReqDto.getCommentId());
        CommentRespDto updateResultDto = CommentRespDto.fromComment(commentPS);
        return updateResultDto;
    }
    
    @Transactional
    public CommentRespDto deleteByComment(Integer commentId, SessionUserDto principal) {
        Comment comment = commentDao.findById(commentId);
        CommentRespDto deleteResult = CommentRespDto.fromComment(comment);
        commentDao.delete(commentId);
        return deleteResult;
    }
    
}
