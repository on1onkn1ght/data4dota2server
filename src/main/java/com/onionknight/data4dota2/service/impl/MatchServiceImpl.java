package com.onionknight.data4dota2.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ibasco.agql.protocols.valve.dota2.webapi.Dota2WebApiClient;
import com.ibasco.agql.protocols.valve.dota2.webapi.interfaces.Dota2Match;
import com.ibasco.agql.protocols.valve.dota2.webapi.pojos.*;
import com.onionknight.data4dota2.entity.*;
import com.onionknight.data4dota2.mapper.HeroMapper;
import com.onionknight.data4dota2.mapper.ItemMapper;
import com.onionknight.data4dota2.mapper.MatchMapper;
import com.onionknight.data4dota2.service.HeroService;
import com.onionknight.data4dota2.service.MatchService;
import com.onionknight.data4dota2.utils.HttpClientUtils;
import com.onionknight.data4dota2.utils.IdToNameUtil;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.io.Charsets;
import org.apache.commons.lang3.CharSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import skadistats.clarity.decoder.Util;
import skadistats.clarity.model.CombatLogEntry;
import skadistats.clarity.model.Entity;
import skadistats.clarity.model.FieldPath;
import skadistats.clarity.model.StringTable;
import skadistats.clarity.processor.entities.Entities;
import skadistats.clarity.processor.entities.UsesEntities;
import skadistats.clarity.processor.gameevents.OnCombatLogEntry;
import skadistats.clarity.processor.reader.OnTickStart;
import skadistats.clarity.processor.runner.Context;
import skadistats.clarity.processor.runner.SimpleRunner;
import skadistats.clarity.processor.stringtables.StringTables;
import skadistats.clarity.processor.stringtables.UsesStringTable;
import skadistats.clarity.source.InputStreamSource;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author :fdy
 * @Date :Created in 10:31 2019/1/18
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Service(value = "matchService")
public class MatchServiceImpl implements MatchService {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    Dota2WebApiClient client;
    @Autowired
    HeroMapper heroMapper;
    @Autowired
    ItemMapper itemMapper;
    private HttpClientUtils httpClientUtils = HttpClientUtils.getInstance();
    @Autowired
    private MatchMapper matchMapper;
    private Match match = new Match();
    private InputStream is = null;
    private FileWriter os = null;
    private boolean init = false;
    private int numPlayers = 10;
    private int[] validIndices = new int[numPlayers];
    private int time = 0;
    private int lasttime = 0;
    private int gameStartTime = 0;
    private HashMap<Integer, Integer> slot_to_playerslot = new HashMap<Integer, Integer>();
    @Autowired
    private IdToNameUtil idToNameUtil;
    @Override
    public String getMatchDetail(long id) {
        MatchJson match = matchMapper.getMatchUrl(id);
        if (match==null){
            String matchOverview = getMatchOverview(id);
            JSONObject jsonObject = JSON.parseObject(matchOverview);
            String replyUrl = jsonObject.getString("replyUrl");
            match = new MatchJson();
            match.setReplay_url(replyUrl);
        }
        if (match.getDetail() != null)
            return new String(match.getDetail(),Charsets.UTF_8);
        try {
            String filePath = "src/main/resources/replaytemp/"+ id + ".dem.bz2";
            httpClientUtils.download(match.getReplay_url(),filePath);
            File file = new File(filePath);
            FileInputStream is = new FileInputStream(file);
            BZip2CompressorInputStream bzis =
                    new BZip2CompressorInputStream(is);

            long tStart = System.currentTimeMillis();
            new SimpleRunner(new InputStreamSource(bzis)).runWith(this);
            long tMatch = System.currentTimeMillis() - tStart;
            System.out.println(tMatch);
            String matchans = JSON.toJSONString(this.match.getMatchDetail());
            matchMapper.addMatchDetail(id,matchans.getBytes());
            return matchans;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getMatchOverview(long id) {
        HashMap<Integer, String> itemIdToName = idToNameUtil.getItemIdToName();
        HashMap<Integer, String> heroIdToEnName = idToNameUtil.getHeroIdToEnName();
        MatchJson matchOverview1 = matchMapper.getMatchOverview(id);
        if (matchOverview1!=null) {
            return new String(matchOverview1.getOverview(),Charsets.UTF_8);
        }
        String url = "https://api.opendota.com/api/matches/" + id;
        MatchOverview matchOverview = new MatchOverview();
        String matchOverviewString = httpClientUtils.httpGet(url);
        JSONObject match = JSON.parseObject(matchOverviewString);
        matchOverview.setDuration(match.getInteger("duration"));
        matchOverview.setStarttime(match.getInteger("start_time"));
        matchOverview.setIsradiantWin(match.getBoolean("radiant_win"));
        final MatchOverview.Player[] players = matchOverview.getPlayers();
        JSONArray players1 = match.getJSONArray("players");
        for (int i = 0; i < players.length; i++) {
            JSONObject players1JSONObject = players1.getJSONObject(i);
            MatchOverview.Player player = matchOverview.new Player();
            player.setSlot(players1JSONObject.getInteger("player_slot"));
            player.setPlayerId(players1JSONObject.getLong("account_id"));
            player.setHeroname(heroIdToEnName.get(players1JSONObject.getInteger("hero_id")));
            player.setKills(players1JSONObject.getInteger("kills"));
            player.setDies(players1JSONObject.getInteger("deaths"));
            player.setAssists(players1JSONObject.getInteger("assists"));
            player.setGold(players1JSONObject.getInteger("gold"));
            player.setGpm(players1JSONObject.getInteger("gold_per_min"));
            player.setXpm(players1JSONObject.getInteger("xp_per_min"));
            player.setHeroDamage(players1JSONObject.getInteger("hero_damage"));
            player.setTowerDamage(players1JSONObject.getInteger("tower_damage"));
            player.setHeadling(players1JSONObject.getInteger("hero_healing"));
            player.setLevel(players1JSONObject.getInteger("level"));
            player.setName(players1JSONObject.getString("personaname"));
            final String[] items = player.getItems();
            for (int j = 0; j < 6; j++) {
                items[j] = itemIdToName.get(players1JSONObject.getInteger("item_" + j));
            }
            for (int j = 6; j < 9; j++) {
                items[j] = itemIdToName.get(players1JSONObject.getInteger("backpack_" +(j-6)));
            }
            players[i] = player;
        }
        matchOverview.setReplyUrl(match.getString("replay_url"));
        String ans = JSON.toJSONString(matchOverview);
        matchMapper.addMatchOverview(id, ans.getBytes(),matchOverview.getReplyUrl());
        return ans;
    }

    @Override
    public List<MatchHistory> getMatchHistories(long acountId, int num,long matchId) {
        HashMap<Integer, String> heroIdToCnName = idToNameUtil.getHeroIdToCnName();
        Dota2Match dota2Match = new Dota2Match(client);
        Dota2MatchHistoryCriteria criteria = new Dota2MatchHistoryCriteria();
        criteria.accountId(acountId);
        criteria.matchesRequested(num);
        if (matchId!=0L){
            criteria.startAtMatchId(matchId);
        }
        try {
            Dota2MatchHistory dota2MatchHistory = dota2Match.getMatchHistory(criteria).get();
            List<Dota2MatchHistoryInfo> matches = dota2MatchHistory.getMatches();
            List<MatchHistory> matchHistories = new ArrayList<>();
            for (Dota2MatchHistoryInfo match : matches) {
                MatchHistory matchHistory = new MatchHistory();
                matchHistory.setMatchId(match.getMatchId());
                SimpleDateFormat dataFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm");
                int startTime = match.getStartTime();
                long start = (long)startTime *1000;
                Date date = new Date(start);
                matchHistory.setTime(dataFormat.format(date));
                List<Dota2MatchHistoryPlayer> players = match.getPlayers();
                for (Dota2MatchHistoryPlayer player : players) {
                    if (player.getAccountId()==acountId){
                        matchHistory.setHeroName(heroIdToCnName.get(player.getHeroId()));
                        matchHistory.setCamp(player.getPlayerSlot()<120? "天辉":"夜魇");
                    }
                }
                matchHistories.add(matchHistory);
            }
            return matchHistories;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


//    @OnMessage(S2DotaGcCommon.CMsgDOTAMatch.class)
//    private void onDotaMatch(Context ctx, S2DotaGcCommon.CMsgDOTAMatch message) throws Exception {
//        MatchOverview matchOverview = new MatchOverview();
//        matchOverview.setDuration(message.getDuration());
//        matchOverview.setStarttime(message.getStartTime());
//        matchOverview.setOutcome(message.getMatchOutcome().getNumber());
//        final MatchOverview.Player[] players = matchOverview.getPlayers();
//        for (int i=0;i<players.length;i++){
//            final S2DotaGcCommon.CMsgDOTAMatch.Player splayer = message.getPlayers(i);
//            MatchOverview.Player player = matchOverview.new Player();
//            player.setSlot(splayer.getPlayerSlot());
//            player.setPlayerId((long)splayer.getAccountId());
//            player.setHeroId(splayer.getHeroId());
//            player.setKills(splayer.getKills());
//            player.setDies(splayer.getDeaths());
//            player.setAssists(splayer.getAssists());
//            player.setGold(splayer.getGold());
//            player.setGpm(splayer.getGoldPerMin());
//            player.setXpm(splayer.getXPPerMin());
//            player.setHeroDamage(splayer.getHeroDamage());
//            player.setTowerDamage(splayer.getTowerDamage());
//            player.setHeadling(splayer.getHeroHealing());
//            player.setLevel(splayer.getLevel());
//            player.setName(splayer.getPlayerName());
//            player.setPickOrder(splayer.getHeroPickOrder());
//            final Integer[] items = player.getItems();
//            for (int j=0;j<items.length;j++){
//                Method method = splayer.getClass().getMethod("getItem" + j);
//                final Integer invoke = (Integer) method.invoke(splayer);
//                items[j] = invoke;
//            }
//            players[i] = player;
//        }
//        match.setMatchOverview(matchOverview);
//    }

    @UsesStringTable("EntityNames")
    @UsesEntities
    @OnTickStart
    private void onTickStart(Context ctx, boolean synthetic) {
        Entity grp = ctx.getProcessor(Entities.class).getByDtName("CDOTAGamerulesProxy");
        Entity pr = ctx.getProcessor(Entities.class).getByDtName("CDOTA_PlayerResource");
        Entity dData = ctx.getProcessor(Entities.class).getByDtName("CDOTA_DataDire");
        Entity rData = ctx.getProcessor(Entities.class).getByDtName("CDOTA_DataRadiant");
        MatchSnap ms = new MatchSnap();

        if (grp != null)
            time = Math.round((float) getEntityProperty(grp, "m_pGameRules.m_fGameTime", null));
        if (gameStartTime != 0 && time != lasttime) {
            ms.setTime(time - gameStartTime);
            if (pr != null) {
                if (!init) {
                    int added = 0;
                    int i = 0;
                    //according to @Decoud Valve seems to have fixed this issue and players should be in first 10 slots again
                    //sanity check of i to prevent infinite loop when <10 players?
                    while (added < numPlayers && i < 100) {
                        try {
                            //check each m_vecPlayerData to ensure the player's team is radiant or dire
                            int playerTeam = getEntityProperty(pr, "m_vecPlayerData.%i.m_iPlayerTeam", i);
                            int teamSlot = getEntityProperty(pr, "m_vecPlayerTeamData.%i.m_iTeamSlot", i);
                            //System.err.format("%s %s %s: %s\n", i, playerTeam, teamSlot, steamid);
                            if (playerTeam == 2 || playerTeam == 3) {
                                //output the player_slot based on team and teamslot
                                int slot = (playerTeam == 2 ? 0 : 128) + teamSlot;
                                //add it to validIndices, add 1 to added
                                validIndices[added] = i;
                                added += 1;
                                slot_to_playerslot.put(added, slot);
                            }
                        } catch (Exception e) {
                            //swallow the exception when an unexpected number of players (!=10)
                            //System.err.println(e);
                        }

                        i += 1;
                    }
                    init = true;
                }
                for (int i = 0; i < numPlayers; i++) {
                    HashMap<Integer, String> heroIdToEnName = idToNameUtil.getHeroIdToEnName();
                    int handle = getEntityProperty(pr, "m_vecPlayerTeamData.%i.m_hSelectedHero", validIndices[i]);
                    int playerTeam = getEntityProperty(pr, "m_vecPlayerData.%i.m_iPlayerTeam", validIndices[i]);
                    int teamSlot = getEntityProperty(pr, "m_vecPlayerTeamData.%i.m_iTeamSlot", validIndices[i]);
                    Entity dataTeam = playerTeam == 2 ? rData : dData;
                    MatchSnap.HeroState hero = ms.new HeroState();
                    ms.getHeros().add(hero);
                    Integer heroId = getEntityProperty(pr, "m_vecPlayerTeamData.%i.m_nSelectedHeroID", validIndices[i]);
                    hero.setHeroname(heroIdToEnName.get(heroId));
                    hero.setLevel(getEntityProperty(pr, "m_vecPlayerTeamData.%i.m_iLevel", validIndices[i]));
                    hero.setKilles(getEntityProperty(pr, "m_vecPlayerTeamData.%i.m_iKills", validIndices[i]));
                    hero.setDies(getEntityProperty(pr, "m_vecPlayerTeamData.%i.m_iDeaths", validIndices[i]));
                    hero.setAssists(getEntityProperty(pr, "m_vecPlayerTeamData.%i.m_iAssists", validIndices[i]));
                    hero.setDenies(getEntityProperty(dataTeam, "m_vecDataTeam.%i.m_iDenyCount", teamSlot));
                    hero.setWard(getEntityProperty(dataTeam, "m_vecDataTeam.%i.m_iObserverWardsPlaced", teamSlot));
                    hero.setSentry(getEntityProperty(dataTeam, "m_vecDataTeam.%i.m_iSentryWardsPlaced", teamSlot));
                    hero.setRunePick(getEntityProperty(dataTeam, "m_vecDataTeam.%i.m_iRunePickups", teamSlot));
                    ms.setRoshans(getEntityProperty(dataTeam, "m_vecDataTeam.%i.m_iRoshanKills", teamSlot));
                    if (teamSlot >= 0) {
                        hero.setGold(getEntityProperty(dataTeam, "m_vecDataTeam.%i.m_iTotalEarnedGold", teamSlot));
                        hero.setLastHit(getEntityProperty(dataTeam, "m_vecDataTeam.%i.m_iLastHitCount", teamSlot));
                        hero.setXp(getEntityProperty(dataTeam, "m_vecDataTeam.%i.m_iTotalEarnedXP", teamSlot));
                    }
                    Entity e = ctx.getProcessor(Entities.class).getByHandle(handle);
                    if (e != null) {
                        //System.err.println(e);
                        hero.setPosition_x(getEntityProperty(e, "CBodyComponent.m_cellX", null));
                        hero.setPosition_y(getEntityProperty(e, "CBodyComponent.m_cellY", null));
                        //System.err.format("%s, %s\n", entry.x, entry.y);
                        //get the hero's entity name, ex: CDOTA_Hero_Zuus
                        hero.setLifeState(getEntityProperty(e, "m_lifeState", null));
                        List<String> heroInventory = getHeroInventory(ctx, e);
                        hero.setItems(heroInventory);
                    }
                }
                lasttime = time;
            }
            match.getMatchDetail().add(ms);
        }
    }

    private List<String> getHeroInventory(Context ctx, Entity eHero) {
        List<String> inventoryList = new ArrayList<>(6);

        for (int i = 0; i < 9; i++) {
            String item = getHeroItem(ctx, eHero, i);
            if (item != null) {
                inventoryList.add(item);
            }
        }

        return inventoryList;
    }

    private String getHeroItem(Context ctx, Entity eHero, int idx) {
        StringTable stEntityNames = ctx.getProcessor(StringTables.class).forName("EntityNames");
        Entities entities = ctx.getProcessor(Entities.class);

        Integer hItem = eHero.getProperty("m_hItems." + Util.arrayIdxToString(idx));
        if (hItem == 0xFFFFFF) {
            return null;
        }
        Entity eItem = entities.getByHandle(hItem);
        if (eItem!=null) {
            int index = eItem.getProperty("m_pEntity.m_nameStringableIndex");
            String itemName = stEntityNames.getNameByIndex(index);
            itemName = itemName.substring(itemName.indexOf("_") + 1);


            int numCharges = eItem.getProperty("m_iCurrentCharges");
            return itemName;
        }
        return null;
    }

    @OnCombatLogEntry
    private void onCombatLogEntry(Context ctx, CombatLogEntry cle) {
        try {
            if (cle.getType().name().equals("DOTA_COMBATLOG_GAME_STATE") && cle.getValue() == 5) {
                //alternate to combat log for getting game zero time (looks like this is set at the same time as the game start, so it's not any better for streaming)
                // int currGameStartTime = Math.round( (float) grp.getProperty("m_pGameRules.m_flGameStartTime"));
                if (gameStartTime == 0) {
                    gameStartTime = time;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private <T> T getEntityProperty(Entity e, String property, Integer idx) {
        try {
            if (e == null) {
                return null;
            }
            if (idx != null) {
                property = property.replace("%i", Util.arrayIdxToString(idx));
            }
            FieldPath fp = e.getDtClass().getFieldPathForName(property);
            return e.getPropertyForFieldPath(fp);
        } catch (Exception ex) {
            return null;
        }
    }

}
