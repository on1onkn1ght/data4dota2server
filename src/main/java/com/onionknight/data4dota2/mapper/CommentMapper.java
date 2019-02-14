package com.onionknight.data4dota2.mapper;

import com.onionknight.data4dota2.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 17:57 2019/2/11
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Mapper
public interface CommentMapper {
    List<Comment> selectByEntityId(Long id,Integer type);

    void add(Comment comment);

    Comment selectById(Long id);
}
