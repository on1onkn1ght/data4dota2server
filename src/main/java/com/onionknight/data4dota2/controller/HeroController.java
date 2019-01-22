package com.onionknight.data4dota2.controller;

import com.onionknight.data4dota2.entity.Hero;
import com.onionknight.data4dota2.entity.RespCode;
import com.onionknight.data4dota2.entity.RespEntity;
import com.onionknight.data4dota2.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author :fdy
 * @Date :Created in 14:50 2019/1/4
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Controller
@RequestMapping(value = "/heroes")
public class HeroController {
    @Autowired
    private HeroService heroService;
    @RequestMapping(value = "/index")
    @CrossOrigin
    @ResponseBody
    public RespEntity findAllHeroes(){
        List<Hero> heroes = heroService.findAllHeroName();
        return new RespEntity(RespCode.SUCCESS,heroes);
    }
    @RequestMapping(value = "/hero",method = RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    public RespEntity findHero(@RequestParam("name") String name){
        Hero hero = heroService.findByName(name);
        return new RespEntity(RespCode.SUCCESS, hero);
    }
}
