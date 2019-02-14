package com.onionknight.data4dota2.mapper;

import com.onionknight.data4dota2.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author :fdy
 * @Date :Created in 13:49 2019/2/10
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Mapper
public interface UserMapper {
    void addUser(User user);
    User selectUserByName(@Param("name") String name);
}
