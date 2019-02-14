package com.onionknight.data4dota2;

import com.alibaba.fastjson.JSON;
import com.ibasco.agql.protocols.valve.dota2.webapi.Dota2WebApiClient;
import com.ibasco.agql.protocols.valve.dota2.webapi.pojos.Dota2GameItem;
import com.ibasco.agql.protocols.valve.dota2.webapi.pojos.Dota2Heroes;
import com.onionknight.data4dota2.entity.*;
import com.onionknight.data4dota2.mapper.*;
import com.onionknight.data4dota2.service.*;
import com.onionknight.data4dota2.utils.HttpClientUtils;
import com.onionknight.data4dota2.utils.RedisUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.user.UserRegistryMessageHandler;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

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
    protected WebApplicationContext wac;
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    RedisUtil redisUtil;
    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }
    @Test
    public void code()throws Exception {
        Comment comment = commentMapper.selectById(1L);
        System.out.println(comment.getContent());

    }
}

