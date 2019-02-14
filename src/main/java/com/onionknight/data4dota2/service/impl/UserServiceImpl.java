package com.onionknight.data4dota2.service.impl;

import com.onionknight.data4dota2.entity.RespCode;
import com.onionknight.data4dota2.entity.RespEntity;
import com.onionknight.data4dota2.entity.User;
import com.onionknight.data4dota2.mapper.UserMapper;
import com.onionknight.data4dota2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author :fdy
 * @Date :Created in 14:09 2019/2/10
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public RespEntity addUser(User user) {
        User user1 = userMapper.selectUserByName(user.getUsername());
        if(user1==null) {
            userMapper.addUser(user);
            return new RespEntity(RespCode.SUCCESS);
        }else{
            return new RespEntity(RespCode.WARN,"用户已存在");
        }
    }

    @Override
    public RespEntity login(User user) {
        System.out.println(user.getUsername());
        User user1 = userMapper.selectUserByName(user.getUsername());
        if (user1==null||!user1.getPassword().equals(user.getPassword()))
            return new RespEntity(RespCode.WARN,"用户不存在或密码错误");
        System.out.println(user1.getNickname());
        return new RespEntity(RespCode.SUCCESS,user1);
    }



}
