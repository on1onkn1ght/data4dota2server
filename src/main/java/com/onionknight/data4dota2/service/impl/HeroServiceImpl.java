package com.onionknight.data4dota2.service.impl;

import com.ibasco.agql.protocols.valve.dota2.webapi.Dota2WebApiClient;
import com.ibasco.agql.protocols.valve.dota2.webapi.interfaces.Dota2Econ;
import com.ibasco.agql.protocols.valve.dota2.webapi.pojos.Dota2Heroes;
import com.onionknight.data4dota2.entity.Skill;
import com.onionknight.data4dota2.mapper.HeroMapper;
import com.onionknight.data4dota2.entity.Hero;

import com.onionknight.data4dota2.mapper.SkillMapper;
import com.onionknight.data4dota2.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 14:31 2019/1/4
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Service(value = "heroService")
public class HeroServiceImpl implements HeroService {
    @Autowired
    private HeroMapper heroMapper;
    @Autowired
    private SkillMapper skillMapper;

    /**
     *  通过英雄英文名联表查询英雄全部信息
     * @param name 英文名
     * @return hero entity
     */
    @Override
    public Hero findByName(String name) {
        return heroMapper.findByName(name);
    }

    @Override
    public List<Hero> findAllHeroName() {
        return heroMapper.findAllHeroName();
    }

}
