package com.onionknight.data4dota2.service.impl;

import com.onionknight.data4dota2.entity.Hero;
import com.onionknight.data4dota2.entity.Item;
import com.onionknight.data4dota2.entity.Skill;
import com.onionknight.data4dota2.mapper.HeroMapper;
import com.onionknight.data4dota2.service.DataWormService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Author :fdy
 * @Date :Created in 13:53 2019/1/7
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Service(value = "dataWormService")
public class DataWormServiceImpl implements DataWormService {
    @Autowired
    HeroMapper heroMapper;
    public List<String> searchHeroUrl()throws IOException{
        String url = "https://www.dota2.com.cn/heroes/index.htm";
        List<String> heroUrls = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Element body = doc.body();
        Elements heroPickerIconLink = body.getElementsByClass("heroPickerIconLink");
        for (Element heroUrl:heroPickerIconLink) {
            String href = heroUrl.attr("href");
            heroUrls.add(href);
        }
        return heroUrls;
    }
    public List<String> searchItemUrl()throws IOException{
        String url = "http://www.dotamax.com/item/";
        List<String> itemUrl = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Element body = doc.body();
        Element table = body.getElementsByTag("table").get(0);
        Elements trs = table.getElementsByTag("tr");
        for (Element tr:trs){
            String item = tr.attr("onclick");
            if (item==null||item.length()==0) continue;
            item = item.substring(item.lastIndexOf("/"), item.lastIndexOf("'"));
            item = url+"detail"+item;
            itemUrl.add(item);
        }
        itemUrl.remove(itemUrl.size()-1);
        itemUrl.remove("http://www.dotamax.com/item/detail/pocket_roshan");
        return itemUrl;
    }
    public static void main(String[] args)throws Exception {

    }
    @Override
    public void downloadIcon()throws Exception{
        List<Hero> allHeroName = heroMapper.findAllHeroName();
        for (Hero hero : allHeroName) {
            String url = "https://api.opendota.com/apps/dota2/images/heroes/"+ hero.getName_en()+"_icon.png";
            downImage("D:/java_program/data4dota2/src/main/resources/static/images/heroicon/",url);
        }
    }
    public List<Item> searchItems()throws Exception{
        List<String> itemUrls = searchItemUrl();
        List<Item> Items = new ArrayList<>();
        List<Callable<Item>> heroCalls = new ArrayList<>();
        for (String itemUrl:itemUrls){
            Callable<Item> itemCallable = new Callable<Item>() {
                @Override
                public Item call() throws Exception {
                    return searchItem(itemUrl);
                }
            };
            heroCalls.add(itemCallable);
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Item>> futures = executorService.invokeAll(heroCalls);
        for (Future<Item> future:futures){
            Items.add(future.get());
        }
        return Items;
    }

    public Item searchItem(String itemUrl)throws Exception {
        Document doc = Jsoup.connect(itemUrl).get();
        Element body = doc.body();
        Item item = new Item();
        String imgUrl = body.getElementsByClass("iconTooltip_img").
                get(0).getElementsByTag("img").get(0).attr("src");
        String nameEn = imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.lastIndexOf("_"));
        item.setName_en(nameEn);
        String path = "images/items/"+nameEn+".png";
        downImage(path,imgUrl);
        String nameCn = body.getElementsByClass("items_tit").get(0).text();
        item.setName_cn(nameCn);
        System.out.println(nameCn);
        String price = body.getElementsByClass("price").text();
        item.setPrice(Integer.valueOf(price));
        Element element = body.getElementsByClass("items_txt").get(0);
        String[] s = element.text().substring(0,element.text().lastIndexOf("。")+1).split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<s.length;i++){
            if (i!=0&&!s[i-1].equals("+"))
                sb.append("#");
            sb.append(s[i]);
        }
        item.setDescription(sb.toString());
        Map<String,String> map = new HashMap<>();
        Elements attribVal = body.getElementsByClass("attribVal");
        Elements attribValText = body.getElementsByClass("attribValText");
        for (int i=0;i<attribVal.size();i++){
            map.put(attribValText.text(),attribVal.text());
        }
        for (String attri:map.keySet()){
            if (attri.equals("攻击力"))
                item.setAttack_power(Double.valueOf(map.get(attri).replace("%","")));
            else if (attri.equals("攻击速度"))
                item.setAttack_speed(Double.valueOf(map.get(attri).replace("%","")));
            else if (attri.equals("力量"))
                item.setStr(Double.valueOf(map.get(attri).replace("%","")));
            else if (attri.equals("智力"))
                item.setIntl(Double.valueOf(map.get(attri).replace("%","")
                .substring(map.get(attri).replace("%","").lastIndexOf(" ")+1)));
            else if (attri.equals("敏捷"))
                item.setAgi(Double.valueOf(map.get(attri).replace("%","")));
            else if (attri.equals("生命值"))
                item.setHp(Integer.valueOf(map.get(attri).replace("%","")));
            else if (attri.equals("魔法值"))
                item.setMp(Integer.valueOf(map.get(attri).replace("%","")));
            else if (attri.equals("移动速度"))
                item.setMove_speed(Integer.valueOf(map.get(attri).replace("%","")));
            else if (attri.equals("魔法抗性"))
                item.setMagic_resis(Integer.valueOf(map.get(attri).replace("%","")));
            else if (attri.equals("全属性")) {
                item.setStr(Double.valueOf(map.get(attri).replace("%","")));
                item.setAgi(Double.valueOf(map.get(attri).replace("%","")));
                item.setIntl(Double.valueOf(map.get(attri).replace("%","")));
            }
            else if (attri.equals("护甲"))
                item.setArmor(Double.valueOf(map.get(attri).replace("%","")));
        }


