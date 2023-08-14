package healthtory.site.healthtory.service;

import org.springframework.stereotype.Service;

import healthtory.site.healthtory.domain.subscribe.Subscribe;
import healthtory.site.healthtory.domain.subscribe.SubscribeDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeDao subscribeDao;

    public Subscribe subscrive(Integer subscriberId, Integer creatorId) {
        Subscribe subscribe = new Subscribe(subscriberId, creatorId);
        subscribeDao.insert(subscribe);
        Integer subscribeId = subscribeDao.findByUserId(subscriberId, creatorId);
        Subscribe subscribePS = subscribeDao.findById(subscribeId);
        return subscribePS;
    }
    
    public Subscribe unsubscribe(Integer subscribeId) {
        Subscribe subscribePS = subscribeDao.findById(subscribeId);
        subscribeDao.delete(subscribeId);
        return subscribePS;
    }

    public Integer findById(Integer subscriberId, Integer creatorId) {
		return subscribeDao.findByUserId(subscriberId, creatorId);
    }
    
}
