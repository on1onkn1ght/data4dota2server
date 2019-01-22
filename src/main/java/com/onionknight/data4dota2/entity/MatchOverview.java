package com.onionknight.data4dota2.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 10:18 2019/1/18
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public class MatchOverview {
    private Integer duration;
    private Integer starttime;
    private boolean IsradiantWin;
    private String replyUrl;
    private Player[] players = new Player[10];
    public class Player{
        private Long playerId;
        private Integer slot;
        private String heroname;
        private Integer kills;
        private Integer dies;
        private Integer assists;
        private Integer gold;
        private Integer gpm;
        private Integer xpm;
        private Integer heroDamage;
        private Integer towerDamage;
        private Integer headling;
        private Integer level;
        private String name;
        private String[] items = new String[9];

        public Long getPlayerId() {
            return playerId;
        }

        public void setPlayerId(Long playerId) {
            this.playerId = playerId;
        }

        public Integer getSlot() {
            return slot;
        }

        public void setSlot(Integer slot) {
            this.slot = slot;
        }

        public String getHeroname() {
            return heroname;
        }

        public void setHeroname(String heroname) {
            this.heroname = heroname;
        }

        public Integer getKills() {
            return kills;
        }

        public void setKills(Integer kills) {
            this.kills = kills;
        }

        public Integer getDies() {
            return dies;
        }

        public void setDies(Integer dies) {
            this.dies = dies;
        }

        public Integer getAssists() {
            return assists;
        }

        public void setAssists(Integer assists) {
            this.assists = assists;
        }

        public Integer getGold() {
            return gold;
        }

        public void setGold(Integer gold) {
            this.gold = gold;
        }

        public Integer getGpm() {
            return gpm;
        }

        public void setGpm(Integer gpm) {
            this.gpm = gpm;
        }

        public Integer getXpm() {
            return xpm;
        }

        public void setXpm(Integer xpm) {
            this.xpm = xpm;
        }

        public Integer getHeroDamage() {
            return heroDamage;
        }

        public void setHeroDamage(Integer heroDamage) {
            this.heroDamage = heroDamage;
        }

        public Integer getTowerDamage() {
            return towerDamage;
        }

        public void setTowerDamage(Integer towerDamage) {
            this.towerDamage = towerDamage;
        }

        public Integer getHeadling() {
            return headling;
        }

        public void setHeadling(Integer headling) {
            this.headling = headling;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public String[] getItems() {
            return items;
        }

        public void setItems(String[] items) {
            this.items = items;
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getStarttime() {
        return starttime;
    }

    public void setStarttime(Integer starttime) {
        this.starttime = starttime;
    }

    public boolean isIsradiantWin() {
        return IsradiantWin;
    }

    public void setIsradiantWin(boolean isradiantWin) {
        IsradiantWin = isradiantWin;
    }

    public String getReplyUrl() {
        return replyUrl;
    }

    public void setReplyUrl(String replyUrl) {
        this.replyUrl = replyUrl;
    }
}
