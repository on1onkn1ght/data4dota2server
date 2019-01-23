package com.onionknight.data4dota2.mapper;

import com.onionknight.data4dota2.entity.Item;

import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 18:44 2019/1/8
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public interface ItemMapper {
    List<Item> findItems();
    Item getItemById(int id);
    List<Item> getAllItems();
}
