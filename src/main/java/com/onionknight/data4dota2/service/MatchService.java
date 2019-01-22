package com.onionknight.data4dota2.service;

import com.onionknight.data4dota2.entity.MatchHistory;
import com.onionknight.data4dota2.entity.MatchOverview;

import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 10:29 2019/1/18
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public interface MatchService {
    String getMatchDetail(long id);
    String  getMatchOverview(long id);
    List<MatchHistory> getMatchHistories(long acountId,int num,long matchId);
}
