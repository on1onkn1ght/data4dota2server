package com.onionknight.data4dota2.service.impl;

import com.onionknight.data4dota2.async.EventModel;
import com.onionknight.data4dota2.async.EventProducer;
import com.onionknight.data4dota2.async.EventType;
import com.onionknight.data4dota2.service.LikeService;
import com.onionknight.data4dota2.utils.RedisKeyUtil;
import com.onionknight.data4dota2.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author :fdy
 * @Date :Created in 12:44 2019/2/12
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    EventProducer eventProducer;
    @Override
    public long liked(Long userId, int type, Long id) {
        EventModel like = new EventModel();
        like.setActorId(userId).setEntityId(id).setEntityType(1).setType(EventType.LIKE);
        eventProducer.fireEvent(like);
        String likeKey = RedisKeyUtil.getLikeKey(type, id);
        return  redisUtil.sadd(likeKey, userId + "");
    }

    @Override
    public long likednum(int type, Long id) {
        String likeKey = RedisKeyUtil.getLikeKey(type, id);
        return redisUtil.getSetSize(likeKey);
    }

    @Override
    public long unliked(Long userId, int type, Long id) {
        String likeKey = RedisKeyUtil.getLikeKey(type, id);
        return redisUtil.sremove(likeKey, userId + "");
    }
}
