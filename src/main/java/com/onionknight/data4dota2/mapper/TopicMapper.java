package com.onionknight.data4dota2.mapper;

import com.onionknight.data4dota2.entity.Topic;
import com.onionknight.data4dota2.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 13:49 2019/2/10
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Mapper
public interface TopicMapper {
    Topic selectById(Long id) ;

    void addTopic(Topic topic);
    List<Topic> selectallTopic();
}
