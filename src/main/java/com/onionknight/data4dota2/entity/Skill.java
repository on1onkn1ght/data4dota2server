package com.onionknight.data4dota2.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 17:40 2019/1/6
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public class Skill {
    private Integer skill_id;
    private String name_cn;
    private String name_en;
    private Integer hero_id;
    private String description;
    private String mp_cost;
    private String cd;
    private String attributes;
    private String details ="";


    public Integer getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(Integer skill_id) {
        this.skill_id = skill_id;
    }

    public String getName_cn() {
        return name_cn;
    }

    public void setName_cn(String name_cn) {
        this.name_cn = name_cn;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public Integer getHero_id() {
        return hero_id;
    }

    public void setHero_id(Integer hero_id) {
        this.hero_id = hero_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMp_cost() {
        return mp_cost;
    }

    public void setMp_cost(String mp_cost) {
        this.mp_cost = mp_cost;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
