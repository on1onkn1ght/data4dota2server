package com.onionknight.data4dota2.mapper;

import com.onionknight.data4dota2.entity.Match;
import com.onionknight.data4dota2.entity.MatchJson;

/**
 * @Author :fdy
 * @Date :Created in 14:09 2019/1/18
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public interface MatchMapper {
    void addMatchOverview(long id ,byte[] overview,String url);
    MatchJson getMatchUrl(long id);

    void addMatchDetail(long id, byte[] matchans);

    MatchJson getMatchOverview(long id);
}
