package com.onionknight.data4dota2.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author :fdy
 * @Date :Created in 11:18 2019/2/11
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public class CookieUtil {
    public static String getCookie(HttpServletRequest request,String key){
        Cookie[] cookies = request.getCookies();
        if(cookies==null||cookies.length==0) return null;
        for (Cookie cookie:cookies){
            if (cookie.getName().equals(key))
                return cookie.getValue();
        }
        return null;
    }
}
