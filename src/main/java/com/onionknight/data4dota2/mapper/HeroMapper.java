package com.onionknight.data4dota2.mapper;

import com.onionknight.data4dota2.entity.Hero;

import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 14:20 2019/1/4
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public interface HeroMapper {
    Hero findByName(String name);
    List<Hero> findByAttri(String attribute);
    int addHero(Hero hero);
    List<Hero> findAllHero();

    List<Hero> findAllHeroName();

    void updateTalent(Hero hero);
}

