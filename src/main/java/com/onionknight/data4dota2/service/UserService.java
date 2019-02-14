package com.onionknight.data4dota2.service;

import com.onionknight.data4dota2.entity.RespEntity;
import com.onionknight.data4dota2.entity.User;

/**
 * @Author :fdy
 * @Date :Created in 14:09 2019/2/10
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public interface UserService  {
    RespEntity addUser(User user);
    RespEntity login(User user);

}
