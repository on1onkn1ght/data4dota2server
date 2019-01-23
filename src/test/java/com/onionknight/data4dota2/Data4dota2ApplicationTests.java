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
    public void code()throws Exception {
        Item itemById = itemService.getItemById(1);
        System.out.println(itemById.getDescription());
    }
}

