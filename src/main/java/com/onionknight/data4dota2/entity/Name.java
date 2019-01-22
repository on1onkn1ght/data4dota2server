package com.onionknight.data4dota2.entity;

/**
 * @Author :fdy
 * @Date :Created in 16:42 2019/1/4
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public class Name {

    String name_en;
    String name_cn;

    public Name(String name_en, String name_cn) {
        this.name_en = name_en;
        this.name_cn = name_cn;
    }

    @Override
    public String toString() {
        return "Name{" +
                "name_en='" + name_en + '\'' +
                ", name_cn='" + name_cn + '\'' +
                '}';
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


}
