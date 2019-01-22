package com.onionknight.data4dota2.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 15:13 2019/1/17
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public class MatchSnap {
    private Integer time =0;
    private Integer roshans = 0;
    private List<HeroState> heros = new ArrayList<>(10);

    public Integer getRoshans() {
        return roshans;
    }

    public void setRoshans(Integer roshans) {
        this.roshans = roshans;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public List<HeroState> getHeros() {
        return heros;
    }

    public void setHeros(List<HeroState> heros) {
        this.heros = heros;
    }

    public class HeroState{
        private String heroname ;
        private Integer position_x;
        private Integer position_y;
        private Integer killes;
        private Integer dies;
        private Integer assists;
        private Integer lifeState;
        private Integer gold;
        private Integer xp;
        private Integer level;

        public String getHeroname() {
            return heroname;
        }

        public void setHeroname(String heroname) {
            this.heroname = heroname;
        }

        public Integer getPosition_x() {
            return position_x;
        }

        public void setPosition_x(Integer position_x) {
            this.position_x = position_x;
        }

        public Integer getPosition_y() {
            return position_y;
        }

        public void setPosition_y(Integer position_y) {
            this.position_y = position_y;
        }

        public Integer getKilles() {
            return killes;
        }

        public void setKilles(Integer killes) {
            this.killes = killes;
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

        public Integer getLifeState() {
            return lifeState;
        }

        public void setLifeState(Integer lifeState) {
            this.lifeState = lifeState;
        }

        public Integer getGold() {
            return gold;
        }

        public void setGold(Integer gold) {
            this.gold = gold;
        }

        public Integer getXp() {
            return xp;
        }

        public void setXp(Integer xp) {
            this.xp = xp;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public Double getParticipation() {
            return participation;
        }

        public void setParticipation(Double participation) {
            this.participation = participation;
        }

        public Integer getLastHit() {
            return lastHit;
        }

        public void setLastHit(Integer lastHit) {
            this.lastHit = lastHit;
        }

        public Integer getDenies() {
            return denies;
        }

        public void setDenies(Integer denies) {
            this.denies = denies;
        }

        public Integer getRunePick() {
            return runePick;
        }

        public void setRunePick(Integer runePick) {
            this.runePick = runePick;
        }

        public Integer getWard() {
            return ward;
        }

        public void setWard(Integer ward) {
            this.ward = ward;
        }

        public Integer getSentry() {
            return sentry;
        }

        public void setSentry(Integer sentry) {
            this.sentry = sentry;
        }

        public List<String> getItems() {
            return items;
        }

        public void setItems(List<String> items) {
            this.items = items;
        }

        private Double participation;
        private Integer lastHit;
        private Integer denies;
        private Integer runePick;
        private Integer ward;
        private Integer sentry;
        private List<String> items = new ArrayList<>(9);
    }

}
