package com.onionknight.data4dota2.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onionknight.data4dota2.entity.Comment;
import com.onionknight.data4dota2.entity.PageResult;
import com.onionknight.data4dota2.mapper.CommentMapper;
import com.onionknight.data4dota2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 18:08 2019/2/11
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Override
    public PageResult<Comment> selectByEntity(Long id, Integer type,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Comment> comments = (Page<Comment>) commentMapper.selectByEntityId(id, type);
        return new PageResult<>(comments.getTotal(),comments.getResult());
    }

    @Override
    public void addComment(Comment comment) {
        commentMapper.add(comment);
    }
}
