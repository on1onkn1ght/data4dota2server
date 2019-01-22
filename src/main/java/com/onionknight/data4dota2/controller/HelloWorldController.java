package com.onionknight.data4dota2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author :fdy
 * @Date :Created in 15:35 2019/1/1
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Controller
public class HelloWorldController {
    @RequestMapping("/hello")
    @ResponseBody
    @CrossOrigin
    public String index(){

        return "suceess";
    }
}
