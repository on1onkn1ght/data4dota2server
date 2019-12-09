package com.onionknight.data4dota2.async;

import com.onionknight.data4dota2.utils.RedisKeyUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author :fdy
 * @Date :Created in 14:33 2019/2/13
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Service
public class EventConsumer implements InitializingBean {
    private Map<EventType, List<EventHandler>> config = new HashMap<>();

    @Autowired
    private ApplicationContext context;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, EventHandler> beans = context.getBeansOfType(EventHandler.class);
        for (Map.Entry<String,EventHandler> entry: beans.entrySet()){
            List<EventType> eventTypes = entry.getValue().getSupportEventTypes();

            for (EventType type : eventTypes){
                System.out.println(type);
                if (!config.containsKey(type))
                    config.put(type,new ArrayList<>());
                config.get(type).add(entry.getValue());
            }
        }

        new Thread( () -> {
            while (true){
                String key = RedisKeyUtil.getEventQueueKey();
                EventModel model = (EventModel) redisTemplate.opsForList().rightPop(key,10, TimeUnit.SECONDS);
                if (model==null||!config.containsKey(model.getType())) {
                    if (model != null)
                        System.out.println(model.getType());
                    System.out.println("错误");
                }
                else {
                    List<EventHandler> eventHandlers = config.get(model.getType());
                    for (EventHandler handler:eventHandlers)
                        handler.doHandle(model);
                }
            }
        }).start();
    }
}
