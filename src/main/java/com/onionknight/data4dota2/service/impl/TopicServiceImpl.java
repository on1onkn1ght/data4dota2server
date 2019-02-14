package com.onionknight.data4dota2.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onionknight.data4dota2.entity.PageResult;
import com.onionknight.data4dota2.entity.RespCode;
import com.onionknight.data4dota2.entity.RespEntity;
import com.onionknight.data4dota2.entity.Topic;
import com.onionknight.data4dota2.mapper.TopicMapper;
import com.onionknight.data4dota2.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author :fdy
 * @Date :Created in 14:21 2019/2/11
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicMapper topicMapper;
    @Override
    public RespEntity addTopic(Topic topic) {
        topicMapper.addTopic(topic);
        return new RespEntity(RespCode.SUCCESS,"发表成功");
    }

    @Override
    public PageResult<Topic> selectAllTopic(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Topic> page = (Page<Topic>)topicMapper.selectallTopic();
        return new PageResult<>(page.getTotal(),page.getResult());
    }

    @Override
    public Topic selectById(Long id) {
        return topicMapper.selectById(id);
    }
}
