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

    @Override
    public Hero findByName(String name) {
        return heroMapper.findByName(name);
    }

    @Override
    public List<Hero> findByAttri(String attribute) {
        return heroMapper.findByAttri(attribute);
    }

    @Override
    public List<Hero> findAllHero() {
        return heroMapper.findAllHero();
    }

    @Override
    public void addAllHeroIfo(List<Hero> heroList) {
        for (Hero hero:heroList){
            heroMapper.addHero(hero);
            for (Skill skill:hero.getSkills()){
                skill.setHero_id(hero.getHero_id());
            }
            skillMapper.addSkills(hero.getSkills());
        }
    }

    @Override
    public List<Dota2Heroes> findAllHeroFromSteam(Dota2WebApiClient client) {
        Dota2Econ econInterface = new Dota2Econ(client);
        try {
            List<Dota2Heroes> heroes = econInterface.getGameHeroes(false, "en").get();
            return heroes;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<Hero> findAllHeroName() {
        return heroMapper.findAllHeroName();
    }

    @Override
    public void updateTalent(List<Hero> heroList) {
        for (Hero hero :heroList){
            heroMapper.updateTalent(hero);
        }
    }
}
