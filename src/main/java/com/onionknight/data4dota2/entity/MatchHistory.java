package com.onionknight.data4dota2.entity;

/**
 * @Author :fdy
 * @Date :Created in 14:13 2019/1/19
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public class MatchHistory {
    private Long matchId;
    private String heroName;
    private String time;
    private String camp;

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCamp() {
        return camp;
    }

    public void setCamp(String camp) {
        this.camp = camp;
    }
}
