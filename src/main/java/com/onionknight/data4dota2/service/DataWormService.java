package com.onionknight.data4dota2.service;

import com.onionknight.data4dota2.entity.Hero;
import com.onionknight.data4dota2.entity.Item;
import com.onionknight.data4dota2.entity.Skill;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @Author :fdy
 * @Date :Created in 13:52 2019/1/7
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public interface DataWormService {
    List<Hero> searchHeroes() throws IOException, InterruptedException, ExecutionException;
    Hero searchHero(String heroUrl) throws IOException;
    Item searchItem(String itemUrl)throws Exception;
    List<Item> searchItems()throws Exception;
    List<Hero> searchTalents() throws Exception;
    List<Skill> searchSkills()  throws Exception;
    public void downImage(String filePath,String imgUrl);
    List<String> searchRelation() throws Exception;
    public void downloadIcon()throws Exception;
}
