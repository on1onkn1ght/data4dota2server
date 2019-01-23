package com.onionknight.data4dota2.service;

import com.ibasco.agql.protocols.valve.dota2.webapi.Dota2WebApiClient;
import com.ibasco.agql.protocols.valve.dota2.webapi.pojos.Dota2GameItem;
import com.onionknight.data4dota2.entity.Item;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @Author :fdy
 * @Date :Created in 11:53 2019/1/12
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public interface ItemService {
    List<Dota2GameItem> getItems(Dota2WebApiClient client) throws ExecutionException, InterruptedException;
    List<Item> findItems();
    void addItems(List<Item> list);
    Item getItemById(int id);

    List<Item> getAllItems();
}
