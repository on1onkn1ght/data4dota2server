package com.onionknight.data4dota2.service;

import com.onionknight.data4dota2.entity.PageResult;
import com.onionknight.data4dota2.entity.RespEntity;
import com.onionknight.data4dota2.entity.Topic;

import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 14:20 2019/2/11
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public interface TopicService {
    RespEntity addTopic(Topic topic);
    PageResult<Topic> selectAllTopic(int pageNum,int pageSize);

    Topic selectById(Long id);
}
