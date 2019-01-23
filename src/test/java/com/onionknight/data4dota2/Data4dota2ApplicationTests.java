package com.onionknight.data4dota2;

import com.ibasco.agql.protocols.valve.dota2.webapi.Dota2WebApiClient;
import com.ibasco.agql.protocols.valve.dota2.webapi.pojos.Dota2GameItem;
import com.ibasco.agql.protocols.valve.dota2.webapi.pojos.Dota2Heroes;
import com.onionknight.data4dota2.entity.*;
import com.onionknight.data4dota2.mapper.*;
import com.onionknight.data4dota2.service.*;
import com.onionknight.data4dota2.utils.HttpClientUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.user.UserRegistryMessageHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.sql.DatabaseMetaData;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Data4dota2ApplicationTests {
    private MockMvc mvc;
    @Autowired
    HeroService heroService;
    @Autowired
    SkillService skillService;
    @Autowired
    MatchService matchService;
    @Autowired
    ItemService itemService;
    @Autowired
    MatchMapper matchMapper;

    @Autowired
    HeroMapper heroMapper;

    @Test
    public void getHeros() throws Exception {
        String token = "3799EF44464E180230ED399292D33CF1";
        Dota2WebApiClient client = new Dota2WebApiClient(token);
        List<Hero> allHero = heroService.findAllHero();
        List<Dota2Heroes> dotaHeroes = heroService.findAllHeroFromSteam(client);
        Map<String, Integer> map = new HashMap<>();
        for (Dota2Heroes hero : dotaHeroes) {
            String name = hero.getName();
            name = name.substring(name.lastIndexOf("hero_") + 5);
            System.out.println(name);
            map.put(name, hero.getId());
        }
        int count = 0;
        for (Hero hero : allHero) {
            if (map.containsKey(hero.getName_en())) {
                hero.setHero_id(map.get(hero.getName_en()));
                count++;
            } else {
                System.out.println(hero.getName_cn());
                System.out.println(hero.getName_en());
            }
        }
        heroService.addAllHeroIfo(allHero);
    }

    @Test
    public void getItems() throws Exception {
        String token = "3799EF44464E180230ED399292D33CF1";
        Dota2WebApiClient client = new Dota2WebApiClient(token);
        List<Item> items = itemService.findItems();
        List<Dota2GameItem> gameItems = itemService.getItems(client);
        Map<String, Integer> map = new HashMap<>();
        for (Dota2GameItem gameItem : gameItems) {
            String name = gameItem.getName();
            name = name.substring(name.indexOf("_")+1);
            map.put(name,gameItem.getId());
        }
        int count=0;
        for (Item item:items){
            if (map.containsKey(item.getName_en())) {
                item.setItem_id(map.get(item.getName_en()));
                count++;
            } else {
                System.out.println(item.getName_cn());
                System.out.println(item.getName_en());
            }
        }
        itemService.addItems(items);
    }
    @Test
    public void code()throws Exception {
        HttpClientUtils httpClientUtils = HttpClientUtils.getInstance();
        String url = "https://api.opendota.com/api/matches/4157472318" ;
        System.out.println(httpClientUtils.httpGet(url));
    }
}

