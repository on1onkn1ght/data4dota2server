package com.onionknight.data4dota2.service.impl;

import com.ibasco.agql.protocols.valve.dota2.webapi.Dota2WebApiClient;
import com.ibasco.agql.protocols.valve.dota2.webapi.interfaces.Dota2Econ;
import com.ibasco.agql.protocols.valve.dota2.webapi.pojos.Dota2GameItem;
import com.onionknight.data4dota2.entity.Item;
import com.onionknight.data4dota2.mapper.ItemMapper;
import com.onionknight.data4dota2.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @Author :fdy
 * @Date :Created in 11:54 2019/1/12
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Service(value = "itemService")
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemMapper itemMapper;

    /**
     * 查询物品ID 名称
     * @return
     */
    @Override
    public List<Item> findItems() {
        return itemMapper.findItems();
    }

    /**
     * 根据ID查询物品
     * @param id
     * @return
     */
    @Override
    public Item getItemById(int id) {
        return itemMapper.getItemById(id);
    }

    /**
     * 查询所有物品
     * @return
     */
    @Override
    public List<Item> getAllItems() {
        return itemMapper.getAllItems();
    }
}
