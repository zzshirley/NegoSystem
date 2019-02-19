package org.ccnu.nercel.controller;

import org.ccnu.nercel.Util.LogUtils;
import org.ccnu.nercel.bean.*;
import org.ccnu.nercel.config.ObjectUtils;
import org.ccnu.nercel.config.WebSecurityConfig;
import org.ccnu.nercel.service.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.lang.reflect.Field;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.Exception;

import static java.awt.SystemColor.activeCaption;
import static java.awt.SystemColor.info;


/**
 * 试卷
 * @author xiaotong
 * @version 2018年9月2日 下午10:32:13
 */

@RestController
public class PaperCotroller {

    public static int  optionnum=5;

    @Autowired
    private PaperService paperService;

    @Autowired
    private PaperQuesOptService paperQuesOptService;
    
    @Autowired
    private DoPaperService dopaperservice;

    @Autowired
    private NegoService negoService;


    @Autowired
    private AbilityService abilityService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private SelfevaService selfevaService;

    @Autowired
    private AbilityNewService abilityNewService;

    public String paperid;
    public String time;

    @RequestMapping("/readingtest")
    public ModelAndView getpaper(@RequestParam String times,Model model,HttpSession session){
        Object userid;
        userid=session.getAttribute(WebSecurityConfig.SESSION_KEY);
        Paper paper = new Paper();

        Logger loggetpaper = LogUtils.getDBLogger();

        time=times;
        paperid=TimetoPaperid(times);
        paper.setPaperid(paperid);

        List<Selfeva> selfevaList=selfevaService.getSelfEvaBytime((String)userid,times);
        List<DoPaper> doPapers=resultService.getpaperAndpaperid((String)userid,paperid);

        List<PaperQuesOption> num1options=new ArrayList<>();//第一道题选项

        List<PaperQuesOption> num2options=new ArrayList<>();//第二道题选项

        List<PaperQuesOption> num3options=new ArrayList<>();//第三道题

        if(selfevaList.isEmpty()){
            model.addAttribute("selfirst","true");
            ModelAndView mv = new ModelAndView("testindex");
            return mv;
        }
        if(doPapers.isEmpty()){

            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            session.setAttribute(WebSecurityConfig.BEGINTIME_KEY,df.format(System.currentTimeMillis()));//开始时间
            ArrayList<String> quesnum= new ArrayList<>();//获取试卷的三个问题编
            ArrayList questions=new ArrayList();
            ArrayList rdmnum=new ArrayList();

            List<paperQues> paperQuesList = paperService.paperQues(paper.getPaperid());//查找文章的对应的问题

            for(paperQues queslist:paperQuesList){

                quesnum.add(queslist.getQuestion());//问题编号（三个面向）
                questions.add(queslist.getQuescontent());//具体问题内容
                rdmnum.add(queslist.getRdm());

            }
            System.out.println(questions+"\n");

            model.addAttribute("papers",questions);

            List<PaperQuesOption> paperQuesOptionList11 =paperQuesOptService.quesOptions(quesnum.get(0),"1");//正确选项
            List<PaperQuesOption> paperQuesOptionList21 =paperQuesOptService.quesOptions(quesnum.get(1),"1");
            List<PaperQuesOption> paperQuesOptionList31 =paperQuesOptService.quesOptions(quesnum.get(2),"1");

            List<PaperQuesOption> paperQuesOptionList10 =paperQuesOptService.quesOptions(quesnum.get(0),"0");//错误选项
            List<PaperQuesOption> paperQuesOptionList20=paperQuesOptService.quesOptions(quesnum.get(1),"0");
            List<PaperQuesOption> paperQuesOptionList30 =paperQuesOptService.quesOptions(quesnum.get(2),"0");

            if(rdmnum.get(0).equals(2)){
                num1options=findoption2(paperQuesOptionList11,paperQuesOptionList10,2);
            }else if(rdmnum.get(0).equals(3)){
                num1options=findoption2(paperQuesOptionList11,paperQuesOptionList10,3);
            }else if(rdmnum.get(0).equals(23)){
                num1options=findoption(paperQuesOptionList11,paperQuesOptionList10,2);
            }else if(rdmnum.get(0).equals(12)){
                num1options=findoption(paperQuesOptionList11,paperQuesOptionList10,1);
            }
            if(rdmnum.get(1).equals(2)){
                num2options=findoption2(paperQuesOptionList21,paperQuesOptionList20,2);
            }else if(rdmnum.get(1).equals(3)){
                num2options=findoption2(paperQuesOptionList21,paperQuesOptionList20,3);
            }else if(rdmnum.get(1).equals(23)){
                num2options=findoption(paperQuesOptionList21,paperQuesOptionList20,2);
            }else if(rdmnum.get(1).equals(12)){
                num2options=findoption(paperQuesOptionList21,paperQuesOptionList20,1);
            }
            if(rdmnum.get(2).equals(2)){
                num3options=findoption2(paperQuesOptionList31,paperQuesOptionList30,2);
            }else if(rdmnum.get(2).equals(3)){
                num3options=findoption2(paperQuesOptionList31,paperQuesOptionList30,3);
            }else if(rdmnum.get(2).equals(23)){
                num3options=findoption(paperQuesOptionList31,paperQuesOptionList30,2);
            }else if(rdmnum.get(2).equals(12)){
                num3options=findoption(paperQuesOptionList31,paperQuesOptionList30,1);
            }
            /**
             num1options=findoption(paperQuesOptionList11,paperQuesOptionList10,2);//选项组合
             num2options=findoption(paperQuesOptionList21,paperQuesOptionList20,2);
             num3options=findoption2(paperQuesOptionList31,paperQuesOptionList30,3);
             */
            /*List judgeopt=new ArrayList();//题目是否为正确的选项

            for(PaperQuesOption s: num1options){
                judgeopt.add(s.getIstrueoption());
            }

            for(PaperQuesOption s: num2options){
                judgeopt.add(s.getIstrueoption());
            }

            for(PaperQuesOption s: num3options){
                judgeopt.add(s.getIstrueoption());
            }*/
            model.addAttribute("ques1opt",num1options);
            model.addAttribute("ques2opt",num2options);
            model.addAttribute("ques3opt",num3options);

            loggetpaper.info("学号："+(String)userid+"文章："+paperid+"时间"+session.getAttribute(WebSecurityConfig.BEGINTIME_KEY));
            System.out.println(num1options+"\n");
            ModelAndView mv = new ModelAndView("readingtest");
            return mv;
        }else {
            model.addAttribute("testError",true);
            ModelAndView mv = new ModelAndView("testindex");
            return mv;
        }

    }
    //提交测试
    @PostMapping("/testsubmit")
    public ModelAndView testsubmit(@RequestParam Map req,HttpSession session){

        DoPaper doPaper=new DoPaper();

        Object userid;
        userid=session.getAttribute(WebSecurityConfig.SESSION_KEY);

        Ability ab=new Ability();

        List answer=new ArrayList();//学生选择答案转换成字符串

        Logger logdopaper = LogUtils.getDBLogger();

        List<String > queslist=new ArrayList();
        for(Iterator s=req.keySet().iterator();s.hasNext();){
            Object key = s.next();
            answer.add(req.get(key));
            queslist.add(key.toString());
        }
        System.out.println("问题列表为"+queslist);
        List judgeopt=new ArrayList();
        for(String s: queslist) {
            judgeopt.add(paperQuesOptService.quesOptions(Integer.valueOf(s).intValue()).get(0).getIstrueoption());
        }
        System.out.println("选项列表为"+judgeopt);

        /*
        * 分三部分计算分数
        * */
        int score;
        int score1;
        int score2;
        List subList1 = answer.subList(0,5);
        score1=mark(subList1,judgeopt.subList(0,5));
        System.out.println(subList1);
        System.out.println(judgeopt.subList(0,5));

        List subList2 = answer.subList(5, 10);
        score2=mark(subList2,judgeopt.subList(5,10));
        System.out.println(subList2);
        System.out.println(judgeopt.subList(5,10));

        List subList3 = answer.subList(10, 15);

        System.out.println(subList3);
        System.out.println(judgeopt.subList(10,15));


        String stuanswer=String.join(",",answer);
        String stuques=String.join(",",queslist);

        score=mark(answer,judgeopt);

        SimpleDateFormat af = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String endtime=af.format(System.currentTimeMillis());

        doPaper.setPaperid(paperid);
        doPaper.setStuid((String)userid);
        doPaper.setScore(score);
        doPaper.setPaperanswer(stuanswer);
        doPaper.setPaperstate(judgeopt.toString());
        doPaper.setBegintime((String)session.getAttribute(WebSecurityConfig.BEGINTIME_KEY));
        doPaper.setEndtime(endtime);
        doPaper.setScorea(score1);
        doPaper.setScoreb(score2);
        doPaper.setScorec(score-score1-score2);
        doPaper.setQueslist(stuques);

        System.out.println("学生选择的答案："+answer);

        System.out.println("你的分数是："+score);

        ab.setAbt1(String.valueOf(score1));
        ab.setAbt2(String.valueOf(score2));
        ab.setAbt3(String.valueOf(score-score1-score2));
        ab.setStuid((String)userid);
        ab.setTimes(time);

        abilityService.initAbility(ab);

        dopaperservice.saveStuAnswer(doPaper);


        logdopaper.info("提交测试"+userid+"/"+doPaper.getPaperid()+"/"+answer+"/"+score+"/"+queslist);

        ModelAndView mv = new ModelAndView("redirect:testindex");

        return mv;

    }
    @PostMapping("/getAbPaper")
    public List getAbPaper(@RequestParam Map<String, String> param, HttpSession session){

        Object userid;
        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id


        String paperid=transPaperid(param.get("paperid"));
        String ab=param.get("ability");

        int i=abToint(ab);//0表示理论能力；1表示应用能力；2表示分析能力

        Logger logregetpaper = LogUtils.getBussinessLogger();

        List<paperQues> paperQuesList = paperService.paperQues(paperid);

        int rdm=paperQuesList.get(i).getRdm();

        List<PaperQuesOption> paperQuesOptionList1 =paperQuesOptService.quesOptions(paperQuesList.get(i).getQuestion(),"1");

        List<PaperQuesOption> paperQuesOptionList0 =paperQuesOptService.quesOptions(paperQuesList.get(i).getQuestion(),"0");

        List<PaperQuesOption> paperQuesOptionList=null;

        if(rdm==2||rdm==3){
            paperQuesOptionList=findoption2(paperQuesOptionList0,paperQuesOptionList1,rdm);
        }else if(rdm==23){
            paperQuesOptionList=findoption(paperQuesOptionList0,paperQuesOptionList1,2);
        }else if(rdm==12){
            paperQuesOptionList=findoption(paperQuesOptionList0,paperQuesOptionList1,1);
        }

        PaperQuesOption ques=new PaperQuesOption();

        String paperquestion=paperQuesList.get(i).getQuescontent();

        ques.setOptions(paperquestion);

        //subjudgeopt=new ArrayList();
        int j;
        List<String> paperidList=new ArrayList();
        for(PaperQuesOption subs:paperQuesOptionList){
            //subjudgeopt.add(subs.getIstrueoption());
            j=subs.getId();
            paperidList.add(String.valueOf(j));
        }
        String plist=String.join(",",paperidList);

        session.setAttribute(WebSecurityConfig.PAPERLIST_KEY,plist);
        paperQuesOptionList.add(ques);
        logregetpaper.info("协商中的测试:获取测试列表"+userid+"/"+param.get("ability")+"/"+param.get("paperid")+"/"+paperidList);
        return paperQuesOptionList;
    }
    @PostMapping("/retest")//协商中重新测试
    public Map reTest(@RequestParam Map<String, String> param, HttpSession session){

        Object userid;
        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id
        Nego nego=new Nego();
        AbilityNew abilityNew=new AbilityNew();
        String paperid=null;

        paperid=transPaperid(param.get("paperid"));

        List<Ability> abilityList=abilityService.findAbilityTimes((String)userid,transtoTime(param.get("paperid")));
        List<AbilityNew> abilityNewList=abilityNewService.findAbilitynew((String)userid,paperid);
        List<DoPaper>  doPaperList =resultService.getpaperAndpaperid((String)userid,paperid);

        List<String> negoanswer = java.util.Arrays.asList(param.get("btntext").split(","));

        System.out.println("返回的答案为："+param.get("btntext"));

        System.out.println("能力面向："+param.get("ability"));

        System.out.println("日期："+param.get("paperid"));

        System.out.println("转list："+negoanswer);

        Object paperList=session.getAttribute(WebSecurityConfig.PAPERLIST_KEY);
        System.out.println("答案："+paperList);
        System.out.println("数据类型："+paperList.getClass());


        List<String> sub=java.util.Arrays.asList(paperList.toString().split(","));

        List<String> subjudgeopt=new ArrayList<>();

        for(Object s:sub){
            subjudgeopt.add(paperQuesOptService.quesOptions(Integer.parseInt(s.toString())).get(0).getIstrueoption());
        }
        System.out.println("参考答案："+subjudgeopt);
        System.out.println("参考答案数据类型："+subjudgeopt.getClass());
        Logger logretest = LogUtils.getBussinessLogger();

        int score=mark(negoanswer,subjudgeopt);
        String s=String.valueOf(score);
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        double score_new=0;
        if(abilityNewList.isEmpty()){
            if(abToString(param.get("ability")).equals("1")){
                score_new=Double.valueOf(doPaperList.get(doPaperList.size()-1).getScorea())*0.4+(double)score*0.6;
                abilityNew.setAbt1(score_new);
                abilityNew.setAbt2(Double.valueOf(doPaperList.get(doPaperList.size()-1).getScoreb()));
                abilityNew.setAbt3(Double.valueOf(doPaperList.get(doPaperList.size()-1).getScorec()));
                abilityNew.setStuid((String)userid);
                abilityNew.setAbt(param.get("ability"));
                abilityNew.setTime(df.format(System.currentTimeMillis()));
                abilityNew.setPaperid(paperid);
                abilityNewService.AbilityNew(abilityNew);
            }
            if(abToString(param.get("ability")).equals("2")){
                score_new=Double.valueOf(doPaperList.get(doPaperList.size()-1).getScoreb())*0.4+(double)score*0.6;
                abilityNew.setAbt2(score_new);
                abilityNew.setAbt1(doPaperList.get(doPaperList.size()-1).getScorea());
                abilityNew.setAbt3(doPaperList.get(doPaperList.size()-1).getScorec());
                abilityNew.setStuid((String)userid);
                abilityNew.setAbt(param.get("ability"));
                abilityNew.setTime(df.format(System.currentTimeMillis()));
                abilityNew.setPaperid(paperid);
                abilityNewService.AbilityNew(abilityNew);
            }
            if(abToString(param.get("ability")).equals("3")){
                score_new=Double.valueOf(doPaperList.get(doPaperList.size()-1).getScorec())*0.4+(double)score*0.6;
                abilityNew.setAbt3(score_new);
                abilityNew.setAbt2(doPaperList.get(doPaperList.size()-1).getScoreb());
                abilityNew.setAbt1(doPaperList.get(doPaperList.size()-1).getScorea());
                abilityNew.setStuid((String)userid);
                abilityNew.setAbt(param.get("ability"));
                abilityNew.setTime(df.format(System.currentTimeMillis()));
                abilityNew.setPaperid(paperid);
                abilityNewService.AbilityNew(abilityNew);
            }
        }else{
            if(abToString(param.get("ability")).equals("1")){
                score_new=abilityNewList.get(abilityNewList.size()-1).getAbt1()*0.4+(double)score*0.6;
                abilityNew.setAbt1(score_new);
                abilityNew.setAbt2(abilityNewList.get(abilityNewList.size()-1).getAbt2());
                abilityNew.setAbt3(abilityNewList.get(abilityNewList.size()-1).getAbt3());
                abilityNew.setStuid((String)userid);
                abilityNew.setAbt(param.get("ability"));
                abilityNew.setTime(df.format(System.currentTimeMillis()));
                abilityNew.setPaperid(paperid);
                abilityNewService.AbilityNew(abilityNew);
            }
            if(abToString(param.get("ability")).equals("2")){
                score_new=abilityNewList.get(abilityNewList.size()-1).getAbt2()*0.4+(double)score*0.6;
                abilityNew.setAbt2(score_new);
                abilityNew.setAbt1(abilityNewList.get(abilityNewList.size()-1).getAbt1());
                abilityNew.setAbt3(abilityNewList.get(abilityNewList.size()-1).getAbt3());
                abilityNew.setStuid((String)userid);
                abilityNew.setAbt(param.get("ability"));
                abilityNew.setTime(df.format(System.currentTimeMillis()));
                abilityNew.setPaperid(paperid);
                abilityNewService.AbilityNew(abilityNew);
            }
            if(abToString(param.get("ability")).equals("3")){
                score_new=abilityNewList.get(abilityNewList.size()-1).getAbt3()*0.4+(double)score*0.6;
                abilityNew.setAbt3(score_new);
                abilityNew.setAbt2(abilityNewList.get(abilityNewList.size()-1).getAbt2());
                abilityNew.setAbt1(abilityNewList.get(abilityNewList.size()-1).getAbt1());
                abilityNew.setStuid((String)userid);
                abilityNew.setAbt(param.get("ability"));
                abilityNew.setTime(df.format(System.currentTimeMillis()));
                abilityNew.setPaperid(paperid);
                abilityNewService.AbilityNew(abilityNew);
            }
        }
        nego.setStuid((String)userid);
        nego.setPaperid(paperid);
        nego.setAbility(abToString(param.get("ability")));
        nego.setNegopt("3");
        nego.setMarka(paperList.toString());
        nego.setMarkb(param.get("btntext"));
        nego.setMarkc(s);
        nego.setEndtime(df.format(System.currentTimeMillis()));
        negoService.insertNego(nego);
        Map map=new HashMap();
        map.put("scoree",score);
        map.put("sysscore",score_new);

        logretest.info("提交测试"+userid+"/"+param.get("ability")+"/"+paperid+"/"+score);
        return map;
    }

