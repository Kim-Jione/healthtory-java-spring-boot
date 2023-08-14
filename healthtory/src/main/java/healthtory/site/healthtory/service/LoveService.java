package healthtory.site.healthtory.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import healthtory.site.healthtory.domain.love.Love;
import healthtory.site.healthtory.domain.love.LoveDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoveService {

    private final LoveDao loveDao;

    @Transactional
    public Love love(Integer userId, Integer postId) {
        Love love = new Love(userId, postId);
        loveDao.insert(love);
        Integer loveId = loveDao.findByLoveId(love.getUserId(), love.getPostId());
        Love lovePS = loveDao.findById(loveId);
        return lovePS;
    }

    @Transactional
    public Integer findById(Integer userId, Integer postId) {
        return loveDao.findByLoveId(userId, postId);
    }

    @Transactional
    public Love unLove(Integer loveId) {
        Love lovePS = loveDao.findById(loveId);
        loveDao.delete(lovePS.getLoveId());
        return lovePS;
    }

}
