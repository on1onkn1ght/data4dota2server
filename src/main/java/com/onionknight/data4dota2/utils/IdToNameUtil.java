package com.onionknight.data4dota2.utils;

import com.onionknight.data4dota2.entity.Hero;
import com.onionknight.data4dota2.entity.Item;
import com.onionknight.data4dota2.mapper.HeroMapper;
import com.onionknight.data4dota2.mapper.ItemMapper;
import com.onionknight.data4dota2.service.HeroService;
import com.onionknight.data4dota2.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 12:51 2019/1/23
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Component
public class IdToNameUtil {
    private HashMap<Integer, String> heroIdToEnName;
    private HashMap<Integer, String> heroIdToCnName;
    private HashMap<Integer, String> itemIdToName;
    @Autowired
    public IdToNameUtil(HeroMapper heroMapper, ItemMapper itemMapper){
        List<Hero> heroes = heroMapper.findAllHeroName();
        List<Item> items = itemMapper.findItems();
        heroIdToCnName = new HashMap<>();
        heroIdToEnName = new HashMap<>();
        for (Hero hero : heroes) {
            heroIdToEnName.put(hero.getHero_id(),hero.getName_en());
            heroIdToCnName.put(hero.getHero_id(),hero.getName_cn());
        }
        itemIdToName = new HashMap<>();
        for (Item item : items) {
            itemIdToName.put(item.getItem_id(),item.getName_en());
        }
    }

    public HashMap<Integer, String> getHeroIdToEnName() {
        return heroIdToEnName;
    }

    public void setHeroIdToEnName(HashMap<Integer, String> heroIdToEnName) {
        this.heroIdToEnName = heroIdToEnName;
    }

    public HashMap<Integer, String> getHeroIdToCnName() {
        return heroIdToCnName;
    }

    public void setHeroIdToCnName(HashMap<Integer, String> heroIdToCnName) {
        this.heroIdToCnName = heroIdToCnName;
    }

    public HashMap<Integer, String> getItemIdToName() {
        return itemIdToName;
    }

    public void setItemIdToName(HashMap<Integer, String> itemIdToName) {
        this.itemIdToName = itemIdToName;
    }
}
