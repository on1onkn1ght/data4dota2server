package com.onionknight.data4dota2.async;

import com.onionknight.data4dota2.utils.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author :fdy
 * @Date :Created in 14:23 2019/2/13
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Service
public class EventProducer {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    public boolean fireEvent(EventModel model){
        try {
            redisTemplate.opsForList().leftPush(RedisKeyUtil.getEventQueueKey(),model);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
