package healthtory.site.healthtory.service;

import org.springframework.stereotype.Service;

import healthtory.site.healthtory.domain.love.Love;
import healthtory.site.healthtory.domain.love.LoveDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoveService {

    private final LoveDao loveDao;

    public Love love(Love love) {
        loveDao.insert(love);
        Love lovePS = loveDao.findByLove(love.getUserId(), love.getPostId());
        return lovePS;
    }

}