    //计算分数
    public int mark(List answer,List judgeopt){

        int score = 0;

        for(int i=0;i<answer.size();i++)
        {
            if((answer.get(i).equals("a")&&judgeopt.get(i).equals("1")||(answer.get(i).equals("b")&&judgeopt.get(i).equals("0")))){
                score=score+2;
            }
            if((answer.get(i).equals("b")&&judgeopt.get(i).equals("1")||(answer.get(i).equals("a")&&judgeopt.get(i).equals("0")))){
                score=score-1;
            }
        }
        return score;
    }
    /*
    * radio点击存入日志
    * */
    @PostMapping("/postradio")
    public void radiolog(@RequestParam Map<String, String> param,HttpSession session) throws Exception{

        Object userid;
        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id

        Logger log1 = LogUtils.getDBLogger();

        String id=param.get("btnNum");

        String value=param.get("btnvalue");

        log1.info(userid+" "+id+" "+value);

    }
   //能力值转换
    public int abToint(String ab){
        int i=0;
        if(ab.equals("理论能力")){
            i=0;
        }else if(ab.equals("应用能力")){
            i=1;
        }else{
            i=2;
        }
        return i;
    }
    /*
     * 随机选择5个题目
     * 正确的2-3个（第二题1-2个）
     * */
    public List<PaperQuesOption> findoption(List<PaperQuesOption> TrueOptionList, List<PaperQuesOption> FalseOptionList, int rdm){

        Random rd = new Random(); //创建一个Random类对象实例
        int x = rd.nextInt(2)+rdm;
        System.out.println("随机数为："+x);
        Collections.shuffle(TrueOptionList);
        List<PaperQuesOption> ques1trueopt=TrueOptionList.subList(0,x);
        Collections.shuffle(FalseOptionList);
        ques1trueopt.addAll(FalseOptionList.subList(0,optionnum-x));
        Collections.shuffle(ques1trueopt);

        return  ques1trueopt;
    }
    /*
     * 随机选择5个题目
     * 正确的2个
     * */
    public List<PaperQuesOption> findoption2(List<PaperQuesOption> TrueOptionList, List<PaperQuesOption> FalseOptionList, int num){

        Collections.shuffle(TrueOptionList);
        List<PaperQuesOption> ques1trueopt=TrueOptionList.subList(0,num);
        Collections.shuffle(FalseOptionList);
        ques1trueopt.addAll(FalseOptionList.subList(0,optionnum-num));
        Collections.shuffle(ques1trueopt);

        return  ques1trueopt;
    }
    public String abToString(String ab){
        String i="1";
        if(ab.equals("理论能力")){
            i="1";
        }else if(ab.equals("应用能力")){
            i="2";
        }else{
            i="3";
        }
        return i;
    }

