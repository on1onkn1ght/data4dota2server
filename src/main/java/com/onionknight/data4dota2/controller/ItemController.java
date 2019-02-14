package com.onionknight.data4dota2.controller;

import com.onionknight.data4dota2.entity.Item;
import com.onionknight.data4dota2.entity.RespCode;
import com.onionknight.data4dota2.entity.RespEntity;
import com.onionknight.data4dota2.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 14:57 2019/1/14
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Controller
@RequestMapping(value = "/items")
@CrossOrigin(origins = {"http://localhost:8081"},allowCredentials = "true")
public class ItemController {
    @Autowired
    ItemService itemService;
    @RequestMapping(value = "/allIcon",method = RequestMethod.GET)
    @ResponseBody
    public RespEntity findAllItemIcon(){
        List<Item> items = itemService.findItems();
        return new RespEntity(RespCode.SUCCESS,items);
    }
    @RequestMapping(value = "/item",method = RequestMethod.GET)
    @ResponseBody
    public RespEntity findItemById(@RequestParam("id") int id){
        Item item = itemService.getItemById(id);
        return new RespEntity(RespCode.SUCCESS,item);
    }
    @RequestMapping(value = "/allitems",method = RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    public RespEntity findAllItems(){
        List<Item> items = itemService.getAllItems();
        return new RespEntity(RespCode.SUCCESS,items);
    }
}
