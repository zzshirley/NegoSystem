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

    public static String begintime;

    public static List<PaperQuesOption> num1options;//第一道题选项

    public static List<PaperQuesOption> num2options;//第二道题选项

    public static List<PaperQuesOption> num3options;//第三道题

    public static List judgeopt;

    public static List subjudgeopt;//协商中测试答案

    public static List paperidList;//协商中试题

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

    public String paperid;
    public String time;

    @RequestMapping("/readingtest")
    public ModelAndView getpaper(@RequestParam String times,Model model,HttpSession session){
        Object userid;
        userid=session.getAttribute(WebSecurityConfig.SESSION_KEY);
        Paper paper = new Paper();

        time=times;
        if(times.equals("3")){
            paperid="srl04";
        }else if(times.equals("2")){
            paperid="srl03";
        }else if(times.equals("1")){
            paperid="srl02";
        }
        paper.setPaperid(paperid);

        List<Selfeva> selfevaList=selfevaService.getSelfEvaBytime((String)userid,times);
        List<DoPaper> doPapers=resultService.getpaperAndpaperid((String)userid,paperid);
        if(selfevaList.isEmpty()){
            model.addAttribute("selfirst","true");
            ModelAndView mv = new ModelAndView("testindex");
            return mv;
        }
        if(doPapers.isEmpty()){

            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            begintime=df.format(System.currentTimeMillis());//开始时间

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
            judgeopt=new ArrayList();//题目是否为正确的选项

            for(PaperQuesOption s: num1options){
                judgeopt.add(s.getIstrueoption());
            }

            for(PaperQuesOption s: num2options){
                judgeopt.add(s.getIstrueoption());
            }

            for(PaperQuesOption s: num3options){
                judgeopt.add(s.getIstrueoption());
            }
            model.addAttribute("ques1opt",num1options);
            model.addAttribute("ques2opt",num2options);
            model.addAttribute("ques3opt",num3options);

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

        Logger logdopaper = LogUtils.getBussinessLogger();

        List queslist=new ArrayList();
        for(Iterator s=req.keySet().iterator();s.hasNext();){
            Object key = s.next();
            answer.add(req.get(key));
            queslist.add((String)key);
        }

        System.out.println("问题列表为"+queslist);
        List subList1 = answer.subList(0,5);

        /*
        * 分三部分计算分数
        * */
        int score1=mark(subList1,judgeopt.subList(0,5));
        System.out.println(subList1);
        System.out.println(judgeopt.subList(0,5));

        List subList2 = answer.subList(5, 10);
        int score2=mark(subList2,judgeopt.subList(5,10));

        System.out.println(subList2);
        System.out.println(judgeopt.subList(5,10));

        List subList3 = answer.subList(10, 15);


        String stuanswer=String.join(",",answer);
        String stuques=String.join(",",queslist);

        int score=mark(answer,judgeopt);

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        doPaper.setPaperid(paperid);
        doPaper.setStuid((String)userid);
        doPaper.setScore(score);
        doPaper.setPaperanswer(stuanswer);
        doPaper.setPaperstate("1");
        doPaper.setBegintime(begintime);
        doPaper.setEndtime(df.format(System.currentTimeMillis()));
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


        logdopaper.info(userid+"/"+doPaper.getPaperid()+"/"+answer+"/"+score+"/"+queslist);

        ModelAndView mv = new ModelAndView("testindex");

        return mv;

    }
    @PostMapping("/getAbPaper")
    public List getAbPaper(@RequestParam Map<String, String> param, HttpSession session){

        Object userid;
        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id


        String paperid=null;
        if(param.get("paperid").equals("2018-10-10 SRL")){
            paperid="srl02";
        }else if(param.get("paperid").equals("2018-10-17 SRL")){
            paperid="srl03";
        }else if(param.get("paperid").equals("2018-10-24 SRL")){
            paperid="srl04";
        }
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

        subjudgeopt=new ArrayList();
        paperidList=new ArrayList();
        for(PaperQuesOption subs:paperQuesOptionList){
            subjudgeopt.add(subs.getIstrueoption());
            paperidList.add(subs.getId());
        }
        paperQuesOptionList.add(ques);
        logregetpaper.info("协商中的测试:获取测试列表"+userid+"/"+param.get("ability")+"/"+param.get("paperid")+"/"+paperidList);
        return paperQuesOptionList;
    }
    @PostMapping("/retest")
    public int reTest(@RequestParam Map<String, String> param, HttpSession session){

        Object userid;
        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id
        Nego nego=new Nego();

        String paperid=null;
        if(param.get("paperid").equals("2018-10-10 SRL")){
            paperid="srl02";
        }else if(param.get("paperid").equals("2018-10-17 SRL")){
            paperid="srl03";
        }else if(param.get("paperid").equals("2018-10-24 SRL")){
            paperid="srl04";
        }

        List<String> negoanswer = java.util.Arrays.asList(param.get("btntext").split(","));

        System.out.println("返回的答案为："+param.get("btntext"));

        System.out.println("能力面向："+param.get("ability"));

        System.out.println("日期："+param.get("paperid"));

        System.out.println("转list："+negoanswer);
        System.out.println("参考答案："+subjudgeopt);

        Logger logretest = LogUtils.getBussinessLogger();

        int score=mark(negoanswer,subjudgeopt);
        String s=String.valueOf(score);
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        nego.setStuid((String)userid);
        nego.setPaperid(paperid);
        nego.setAbility(abToString(param.get("ability")));
        nego.setNegopt("3");
        nego.setMarka(paperidList.toString());
        nego.setMarkb(param.get("btntext"));
        nego.setMarkc(s);
        nego.setEndtime(df.format(System.currentTimeMillis()));
        negoService.insertNego(nego);

        logretest.info("提交测试"+userid+"/"+param.get("ability")+"/"+paperid+"/"+score);

        return score;

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

        log1.info(userid+"/"+id+"/"+value);

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

    public String ablity(int scorea){
        String abt1="";
        String  abt=evaluate(scorea,abt1);
        return abt;
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
