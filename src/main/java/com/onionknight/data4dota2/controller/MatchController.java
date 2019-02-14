package com.onionknight.data4dota2.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.ibasco.agql.protocols.valve.dota2.webapi.Dota2WebApiClient;
import com.onionknight.data4dota2.entity.*;
import com.onionknight.data4dota2.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 10:27 2019/1/19
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Controller
@RequestMapping(value = "/matches")
@CrossOrigin(origins = {"http://localhost:8081"},allowCredentials = "true")
public class MatchController {
    @Autowired
    MatchService matchService;
    @RequestMapping(value = "/overview",method = RequestMethod.GET)
    @ResponseBody
    public RespEntity findMatchOverview(@RequestParam("id") long id){
        String matchOverview = matchService.getMatchOverview(id);
        JSONObject jsonObject = JSON.parseObject(matchOverview);
        return new RespEntity(RespCode.SUCCESS,jsonObject);
    }
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    @ResponseBody
    public RespEntity findMatchDetail(@RequestParam("id") long id){
        String matchDetail = matchService.getMatchDetail(id);
        JSONArray objects = JSON.parseArray(matchDetail);
        return new RespEntity(RespCode.SUCCESS,objects);
    }
    @RequestMapping(value = "/history",method = RequestMethod.GET)
    @ResponseBody
    public RespEntity getMatchHistory(@RequestParam("acountid") long acountid,@RequestParam("matchid") long matchid){
        List<MatchHistory> matchHistories = matchService.getMatchHistories(acountid, 11, matchid);
        return new RespEntity(RespCode.SUCCESS,matchHistories);
    }
}
