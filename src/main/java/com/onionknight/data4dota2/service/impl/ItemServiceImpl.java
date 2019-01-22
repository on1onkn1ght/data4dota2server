package com.onionknight.data4dota2.service.impl;

import com.ibasco.agql.protocols.valve.dota2.webapi.Dota2WebApiClient;
import com.ibasco.agql.protocols.valve.dota2.webapi.interfaces.Dota2Econ;
import com.ibasco.agql.protocols.valve.dota2.webapi.pojos.Dota2GameItem;
import com.onionknight.data4dota2.entity.Item;
import com.onionknight.data4dota2.mapper.ItemMapper;
import com.onionknight.data4dota2.service.DataWormService;
import com.onionknight.data4dota2.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Autowired
    DataWormService dataWormService;
    @Override
    public List<Dota2GameItem> getItems(Dota2WebApiClient client) throws ExecutionException, InterruptedException {
        Dota2Econ econInterface = new Dota2Econ(client);
        return econInterface.getGameItems().get();
    }

    @Override
    public List<Item> findItems() {
        return itemMapper.findItems();
    }

    @Override
    public void addItems(List<Item> list) {
        itemMapper.addItem(list);
    }

    @Override
    public void addRelation() {
        List<Item> items = itemMapper.findItems();
        Map<String,Integer> map = new HashMap<>();
        for (Item item:items){
            map.put(item.getName_en(),item.getItem_id());
        }
        try {
            List<String> strings = dataWormService.searchRelation();
            for (String s:strings){
                String[] split = s.split("#");
                String name = split[0];
                if (map.containsKey(name)) {
                    int itemid = map.get(name);
                    for (int i = 1; i < split.length; i++) {
                        if (map.containsKey(split[i])) {
                            int partid = map.get(split[i]);
                            itemMapper.addRelation(itemid, partid);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Item getItemById(int id) {
        return itemMapper.getItemById(id);
    }

    @Override
    public List<Item> getAllItems() {
        return itemMapper.getAllItems();
    }
}
