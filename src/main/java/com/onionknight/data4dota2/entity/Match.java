package com.onionknight.data4dota2.entity;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 10:39 2019/1/18
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public class Match {
    private Long id;
    private MatchOverview matchOverview;
    private List<MatchSnap> matchDetail = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MatchOverview getMatchOverview() {
        return matchOverview;
    }

    public void setMatchOverview(MatchOverview matchOverview) {
        this.matchOverview = matchOverview;
    }

    public List<MatchSnap> getMatchDetail() {
        return matchDetail;
    }

    public void setMatchDetail(List<MatchSnap> matchDetail) {
        this.matchDetail = matchDetail;
    }
}
