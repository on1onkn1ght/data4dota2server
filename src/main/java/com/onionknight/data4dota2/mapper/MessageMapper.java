package com.onionknight.data4dota2.mapper;

import com.onionknight.data4dota2.entity.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author :fdy
 * @Date :Created in 10:31 2019/2/14
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Mapper
public interface MessageMapper {
    void addMessage(Message message);
}
