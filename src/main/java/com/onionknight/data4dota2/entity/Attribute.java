package com.onionknight.data4dota2.entity;

import javax.swing.plaf.PanelUI;

/**
 * @Author :fdy
 * @Date :Created in 15:03 2019/1/8
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public class Attribute {

    private int hp;
    private int mp;
    private double str;
    private double intl;
    private double agi;
    private double attack_power;
    private double magic_power;
    private double armor;
    private int magic_resis;
    private int attack_arrange;
    private int vision_field_day;
    private int vision_field_night;
    private int move_speed;
    private double attack_speed;

    public Attribute() { }

    public Attribute(int hp, int mp, double str, double intl, double agi, double attack_power, double magic_power, double armor, int magic_resis, int attack_arrange, int vision_field_day, int vision_field_night, int move_speed, double attack_speed) {
        this.hp = hp;
        this.mp = mp;
        this.str = str;
        this.intl = intl;
        this.agi = agi;
        this.attack_power = attack_power;
        this.magic_power = magic_power;
        this.armor = armor;
        this.magic_resis = magic_resis;
        this.attack_arrange = attack_arrange;
        this.vision_field_day = vision_field_day;
        this.vision_field_night = vision_field_night;
        this.move_speed = move_speed;
        this.attack_speed = attack_speed;
    }

    public Attribute add(Attribute added) {
        this.hp += added.hp;
        this.mp += added.mp;
        this.str += added.str;
        this.intl += added.intl;
        this.agi += added.agi;
        this.attack_power += added.attack_power;
        this.armor += added.armor;
        this.magic_resis += added.magic_resis;
        this.attack_arrange += added.attack_arrange;
        this.vision_field_day += added.vision_field_day;
        this.vision_field_night += added.vision_field_night;
        this.move_speed += added.move_speed;
        this.attack_speed += added.attack_speed;
        return this;
    }

    public Attribute copy() {
        Attribute attribute = new Attribute();
        attribute.hp = this.hp;
        attribute.mp = this.mp;
        attribute.str= this.str;
        attribute.intl = this.intl;
        attribute.agi = this.agi;
        attribute.attack_power = this.attack_power;
        attribute.armor = this.armor;
        attribute.magic_resis = this.magic_resis;
        attribute.attack_arrange = this.attack_arrange;
        attribute.vision_field_day = this.vision_field_day;
        attribute.vision_field_night = this.vision_field_night;
        attribute.move_speed = this.move_speed;
        attribute.attack_speed = this.attack_speed;
        return attribute;
    }

    public Attribute minus(Attribute added) {
        this.hp -= added.hp;
        this.mp -= added.mp;
        this.str -= added.str;
        this.intl -= added.intl;
        this.agi -= added.agi;
        this.attack_power -= added.attack_power;
        this.armor -= added.armor;
        this.magic_resis -= added.magic_resis;
        this.attack_arrange -= added.attack_arrange;
        this.vision_field_day -= added.vision_field_day;
        this.vision_field_night -= added.vision_field_night;
        this.move_speed -= added.move_speed;
        this.attack_speed -= added.attack_speed;
        return this;
    }
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public double getMagic_power() {
        return magic_power;
    }

    public void setMagic_power(double magic_power) {
        this.magic_power = magic_power;
    }

    public double getStr() {
        return str;
    }

    public void setStr(double str) {
        this.str = str;
    }

    public double getIntl() {
        return intl;
    }

    public void setIntl(double intl) {
        this.intl = intl;
    }

    public double getAgi() {
        return agi;
    }

    public void setAgi(double agi) {
        this.agi = agi;
    }

    public double getAttack_power() {
        return attack_power;
    }

    public void setAttack_power(double attack_power) {
        this.attack_power = attack_power;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public int getMagic_resis() {
        return magic_resis;
    }

    public void setMagic_resis(int magic_resis) {
        this.magic_resis = magic_resis;
    }

    public int getAttack_arrange() {
        return attack_arrange;
    }

    public void setAttack_arrange(int attack_arrange) {
        this.attack_arrange = attack_arrange;
    }

    public int getVision_field_day() {
        return vision_field_day;
    }

    public void setVision_field_day(int vision_field_day) {
        this.vision_field_day = vision_field_day;
    }

    public int getVision_field_night() {
        return vision_field_night;
    }

    public void setVision_field_night(int vision_field_night) {
        this.vision_field_night = vision_field_night;
    }

    public int getMove_speed() {
        return move_speed;
    }

    public void setMove_speed(int move_speed) {
        this.move_speed = move_speed;
    }

    public double getAttack_speed() {
        return attack_speed;
    }

    public void setAttack_speed(double attack_speed) {
        this.attack_speed = attack_speed;
    }
}
