package com.onionknight.data4dota2.async;

/**
 * @Author :fdy
 * @Date :Created in 14:11 2019/2/13
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public enum EventType {
    LIKE(0),
    COMMENT(1),
    lOGIN(2),
    MAIL(3);
    private int value;
    EventType(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
