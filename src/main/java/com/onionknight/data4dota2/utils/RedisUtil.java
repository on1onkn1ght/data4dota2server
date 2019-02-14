package com.onionknight.data4dota2.utils;

import com.onionknight.data4dota2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author :fdy
 * @Date :Created in 11:24 2019/2/11
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String,Object> template;

    public void add(String key, Object object){
        template.opsForValue().set(key,object);
    }
    public void del(String key){
        template.delete(key);
    }
    public User get(String key){
        return (User) template.opsForValue().get(key);
    }
    public Long sadd(String key,String id){
        template.opsForSet().add(key, id);
        return getSetSize(key);
    }
    public Long getSetSize(String key){
        return template.opsForSet().size(key);
    }

    public long sremove(String likeKey, String s) {
        template.opsForSet().remove(likeKey,s);
        return getSetSize(likeKey);
    }
}
