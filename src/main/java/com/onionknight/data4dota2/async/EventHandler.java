package com.onionknight.data4dota2.async;

import org.hibernate.validator.constraints.Range;

import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 14:31 2019/2/13
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public interface EventHandler {
    void doHandle(EventModel model);
    List<EventType> getSupportEventTypes();
}