        Element itemsP5 = body.getElementsByClass("items_p5").get(0);
        String cd = itemsP5.getElementsByClass("ico2").text();
        String cost = itemsP5.getElementsByClass("ico1").text();
        if (cd!=null&&cd.length()!=0)
            item.setCd(Double.valueOf(cd));
        if (cost!=null&&cost.length()!=0)
            item.setCost(Integer.valueOf(cost));
        return item;
    }
    public Hero searchTalent(String heroUrl)throws IOException{
        Document doc = Jsoup.connect(heroUrl).get();
        Element body = doc.body();
        Hero hero = new Hero();
        String[] names = body.getElementsByClass("top_hero_card").
                get(0).getElementsByTag("p").text().split(" ");
        hero.setName_cn(names[0]);
        hero.setName_en(names[1]);
        StringBuilder talent = new StringBuilder();
        Elements talentBoxs = body.getElementsByClass("talent_box")
                .get(0).getElementsByTag("li");
        for (Element talentBox:talentBoxs){
            talent.append(talentBox.getElementsByClass("talent-explain").get(0).text());
            talent.append("#");
            talent.append(talentBox.getElementsByClass("talent-explain").get(1).text());
            talent.append("#");
        }
        hero.setTalents(talent.toString().substring(0,talent.length()-1));
        return hero;
    }
    public List<Hero> searchTalents() throws Exception{
        List<String> heroUrls = searchHeroUrl();
        List<Hero> heroes = new ArrayList<>();
        List<Callable<Hero>> heroCalls = new ArrayList<>();
        for (String heroUrl:heroUrls){
            Callable<Hero> heroCallable = new Callable<Hero>() {
                @Override
                public Hero call() throws Exception {
                    return searchTalent(heroUrl);
                }
            };
            heroCalls.add(heroCallable);
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Hero>> futures = executorService.invokeAll(heroCalls);
        for (Future<Hero> future:futures){
            heroes.add(future.get());
        }
        return heroes;
    }
    @Override
    public List<Skill> searchSkills() throws Exception {
        List<String> heroUrls = searchHeroUrl();
        List<Skill> heroes = new ArrayList<>();
        List<Callable<Skill>> heroCalls = new ArrayList<>();
        for (String heroUrl:heroUrls){
            Callable<Skill> heroCallable = new Callable<Skill>() {
                @Override
                public Skill call() throws Exception {
                    return searchSkill(heroUrl);
                }
            };
            heroCalls.add(heroCallable);
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Skill>> futures = executorService.invokeAll(heroCalls);
        for (Future<Skill> future:futures){
            heroes.add(future.get());
        }
        return heroes;
    }

    private Skill searchSkill(String heroUrl) throws Exception {
        Skill skill = new Skill();
        Document doc = Jsoup.connect(heroUrl).get();
        Element body = doc.body();
        return skill;
    }

    @Override
    public Hero searchHero(String heroUrl) throws IOException{
        Document doc = Jsoup.connect(heroUrl).get();
        Element body = doc.body();
        Hero hero = new Hero();
        String[] names = body.getElementsByClass("top_hero_card").
                get(0).getElementsByTag("p").text().split(" ");
        hero.setName_cn(names[0]);
        hero.setName_en(names[1]);
        hero.setIcon_hp("images/heroes/"+names[1]+"_hphover.png");
        hero.setIcon_sb("images/heroes/"+names[1]+"_sb.png");
        hero.setAttack_type(body.getElementsByClass("info_p").get(0).text());
        String roles = body.getElementById("lastEm").text();
        roles = roles.substring(0,roles.length()-1);
        hero.setRoles(roles);
        hero.setCamp(body.getElementsByClass("zhengying_p").get(0).text());

        //属性
        Elements properties = body.getElementsByClass("pro6_box").get(0).getElementsByTag("li");
        Element str = properties.get(0);
        String[] str_num = str.text().split(" ");
        hero.setInit_str(Double.valueOf(str_num[0]));
        hero.setLevel_str(Double.valueOf(str_num[2]));
        String attri = str.getElementsByClass("pop_property_t").get(0).text();
        if (attri.contains("主要属性"))
            hero.setPrimary_attr("力量");


        Element agi = properties.get(1);
        String[] agi_num = agi.text().split(" ");
        hero.setInit_agi(Double.valueOf(agi_num[0]));
        hero.setLevel_agi(Double.valueOf(agi_num[2]));
        attri = agi.getElementsByClass("pop_property_t").get(0).text();
        if (attri.contains("主要属性"))
            hero.setPrimary_attr("敏捷");

        Element intl = properties.get(2);
        String[] int_num = intl.text().split(" ");
        hero.setInit_intl(Double.valueOf(int_num[0]));
        hero.setLevel_int(Double.valueOf(int_num[2]));
        attri = intl.getElementsByClass("pop_property_t").get(0).text();
        if (attri.contains("主要属性"))
            hero.setPrimary_attr("智力");

        Element attack = properties.get(3);
        String[] attck_num = attack.text().split(" ");
        hero.setInit_attack_power(Double.valueOf(attck_num[0]));
        String speed = attck_num[3];
        speed = speed.substring(speed.indexOf("：")+1,speed.indexOf("（"));
        hero.setInit_attack_speed(Double.valueOf(speed));

        String arrange = attck_num[9];
        arrange = arrange.substring(arrange.indexOf("：")+1);
        hero.setInit_attack_arrange(Integer.valueOf(arrange));

        Element arm = properties.get(4);
        String armnum = arm.text().split(" ")[0];
        hero.setInit_armor(Double.valueOf(armnum));


        Element move = properties.get(5);
        String movespeed = move.text().split(" ")[0];
        hero.setInit_move_speed(Integer.valueOf(movespeed));

        Element flash_data = body.getElementById("flash_data");
        hero.setInit_hp(Integer.valueOf(flash_data.attr("smz")
                .split("/")[0]));
        hero.setInit_mp(Integer.valueOf(flash_data.attr("mfz")
                .split("/")[0]));

        Element area_box = body.getElementsByClass("area_box").get(0);
        Elements tr = area_box.getElementsByTag("tr");
        String[] tds = tr.get(0).getElementsByTag("td").get(1).text().split("/");
        hero.setInit_vision_field_day(Integer.valueOf(tds[0].trim()));
        hero.setInit_vision_field_night(Integer.valueOf(tds[1].trim()));

        String ball = tr.get(1).getElementsByTag("td").get(1).text();
        if(ball.equals("N/A"))
            hero.setBall_speed(0);
        else
            hero.setBall_speed(Integer.valueOf(ball));

        StringBuilder talent = new StringBuilder();
        Elements talentBoxs = body.getElementsByClass("talent_box")
                .get(0).getElementsByTag("li");
        for (Element talentBox:talentBoxs){
            talent.append(talentBox.getElementsByClass("talent-explain").get(0).text());
            talent.append("|");
            talent.append(talentBox.getElementsByClass("talent-explain").get(1).text());
            talent.append("#");
        }
        hero.setTalents(talent.toString().substring(0,talent.length()-1));

        //skills
        Elements skill_wrap = body.getElementsByClass("skill_wrap");
        List<Skill> skills = new ArrayList<>();
        for (int i=0;i<skill_wrap.size();i++) {
            Skill skill = new Skill();
            Element skillWrap = skill_wrap.get(i);
            String src = skillWrap.getElementsByClass("skill_b")
                    .get(0).attr("abs:src");
            String skillname = src.substring(src.indexOf("_")+1,src.lastIndexOf("_")) ;
            downImage("D:/java_program/data4dota2/src/main/resources/static/images/skills/",src);
            skill.setName_en(skillname);
            Element skillInfo = skillWrap.getElementsByClass("skill_info").get(0);
            Elements ps = skillInfo.getElementsByTag("p");
            String pstext = ps.get(0).text();
            if (pstext==null||pstext.length()==0)
                continue;
            skill.setName_cn(pstext.substring(0,pstext.indexOf(" ")));
            skill.setDescription(pstext.substring(pstext.indexOf(" ")+1));
            if (ps.size()>1)
                skill.setDetails(ps.get(1).text());
            String mpCost = skillInfo.getElementsByClass("icon_xh").get(0).text();
            skill.setMp_cost(mpCost.substring(mpCost.indexOf("：")+1));
            String cd = skillInfo.getElementsByClass("icon_lq").get(0).text();
            skill.setCd(mpCost.substring(mpCost.indexOf("：")+1));
            Elements skillAttris = skillWrap.getElementsByClass("skill_ul").
                    get(0).getElementsByTag("li");
            StringBuilder skillSb = new StringBuilder();
            for (Element skillAttri:skillAttris){
                skillSb.append("#");
                skillSb.append(skillAttri.text());
            }
            skill.setAttributes(skillSb.toString().substring(1));
            skills.add(skill);
        }
        hero.setSkills(skills);
        return hero;
    }
    public List<Hero> searchHeroes() throws IOException, InterruptedException, ExecutionException {
        List<String> heroUrls = searchHeroUrl();
        List<Hero> heroes = new ArrayList<>();
        List<Callable<Hero>> heroCalls = new ArrayList<>();
        for (String heroUrl:heroUrls){
            Callable<Hero> heroCallable = new Callable<Hero>() {
                @Override
                public Hero call() throws Exception {
                    return searchHero(heroUrl);
                }
            };
            heroCalls.add(heroCallable);
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Hero>> futures = executorService.invokeAll(heroCalls);
        for (Future<Hero> future:futures){
            heroes.add(future.get());
        }
        return heroes;
    }
    public List<Hero> searchHeroesOneByOne() throws IOException{
        List<String> heroUrls = searchHeroUrl();
        List<Hero> heroes = new ArrayList<>();
        for (String heroUrl:heroUrls){
            heroes.add(searchHero(heroUrl));
        }
        return heroes;
    }
    public void dowmloadSkillIcon(String heroUrl)throws Exception{
        Document doc = Jsoup.connect(heroUrl).get();
        Element body = doc.body();
        Element dt = body.getElementById("focus_dl").getElementsByTag("dt").get(0);
        Elements imgs = dt.getElementsByTag("img");
        for (Element img:imgs){
            String url = img.attr("abs:src");
            downImage("D:\\skills\\",url);
        }
    }
    public void downImage(String filePath,String imgUrl) {
        try {

            //图片url中的前面部分：例如"http://images.csdn.net/"
            String beforeUrl = imgUrl.substring(0,imgUrl.lastIndexOf("/")+1);
            //图片url中的后面部分：例如“20150529/PP6A7429_副本1.jpg”
            String fileName = imgUrl.substring(imgUrl.lastIndexOf("/")+1);
            //编码之后的fileName，空格会变成字符"+"
            String newFileName = URLEncoder.encode(fileName, "UTF-8");
            //把编码之后的fileName中的字符"+"，替换为UTF-8中的空格表示："%20"
            newFileName = newFileName.replaceAll("\\+", "\\%20");
            //编码之后的url
            imgUrl = beforeUrl + newFileName;
            URL url = new URL(imgUrl);
            //链接网络地址
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            //获取链接的输出流
            InputStream is = connection.getInputStream();
            //创建文件，fileName为编码之前的文件名
            filePath = filePath + fileName;
            File file = new File(filePath);
            //根据输入流写入文件
            FileOutputStream out = new FileOutputStream(file);
            int i = 0;
            while((i = is.read()) != -1){
                out.write(i);
            }
            out.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> searchRelation() throws Exception {
        List<String> strings = new ArrayList<>();
        List<String> urls = searchItemUrl();
        for (String url:urls){
            String s = searcbRela(url);
            if (s!=null)
                strings.add(s);
        }
        return strings;
    }
    private String searcbRela(String url ) throws Exception{
        Document doc = Jsoup.connect(url).get();
        Element body = doc.body();
        StringBuilder sb = new StringBuilder();
        String name = url.substring(url.lastIndexOf("/")+1);
        sb.append(name);
        Element element = body.getElementsByClass("iconTooltip").get(0);
        if (element.text().contains("合成需要")){
            Elements as = element.getElementsByTag("a");
            for (Element a:as){
                String href = a.attr("href");
                sb.append("#");
                sb.append(href.substring(href.lastIndexOf("/")+1));
            }
            System.out.println(sb.toString());
            return sb.toString();
        }
        return null;
    }

}
