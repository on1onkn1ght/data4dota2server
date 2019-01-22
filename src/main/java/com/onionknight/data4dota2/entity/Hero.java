package com.onionknight.data4dota2.entity;

import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 13:30 2019/1/4
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public class Hero {
    private Integer hero_id;
    private String name_en;
    private String name_cn;
    private String icon_hp;
    private String icon_sb;
    private String primary_attr;
    private String attack_type;
    private String camp;

    private String roles;

    //初始属性
    private Integer init_hp;
    private Integer init_mp;
    private Double init_str;
    private Double init_intl;
    private Double init_agi;
    private Double init_attack_power;
    private Double init_armor;
    private Integer init_magic_resis;
    private Integer init_attack_arrange;
    private Integer init_vision_field_day;
    private Integer init_vision_field_night;
    private Integer init_move_speed;
    private Double init_attack_speed;
    private Integer init_level = 1;
    //属性加成

    private Double level_str;
    private Double level_agi;
    private Double level_int;
    //属性

    //弹道速度
    private Integer ball_speed;
    private List<Skill> skills;
    private String talents;
    private String description;


    public Integer getHero_id() {
        return hero_id;
    }

    public void setHero_id(Integer hero_id) {
        this.hero_id = hero_id;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getName_cn() {
        return name_cn;
    }

    public void setName_cn(String name_cn) {
        this.name_cn = name_cn;
    }

    public String getIcon_hp() {
        return icon_hp;
    }

    public void setIcon_hp(String icon_hp) {
        this.icon_hp = icon_hp;
    }

    public String getIcon_sb() {
        return icon_sb;
    }

    public void setIcon_sb(String icon_sb) {
        this.icon_sb = icon_sb;
    }

    public String getPrimary_attr() {
        return primary_attr;
    }

    public void setPrimary_attr(String primary_attr) {
        this.primary_attr = primary_attr;
    }

    public String getAttack_type() {
        return attack_type;
    }

    public void setAttack_type(String attack_type) {
        this.attack_type = attack_type;
    }

    public String getCamp() {
        return camp;
    }

    public void setCamp(String camp) {
        this.camp = camp;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Integer getInit_hp() {
        return init_hp;
    }

    public void setInit_hp(Integer init_hp) {
        this.init_hp = init_hp;
    }

    public Integer getInit_mp() {
        return init_mp;
    }

    public void setInit_mp(Integer init_mp) {
        this.init_mp = init_mp;
    }

    public Double getInit_str() {
        return init_str;
    }

    public void setInit_str(Double init_str) {
        this.init_str = init_str;
    }

    public Double getInit_intl() {
        return init_intl;
    }

    public void setInit_intl(Double init_intl) {
        this.init_intl = init_intl;
    }

    public Double getInit_agi() {
        return init_agi;
    }

    public void setInit_agi(Double init_agi) {
        this.init_agi = init_agi;
    }

    public Double getInit_attack_power() {
        return init_attack_power;
    }

    public void setInit_attack_power(Double init_attack_power) {
        this.init_attack_power = init_attack_power;
    }

    public Double getInit_armor() {
        return init_armor;
    }

    public void setInit_armor(Double init_armor) {
        this.init_armor = init_armor;
    }

    public Integer getInit_magic_resis() {
        return init_magic_resis;
    }

    public void setInit_magic_resis(Integer init_magic_resis) {
        this.init_magic_resis = init_magic_resis;
    }

    public Integer getInit_attack_arrange() {
        return init_attack_arrange;
    }

    public void setInit_attack_arrange(Integer init_attack_arrange) {
        this.init_attack_arrange = init_attack_arrange;
    }

    public Integer getInit_vision_field_day() {
        return init_vision_field_day;
    }

    public void setInit_vision_field_day(Integer init_vision_field_day) {
        this.init_vision_field_day = init_vision_field_day;
    }

    public Integer getInit_vision_field_night() {
        return init_vision_field_night;
    }

    public void setInit_vision_field_night(Integer init_vision_field_night) {
        this.init_vision_field_night = init_vision_field_night;
    }

    public Integer getInit_move_speed() {
        return init_move_speed;
    }

    public void setInit_move_speed(Integer init_move_speed) {
        this.init_move_speed = init_move_speed;
    }

    public Double getInit_attack_speed() {
        return init_attack_speed;
    }

    public void setInit_attack_speed(Double init_attack_speed) {
        this.init_attack_speed = init_attack_speed;
    }

    public Integer getInit_level() {
        return init_level;
    }

    public void setInit_level(Integer init_level) {
        this.init_level = init_level;
    }

    public Double getLevel_str() {
        return level_str;
    }

    public void setLevel_str(Double level_str) {
        this.level_str = level_str;
    }

    public Double getLevel_agi() {
        return level_agi;
    }

    public void setLevel_agi(Double level_agi) {
        this.level_agi = level_agi;
    }

    public Double getLevel_int() {
        return level_int;
    }

    public void setLevel_int(Double level_int) {
        this.level_int = level_int;
    }

    public Integer getBall_speed() {
        return ball_speed;
    }

    public void setBall_speed(Integer ball_speed) {
        this.ball_speed = ball_speed;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public String getTalents() {
        return talents;
    }

    public void setTalents(String talents) {
        this.talents = talents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
