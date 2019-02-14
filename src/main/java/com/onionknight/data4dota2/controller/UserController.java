package com.onionknight.data4dota2.controller;

import com.onionknight.data4dota2.entity.RespCode;
import com.onionknight.data4dota2.entity.RespEntity;
import com.onionknight.data4dota2.entity.User;
import com.onionknight.data4dota2.mapper.UserMapper;
import com.onionknight.data4dota2.service.UserService;
import com.onionknight.data4dota2.utils.CookieUtil;
import com.onionknight.data4dota2.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

/**
 * @Author :fdy
 * @Date :Created in 14:11 2019/2/10
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:8081"},allowCredentials = "true")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RedisUtil redisUtil;
    @PostMapping("/register")
    public RespEntity register(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            if(bindingResult.getFieldError()==null)
                return new RespEntity(RespCode.WARN);
            return new RespEntity(RespCode.WARN,bindingResult.getFieldError().getDefaultMessage());
        }

        RespEntity respEntity = userService.addUser(user);
        return respEntity;
    }
    @PostMapping("/login")
    public RespEntity login(@RequestBody User user, HttpServletResponse response, HttpServletRequest request){
        if(user.getUsername()==null)
            return new RespEntity(RespCode.WARN,"用户名不能为空");
        String token1 = CookieUtil.getCookie(request, "token");
        if(token1 !=null)
            return new RespEntity(RespCode.SUCCESS,redisUtil.get(token1));
        RespEntity login = userService.login(user);
        User data = (User) login.getData();
        String token = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("token",token);
        cookie.setPath("/");
        response.addCookie(cookie);

        redisUtil.add(token,data);
        System.out.println(token);
        return login;
    }
    @GetMapping("/logout")
    public RespEntity logout(HttpServletResponse response, HttpServletRequest request){
        String token = CookieUtil.getCookie(request, "token");
        if(token==null)
            return new RespEntity(RespCode.WARN,"未登录");
        System.out.println(token);
        Cookie cookie = new Cookie("token", "0");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        redisUtil.del(token);
        return new RespEntity(RespCode.SUCCESS,"成功");
    }
}
