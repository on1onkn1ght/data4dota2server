package com.onionknight.data4dota2.service;

import com.ibasco.agql.protocols.valve.dota2.webapi.Dota2WebApiClient;
import com.ibasco.agql.protocols.valve.dota2.webapi.pojos.Dota2Heroes;
import com.onionknight.data4dota2.entity.Hero;
import com.onionknight.data4dota2.entity.Name;

import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 14:29 2019/1/4
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public interface HeroService {
    Hero findByName(String name);
    List<Hero> findByAttri(String attribute);
    List<Hero> findAllHero();
    void addAllHeroIfo(List<Hero> heroList);
    List<Dota2Heroes> findAllHeroFromSteam(Dota2WebApiClient client);
    List<Hero> findAllHeroName();
    void updateTalent(List<Hero> heroList);
}
