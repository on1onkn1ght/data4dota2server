package com.onionknight.data4dota2.controller;

import com.onionknight.data4dota2.entity.*;
import com.onionknight.data4dota2.service.CommentService;
import com.onionknight.data4dota2.service.LikeService;
import com.onionknight.data4dota2.utils.CookieUtil;
import com.onionknight.data4dota2.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author :fdy
 * @Date :Created in 18:39 2019/2/11
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = {"http://localhost:8081"},allowCredentials = "true")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    LikeService likeService;
    @Autowired
    RedisUtil redisUtil;
    @GetMapping(value = "/findPage")
    public RespEntity findPage(@RequestParam("id") Long id,@RequestParam("type") Integer type,
                             @RequestParam("num") int num, @RequestParam("rows") int rows){
        PageResult<Comment> comments = commentService.selectByEntity(id, type, num, rows);
        return new RespEntity(RespCode.SUCCESS,comments);
    }
    @PostMapping(value = "/add")
    public RespEntity add(@RequestBody Comment comment,HttpServletRequest request){
        String token = CookieUtil.getCookie(request, "token");
        if(token==null)
            return new RespEntity(RespCode.UNAUTHORIZED);
        User user = redisUtil.get(token);
        comment.setNickname(user.getNickname());
        comment.setUserId(user.getId());
        commentService.addComment(comment);
        return new RespEntity(RespCode.SUCCESS);
    }
    @GetMapping(value = "/like")
    public RespEntity like(@RequestParam("id") Long id, HttpServletRequest request){
        String token = CookieUtil.getCookie(request, "token");
        if(token==null)
            return new RespEntity(RespCode.UNAUTHORIZED);
        User user = redisUtil.get(token);
        long liked = likeService.liked(user.getId(), 1, id);
        return new RespEntity(RespCode.SUCCESS,liked);
    }
    @GetMapping(value = "/likenum")
    public RespEntity likeNum(@RequestParam("id") Long id){
        long likednum = likeService.likednum(1, id);
        return new RespEntity(RespCode.SUCCESS,likednum);
    }
    @GetMapping(value = "/unlike")
    public RespEntity unlike(@RequestParam("id") Long id, HttpServletRequest request){
        String token = CookieUtil.getCookie(request, "token");
        if(token==null)
            return new RespEntity(RespCode.UNAUTHORIZED);
        User user = redisUtil.get(token);
        long liked = likeService.unliked(user.getId(), 1, id);
        return new RespEntity(RespCode.SUCCESS,liked);
    }
}
