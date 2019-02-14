package com.onionknight.data4dota2.service;

import com.onionknight.data4dota2.entity.Comment;
import com.onionknight.data4dota2.entity.PageResult;

import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 18:06 2019/2/11
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public interface CommentService {
    PageResult<Comment> selectByEntity(Long id, Integer type,int pageNum,int pageSize);
    void addComment(Comment comment);
}
