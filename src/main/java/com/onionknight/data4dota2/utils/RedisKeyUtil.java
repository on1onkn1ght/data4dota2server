package com.onionknight.data4dota2.utils;

/**
 * @Author :fdy
 * @Date :Created in 12:03 2019/2/12
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public class RedisKeyUtil {
    public final static String SPLIT = ":";
    public final static String LIKED = "liked";
    public final static String EVENTQUEUE = "eventQueue";
    public static String getLikeKey(int type,Long id){
        return LIKED+SPLIT+type+SPLIT+id;
    }
    public static String getEventQueueKey(){
        return EVENTQUEUE;
    }
}
