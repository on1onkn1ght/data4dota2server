package com.onionknight.data4dota2.mapper;

import com.onionknight.data4dota2.entity.Hero;
import com.onionknight.data4dota2.service.HeroService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author :fdy
 * @Date :Created in 15:27 2019/1/4
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HeroMapperTest {
    @Autowired
    private HeroMapper heroMapper;
    @Autowired
    private HeroService heroService;


}