    public String TimetoPaperid(String times){
        String paperid=null;
        if(times.equals("3")){
            paperid="srl04";
        }else if(times.equals("2")){
            paperid="srl03";
        }else if(times.equals("1")){
            paperid="srl02";
        }else if(times.equals("4")){
            paperid="srl05";
        }else if(times.equals("5")){
            paperid="srl06";
        }else if(times.equals("6")){
            paperid="srl07";
        }else if(times.equals("7")){
            paperid="srl08";
        }else if(times.equals("8")){
            paperid="srl09";
        }else if(times.equals("9")){
            paperid="srl10";
        }
        return paperid;
    }
    public String transtoTime(String s){

        String times=null;
        if(s.equals("2018-10-10 SRL")){
            times="1";
        }else if(s.equals("2018-10-17 SRL")){
            times="2";
        }else if(s.equals("2018-10-24 SRL")){
            times="3";
        }else if(s.equals("2018-11-7 SRL")){
            times="4";
        }else if(s.equals("2018-11-14 SRL")){
            times="5";
        }else if(s.equals("2018-11-21 SRL")){
            times="6";
        }else if(s.equals("2018-12-12 SRL")){
            times="7";
        }else if(s.equals("2018-12-19 SRL")){
            times="8";
        }else if(s.equals("2018-12-26 SRL")){
            times="9";
        }
        return times;
    }
    public String transPaperid(String s){

        String paperid=null;
        if(s.equals("2018-10-10 SRL")){
            paperid="srl02";
        }else if(s.equals("2018-10-17 SRL")){
            paperid="srl03";
        }else if(s.equals("2018-10-24 SRL")){
            paperid="srl04";
        }else if(s.equals("2018-11-7 SRL")){
            paperid="srl05";
        }else if(s.equals("2018-11-14 SRL")){
            paperid="srl06";
        }else if(s.equals("2018-11-21 SRL")){
            paperid="srl07";
        }else if(s.equals("2018-12-12 SRL")){
            paperid="srl08";
        }else if(s.equals("2018-12-19 SRL")){
            paperid="srl09";
        }else if(s.equals("2018-12-26 SRL")){
            paperid="srl10";
        }
        return paperid;
    }
    public String evaluate(float score,String abt){
        if(score>=8&&score<=10){
            abt="完全理解";
        }else if(score<8&score>=6){
            abt="理解";
        } else if(score<6&score>=4){
            abt="一知半解";
        } else if(score<4&score>=2){
            abt="不理解";
        }else if(score<2){
            abt="完全不理解";
        }else {
            abt="未知";
        }

        return abt;

    }

}
