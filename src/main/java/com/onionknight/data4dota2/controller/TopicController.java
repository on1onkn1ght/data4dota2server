package com.onionknight.data4dota2.controller;

import com.onionknight.data4dota2.entity.*;
import com.onionknight.data4dota2.service.CommentService;
import com.onionknight.data4dota2.service.TopicService;
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
 * @Date :Created in 14:31 2019/2/11
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@RestController
@RequestMapping("/topic")
@CrossOrigin(origins = {"http://localhost:8081"},allowCredentials = "true")
public class TopicController {
    @Autowired
    TopicService topicService;
    @Autowired
    CommentService commentService;
    @Autowired
    RedisUtil redisUtil;
    @GetMapping(value = "/findPage")
    public RespEntity findpage(@RequestParam("num") int num, @RequestParam("rows") int rows){
        PageResult<Topic> topicPageResult = topicService.selectAllTopic(num, rows);
        return new RespEntity(RespCode.SUCCESS,topicPageResult);
    }
    @PostMapping(value = "/add")
    public RespEntity addTopic(@RequestBody Topic topic, HttpServletRequest request){
        String token = CookieUtil.getCookie(request, "token");
        if(token==null)
            return new RespEntity(RespCode.UNAUTHORIZED,"请登录");
        User user = redisUtil.get(token);
        topic.setUserId(user.getId());
        topic.setNickname(user.getUsername());
        topicService.addTopic(topic);
        return new RespEntity(RespCode.SUCCESS);
    }
    @GetMapping(value = "/detail")
    public RespEntity detail(@RequestParam("id") Long id){
        Topic topic = topicService.selectById(id);
        return new RespEntity(RespCode.SUCCESS,topic);
    }
}
