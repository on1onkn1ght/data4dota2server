package com.onionknight.data4dota2.async.handler;

import com.onionknight.data4dota2.async.EventHandler;
import com.onionknight.data4dota2.async.EventModel;
import com.onionknight.data4dota2.async.EventType;
import com.onionknight.data4dota2.entity.Comment;
import com.onionknight.data4dota2.entity.Message;
import com.onionknight.data4dota2.mapper.CommentMapper;
import com.onionknight.data4dota2.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 15:39 2019/2/13
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Component
public class LikeHandler implements EventHandler {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    MessageMapper messageMapper;
    @Override
    public void doHandle(EventModel model) {
        Long entityId = model.getEntityId();
        Comment comment = commentMapper.selectById(entityId);
        Message message = new Message();
        message.setFromId(model.getActorId());
        message.setToId(comment.getUserId());
        message.setHasRead(false);
        message.setCreatedDate(new Date().getTime());
        message.setContent(comment.getUserId() + "给您点了个赞");
        messageMapper.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        List<EventType> list = new ArrayList<>();
        list.add(EventType.LIKE);
        return list;
    }
}
