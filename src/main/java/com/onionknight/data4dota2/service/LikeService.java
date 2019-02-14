package com.onionknight.data4dota2.service;

/**
 * @Author :fdy
 * @Date :Created in 12:43 2019/2/12
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public interface LikeService {
    long liked(Long userId,int type, Long id);
    long likednum(int type, Long id);

    long unliked(Long id, int i, Long id1);
}
