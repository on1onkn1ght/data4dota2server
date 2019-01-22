package com.onionknight.data4dota2.entity;

import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 17:54 2019/1/6
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public class Item {
    private Integer item_id;
    private String name_en;
    private String name_cn;
    private Integer price;
    private Double cd;
    private Integer cost;
    private String description;
    private Integer hp;
    private Integer mp;
    private Double str;
    private Double intl;
    private Double agi;
    private Double attack_power;
    private Double magic_power;
    private Double armor;
    private Integer magic_resis;
    private Integer move_speed;
    private Double attack_speed;
    private Integer type;
    private List<Item> parts;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Item> getParts() {
        return parts;
    }

    public void setParts(List<Item> parts) {
        this.parts = parts;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getCd() {
        return cd;
    }

    public void setCd(Double cd) {
        this.cd = cd;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getMp() {
        return mp;
    }

    public void setMp(Integer mp) {
        this.mp = mp;
    }

    public Double getStr() {
        return str;
    }

    public void setStr(Double str) {
        this.str = str;
    }

    public Double getIntl() {
        return intl;
    }

    public void setIntl(Double intl) {
        this.intl = intl;
    }

    public Double getAgi() {
        return agi;
    }

    public void setAgi(Double agi) {
        this.agi = agi;
    }

    public Double getAttack_power() {
        return attack_power;
    }

    public void setAttack_power(Double attack_power) {
        this.attack_power = attack_power;
    }

    public Double getMagic_power() {
        return magic_power;
    }

    public void setMagic_power(Double magic_power) {
        this.magic_power = magic_power;
    }

    public Double getArmor() {
        return armor;
    }

    public void setArmor(Double armor) {
        this.armor = armor;
    }

    public Integer getMagic_resis() {
        return magic_resis;
    }

    public void setMagic_resis(Integer magic_resis) {
        this.magic_resis = magic_resis;
    }



    public Integer getMove_speed() {
        return move_speed;
    }

    public void setMove_speed(Integer move_speed) {
        this.move_speed = move_speed;
    }

    public Double getAttack_speed() {
        return attack_speed;
    }

    public void setAttack_speed(Double attack_speed) {
        this.attack_speed = attack_speed;
    }
}
