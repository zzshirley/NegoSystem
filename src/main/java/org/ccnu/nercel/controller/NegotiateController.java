package org.ccnu.nercel.controller;

import org.ccnu.nercel.Util.LogUtils;
import org.ccnu.nercel.bean.*;
import org.ccnu.nercel.config.WebSecurityConfig;
import org.ccnu.nercel.service.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Xiaotong
 * @createTime 20180908 下午3:05
 * @description 协商
 */
@RestController
public class NegotiateController {

    public static int  optionnum=5;

    @Autowired
    private ResultService resultService;

    @Autowired
    private SelfevaService selfevaService;

    @Autowired
    private AbilityService abilityService;

    @Autowired
    private NegoService negoService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private PaperQuesOptService paperQuesOptService;

    @Autowired
    private StuQueService stuQueService;

    @Autowired
    private SelfevaNewService selfevaNewService;

    @Autowired
    private AbilityNewService abilityNewService;

    /*
    开始协商
     */
    @RequestMapping("/negotiate")
    public ModelAndView negotiate(@RequestParam String ab, @RequestParam String time, @RequestParam String selfeva,@RequestParam String syseva,HttpSession session,Model model) {

       Object userid;
       userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id
       SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

       Logger logabt = LogUtils.getBussinessLogger();

       Nego nego=new Nego();
       nego.setStuid((String)userid);
       nego.setPaperid(transPaperid(time));
       nego.setAbility(ab);
       nego.setEndtime(df.format(System.currentTimeMillis()));
       nego.setMarka("negostart");

       negoService.insertNego(nego);

       model.addAttribute("stuid",userid);
       model.addAttribute("time",time);
       model.addAttribute("ability",toStringab(ab));
       model.addAttribute("sys",syseva);
       model.addAttribute("self1",selfeva);
       logabt.info("协商开始："+userid+"/"+ab+"/"+time+"/"+selfeva+"/"+syseva);

       ModelAndView mv = new ModelAndView("negotiate");
       return mv;
    }

    //查询测评结果，返回成绩查询list
    @PostMapping("/getScore")
    public Map getScore(@RequestParam Map<String, String> param, HttpSession session) {

        Object userid;
        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id

        Nego nego=new Nego();
        Map map =new HashMap();
        int i=abToint(param.get("ability"));
        Logger logaskexp = LogUtils.getBussinessLogger();

        String paperid=transPaperid(param.get("paperid"));

        List<DoPaper> resultList = resultService.getpaperAndpaperid((String)userid,paperid);

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        nego.setPaperid(paperid);
        nego.setAbility(abToString(param.get("ability")));
        nego.setStuid((String)userid);
        nego.setNegopt("1");//1表示查询成绩
        nego.setMarka("查看成绩");
        nego.setEndtime(df.format(System.currentTimeMillis()));

        logaskexp.info("请求查看成绩"+userid+"/"+param.get("ability")+"/"+paperid);

        negoService.insertNego(nego);

        List<PaperQuesOption> paperQuesOptionList=null;

        if(paperid.equals("srl02")){
            List<paperQues> paperQuesList = paperService.paperQues(paperid);

            int rdm=paperQuesList.get(i).getRdm();

            List<PaperQuesOption> paperQuesOptionList1 =paperQuesOptService.quesOptions(paperQuesList.get(i).getQuestion(),"1");

            List<PaperQuesOption> paperQuesOptionList0 =paperQuesOptService.quesOptions(paperQuesList.get(i).getQuestion(),"0");

            if(rdm==2||rdm==3){
                paperQuesOptionList=findoption2(paperQuesOptionList0,paperQuesOptionList1,rdm);
            }else if(rdm==23){
                paperQuesOptionList=findoption(paperQuesOptionList0,paperQuesOptionList1,2);
            }else if(rdm==12){
                paperQuesOptionList=findoption(paperQuesOptionList0,paperQuesOptionList1,1);
            }
            map.put("ques1",paperQuesOptionList.get(0).getOptions());
            map.put("ques2",paperQuesOptionList.get(1).getOptions());
            map.put("ques3",paperQuesOptionList.get(2).getOptions());
            map.put("ques4",paperQuesOptionList.get(3).getOptions());
            map.put("ques5",paperQuesOptionList.get(4).getOptions());
        }

        String stuanswer[] = resultList.get(resultList.size()-1).getPaperanswer().split(",");
        String quesnum[] = resultList.get(resultList.size()-1).getQueslist().split(",");


        if(abToString(param.get("ability")).equals("1")){
            map.put("score",resultList.get(resultList.size()-1).getScorea());
            map.put("ques1",paperQuesOptService.quesOptions(Integer.valueOf(quesnum[0]).intValue()).get(0).getOptions());
            map.put("ques2",paperQuesOptService.quesOptions(Integer.valueOf(quesnum[1]).intValue()).get(0).getOptions());
            map.put("ques3",paperQuesOptService.quesOptions(Integer.valueOf(quesnum[2]).intValue()).get(0).getOptions());
            map.put("ques4",paperQuesOptService.quesOptions(Integer.valueOf(quesnum[3]).intValue()).get(0).getOptions());
            map.put("ques5",paperQuesOptService.quesOptions(Integer.valueOf(quesnum[4]).intValue()).get(0).getOptions());
            map.put("score1",OptoString(stuanswer[0]));
            map.put("score2",OptoString(stuanswer[1]));
            map.put("score3",OptoString(stuanswer[2]));
            map.put("score4",OptoString(stuanswer[3]));
            map.put("score5",OptoString(stuanswer[4]));
        }else if(abToString(param.get("ability")).equals("2")){
            map.put("score",resultList.get(resultList.size()-1).getScoreb());
            map.put("ques1",paperQuesOptService.quesOptions(Integer.valueOf(quesnum[5]).intValue()).get(0).getOptions());
            map.put("ques2",paperQuesOptService.quesOptions(Integer.valueOf(quesnum[6]).intValue()).get(0).getOptions());
            map.put("ques3",paperQuesOptService.quesOptions(Integer.valueOf(quesnum[7]).intValue()).get(0).getOptions());
            map.put("ques4",paperQuesOptService.quesOptions(Integer.valueOf(quesnum[8]).intValue()).get(0).getOptions());
            map.put("ques5",paperQuesOptService.quesOptions(Integer.valueOf(quesnum[9]).intValue()).get(0).getOptions());
            map.put("score1",OptoString(stuanswer[5]));
            map.put("score2",OptoString(stuanswer[6]));
            map.put("score3",OptoString(stuanswer[7]));
            map.put("score4",OptoString(stuanswer[8]));
            map.put("score5",OptoString(stuanswer[9]));
        }else if(abToString(param.get("ability")).equals("3")){
            map.put("score",resultList.get(resultList.size()-1).getScorec());
            map.put("ques1",paperQuesOptService.quesOptions(Integer.valueOf(quesnum[10]).intValue()).get(0).getOptions());
            map.put("ques2",paperQuesOptService.quesOptions(Integer.valueOf(quesnum[11]).intValue()).get(0).getOptions());
            map.put("ques3",paperQuesOptService.quesOptions(Integer.valueOf(quesnum[12]).intValue()).get(0).getOptions());
            map.put("ques4",paperQuesOptService.quesOptions(Integer.valueOf(quesnum[13]).intValue()).get(0).getOptions());
            map.put("ques5",paperQuesOptService.quesOptions(Integer.valueOf(quesnum[14]).intValue()).get(0).getOptions());
            map.put("score1",OptoString(stuanswer[10]));
            map.put("score2",OptoString(stuanswer[11]));
            map.put("score3",OptoString(stuanswer[12]));
            map.put("score4",OptoString(stuanswer[13]));
            map.put("score5",OptoString(stuanswer[14]));
        }
        map.put("date",param.get("paperid"));



        return map;
    }
    //学生解释测评结果
    @PostMapping("/findExp")
    public Map findstuexp(@RequestParam Map<String, String> param, HttpSession session){

        String exp="stuexp";

        Object userid;
        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id

        Logger logexp= LogUtils.getBussinessLogger();

        String paperid=transPaperid(param.get("paperid"));

        List<Nego> negoList=negoService.findNegot((String)userid,paperid,"2");

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        logexp.info("学生请求解释测评结果"+"/"+"stuid"+userid+"/"+"ab:"+param.get("ab"));

        Map negoexp=new HashMap();
        for(int i=0;i<negoList.size();i++){
            negoexp.put(exp+String.valueOf(i),negoList.get(i).getMarkc());
        }

        return negoexp;
    }
    //学生解释测评结果
    @PostMapping("/postExp")
    public Map stuexp(@RequestParam Map<String, String> param, HttpSession session){

        Object userid;
        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id

        Logger logexp= LogUtils.getBussinessLogger();
        Nego nego=new Nego();

        String paperid=transPaperid(param.get("paperid"));

        List<Nego> negoList=negoService.findNegot((String)userid,paperid,"2");

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        nego.setStuid((String)userid);
        nego.setPaperid(paperid);
        nego.setAbility(abToString(param.get("ab")));
        nego.setNegopt("2");
        nego.setMarka("学生解释理由");
        nego.setMarkb(param.get("btnoption"));
        nego.setMarkc(param.get("btntext"));

        nego.setEndtime(df.format(System.currentTimeMillis()));
        System.out.println("学生的理由是："+param.get("btntext"));
        System.out.println("学生的选择是："+param.get("btnoption"));//ok

        negoService.insertNego(nego);

        logexp.info("学生提交测试理由"+"/"+"stuid"+userid+"/"+"ab:"+param.get("ab")+"/"+param.get("btnoption")+"/"+param.get("btntext"));

        Map negoexp=new HashMap();
        negoexp.put("ret","成功提交成绩解释");

        return negoexp;
    }
    //学生重新自我测评
    @PostMapping("/postreself")
    public void reSeleva(@RequestParam Map<String, String> param, HttpSession session){
        Object userid;
        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id

        SelfevaNew selfevaNew=new SelfevaNew();

        String paperid=transPaperid(param.get("paperid"));

        Nego nego=new Nego();
        Logger logretest= LogUtils.getBussinessLogger();



        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        List<SelfevaNew> selfevaNewList=selfevaNewService.findnewSelf((String)userid,paperid);
        List<Selfeva> selfevaList=selfevaService.getSelfEvaBytime((String)userid,transtimes(param.get("paperid")));

        double self_new;
        if(selfevaNewList.isEmpty()){
            if(abToString(param.get("ab")).equals("1")){
                //self_new=Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt1())*0.6+Double.valueOf(param.get("selfeva"))*0.4;
                self_new=Double.valueOf(param.get("selfeva"));
                selfevaNew.setAbt1(self_new);
                selfevaNew.setAbt2(Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt2()));
                selfevaNew.setAbt3(Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt3()));
                selfevaNew.setEndtime(df.format(System.currentTimeMillis()));
                selfevaNew.setStuid((String)userid);
                selfevaNew.setTimes(paperid);
                selfevaNew.setAbt(abToString(param.get("ab")));
                selfevaNewService.savenewself(selfevaNew);
            }
            if(abToString(param.get("ab")).equals("2")){
                //self_new=Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt2())*0.6+Double.valueOf(param.get("selfeva"))*0.4;
                self_new=Double.valueOf(param.get("selfeva"));
                selfevaNew.setAbt2(self_new);
                selfevaNew.setAbt1(Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt1()));
                selfevaNew.setAbt3(Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt3()));
                selfevaNew.setEndtime(df.format(System.currentTimeMillis()));
                selfevaNew.setStuid((String)userid);
                selfevaNew.setTimes(paperid);
                selfevaNew.setAbt(abToString(param.get("ab")));
                selfevaNewService.savenewself(selfevaNew);
            }
            if(abToString(param.get("ab")).equals("3")){
               // self_new=Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt3())*0.6+Double.valueOf(param.get("selfeva"))*0.4;
                self_new=Double.valueOf(param.get("selfeva"));
                selfevaNew.setAbt3(self_new);
                selfevaNew.setAbt2(Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt2()));
                selfevaNew.setAbt1(Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt1()));
                selfevaNew.setEndtime(df.format(System.currentTimeMillis()));
                selfevaNew.setStuid((String)userid);
                selfevaNew.setTimes(paperid);
                selfevaNew.setAbt(abToString(param.get("ab")));
                selfevaNewService.savenewself(selfevaNew);
            }
        }else{
            if(abToString(param.get("ab")).equals("1")){
                //self_new=selfevaNewList.get(selfevaNewList.size()-1).getAbt1()*0.6+Double.valueOf(param.get("selfeva"))*0.4;
                self_new=Double.valueOf(param.get("selfeva"));
                selfevaNew.setAbt1(self_new);
                selfevaNew.setAbt2(selfevaNewList.get(selfevaNewList.size()-1).getAbt2());
                selfevaNew.setAbt3(selfevaNewList.get(selfevaNewList.size()-1).getAbt3());
                selfevaNew.setEndtime(df.format(System.currentTimeMillis()));
                selfevaNew.setStuid((String)userid);
                selfevaNew.setTimes(paperid);
                selfevaNew.setAbt(abToString(param.get("ab")));
                selfevaNewService.savenewself(selfevaNew);
            }
            if(abToString(param.get("ab")).equals("2")){
                //self_new=selfevaNewList.get(selfevaNewList.size()-1).getAbt2()*0.6+Double.valueOf(param.get("selfeva"))*0.4;
                self_new=Double.valueOf(param.get("selfeva"));
                selfevaNew.setAbt2(self_new);
                selfevaNew.setAbt1(selfevaNewList.get(selfevaNewList.size()-1).getAbt1());
                selfevaNew.setAbt3(selfevaNewList.get(selfevaNewList.size()-1).getAbt3());
                selfevaNew.setEndtime(df.format(System.currentTimeMillis()));
                selfevaNew.setStuid((String)userid);
                selfevaNew.setTimes(paperid);
                selfevaNew.setAbt(abToString(param.get("ab")));
                selfevaNewService.savenewself(selfevaNew);
            }
            if(abToString(param.get("ab")).equals("3")){
                //self_new=selfevaNewList.get(selfevaNewList.size()-1).getAbt3()*0.6+Double.valueOf(param.get("selfeva"))*0.4;
                self_new=Double.valueOf(param.get("selfeva"));
                selfevaNew.setAbt3(Double.valueOf(param.get("selfeva")));
                selfevaNew.setAbt2(selfevaNewList.get(selfevaNewList.size()-1).getAbt2());
                selfevaNew.setAbt1(selfevaNewList.get(selfevaNewList.size()-1).getAbt1());
                selfevaNew.setEndtime(df.format(System.currentTimeMillis()));
                selfevaNew.setStuid((String)userid);
                selfevaNew.setAbt(abToString(param.get("ab")));
                selfevaNew.setTimes(paperid);
                selfevaNewService.savenewself(selfevaNew);
            }
        }

        nego.setStuid((String)userid);
        nego.setPaperid(paperid);
        nego.setAbility(abToString(param.get("ab")));
        nego.setNegopt("4");
        nego.setMarka("学生重新自我测评");
        nego.setMarkb(param.get("selfeva"));
        nego.setEndtime(df.format(System.currentTimeMillis()));
        System.out.println("学生的自我评价："+param.get("selfeva"));//ok
        logretest.info("学生重新自我测评"+"stuid"+userid+"/"+"ab:"+param.get("ab")+"/"+param.get("selfeva"));
        negoService.insertNego(nego);

    }
    //同意协商
    @PostMapping("/negoagree")
    public void negoDecision(@RequestParam Map<String, String> param, HttpSession session){

        Object userid;
        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id

        Logger log0 = LogUtils.getBussinessLogger();
        Nego nego=new Nego();


        String paperid=transPaperid(param.get("paperid"));

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        SelfevaNew selfevaNew=new SelfevaNew();
        AbilityNew abilityNew=new AbilityNew();
        List<SelfevaNew> selfevaNewList =selfevaNewService.findnewSelf((String)userid,paperid);
        List<AbilityNew> abilityNewList=abilityNewService.findAbilitynew((String)userid,paperid);
        List<Selfeva> selfevaList=selfevaService.getSelfEvaBytime((String)userid,transtimes(param.get("paperid")));
        List<Ability> abilityList = abilityService.findAbilityTimes((String)userid,transtimes(param.get("paperid")));
        List<DoPaper>  doPaperList =resultService.getpaperAndpaperid((String)userid,paperid);

        if(selfevaNewList.isEmpty()){
            if(abToString(param.get("ability")).equals("1")){
                selfevaNew.setAbt(abToString(param.get("ability")));
                selfevaNew.setAbt1(Double.valueOf(param.get("selfeva")));
                selfevaNew.setAbt2(Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt2()));
                selfevaNew.setAbt3(Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt3()));
            }
            if(abToString(param.get("ability")).equals("2")){
                selfevaNew.setAbt(abToString(param.get("ability")));
                selfevaNew.setAbt2(Double.valueOf(param.get("selfeva")));
                selfevaNew.setAbt1(Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt1()));
                selfevaNew.setAbt3(Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt3()));
            }
            if(abToString(param.get("ability")).equals("3")){
                selfevaNew.setAbt(abToString(param.get("ability")));
                selfevaNew.setAbt3(Double.valueOf(param.get("selfeva")));
                selfevaNew.setAbt2(Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt2()));
                selfevaNew.setAbt1(Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt1()));
            }
            selfevaNew.setStuid((String)userid);
            selfevaNew.setEndtime(df.format(System.currentTimeMillis()));
            selfevaNew.setTimes(paperid);
            selfevaNewService.savenewself(selfevaNew);
        }else{
            if(abToString(param.get("ability")).equals("1")){
                selfevaNew.setAbt(abToString(param.get("ability")));
                selfevaNew.setAbt1(Double.valueOf(param.get("selfeva")));
                selfevaNew.setAbt2(selfevaNewList.get(selfevaNewList.size()-1).getAbt2());
                selfevaNew.setAbt3(Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt3()));
            }
            if(abToString(param.get("ability")).equals("2")){
                selfevaNew.setAbt(abToString(param.get("ability")));
                selfevaNew.setAbt2(Double.valueOf(param.get("selfeva")));
                selfevaNew.setAbt1(selfevaNewList.get(selfevaNewList.size()-1).getAbt1());
                selfevaNew.setAbt3(selfevaNewList.get(selfevaNewList.size()-1).getAbt3());
            }
            if(abToString(param.get("ability")).equals("3")){
                selfevaNew.setAbt(abToString(param.get("ability")));
                selfevaNew.setAbt3(Double.valueOf(param.get("selfeva")));
                selfevaNew.setAbt2(selfevaNewList.get(selfevaNewList.size()-1).getAbt2());
                selfevaNew.setAbt1(selfevaNewList.get(selfevaNewList.size()-1).getAbt1());
            }
            selfevaNew.setStuid((String)userid);
            selfevaNew.setEndtime(df.format(System.currentTimeMillis()));
            selfevaNew.setTimes(paperid);
            selfevaNewService.savenewself(selfevaNew);
        }
        //测试成绩
        if(abilityNewList.isEmpty()){
            if(abToString(param.get("ability")).equals("1")){
                abilityNew.setAbt(abToString(param.get("ability")));
                abilityNew.setAbt1(Double.valueOf(param.get("sys")));
                abilityNew.setAbt2(Double.valueOf(doPaperList.get(selfevaList.size()-1).getScoreb()));
                abilityNew.setAbt3(Double.valueOf(doPaperList.get(selfevaList.size()-1).getScorec()));
            }
            if(abToString(param.get("ability")).equals("2")){
                abilityNew.setAbt(abToString(param.get("ability")));
                abilityNew.setAbt2(Double.valueOf(param.get("sys")));
                abilityNew.setAbt1(Double.valueOf(doPaperList.get(selfevaList.size()-1).getScorea()));
                abilityNew.setAbt3(Double.valueOf(doPaperList.get(selfevaList.size()-1).getScorec()));
            }
            if(abToString(param.get("ability")).equals("3")){
                abilityNew.setAbt(abToString(param.get("ability")));
                abilityNew.setAbt3(Double.valueOf(param.get("sys")));
                abilityNew.setAbt2(Double.valueOf(doPaperList.get(selfevaList.size()-1).getScoreb()));
                abilityNew.setAbt1(Double.valueOf(doPaperList.get(selfevaList.size()-1).getScorea()));
            }
            abilityNew.setStuid((String)userid);
            abilityNew.setTime(df.format(System.currentTimeMillis()));
            abilityNew.setPaperid(paperid);
            abilityNewService.AbilityNew(abilityNew);
        }else{
            if(abToString(param.get("ability")).equals("1")){
                abilityNew.setAbt(abToString(param.get("ability")));
                abilityNew.setAbt1(Double.valueOf(param.get("sys")));
                abilityNew.setAbt2(abilityNewList.get(abilityNewList.size()-1).getAbt2());
                abilityNew.setAbt3(abilityNewList.get(abilityNewList.size()-1).getAbt3());
            }
            if(abToString(param.get("ability")).equals("2")){
                abilityNew.setAbt(abToString(param.get("ability")));
                abilityNew.setAbt2(Double.valueOf(param.get("sys")));
                abilityNew.setAbt1(abilityNewList.get(abilityNewList.size()-1).getAbt1());
                abilityNew.setAbt3(abilityNewList.get(abilityNewList.size()-1).getAbt3());
            }
            if(abToString(param.get("ability")).equals("3")){
                abilityNew.setAbt(abToString(param.get("ability")));
                abilityNew.setAbt3(Double.valueOf(param.get("sys")));
                abilityNew.setAbt2(abilityNewList.get(abilityNewList.size()-1).getAbt2());
                abilityNew.setAbt1(abilityNewList.get(abilityNewList.size()-1).getAbt1());
            }
            abilityNew.setStuid((String)userid);
            abilityNew.setTime(df.format(System.currentTimeMillis()));
            abilityNew.setPaperid(paperid);
            abilityNewService.AbilityNew(abilityNew);
        }

        nego.setStuid((String)userid);
        nego.setPaperid(paperid);
        nego.setAbility(abToString(param.get("ability")));
        nego.setNegopt("7");
        nego.setMarka("学生选择");
        nego.setMarkb(param.get("isagree"));
        nego.setEndtime(df.format(System.currentTimeMillis()));
        System.out.println("学生的选择是："+param.get("isagree"));//ok

        log0.info("做决定"+"stuid"+userid+"/"+"ab:"+param.get("ability")+"/"+param.get("isagree"));

        negoService.insertNego(nego);

    }
    //协商结果为不同意
    @PostMapping("/negodisagree")
    public void negodisDecision(@RequestParam Map<String, String> param, HttpSession session){

        Object userid;
        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id

        Logger log0 = LogUtils.getBussinessLogger();
        Nego nego=new Nego();

        String paperid=transPaperid(param.get("paperid"));
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        nego.setStuid((String)userid);
        nego.setPaperid(paperid);
        nego.setAbility(abToString(param.get("ability")));
        nego.setNegopt("7");
        nego.setMarkb(param.get("isagree"));
        nego.setMarka("学生折衷");
        nego.setMarkc(param.get("sc"));
        nego.setEndtime(df.format(System.currentTimeMillis()));
        System.out.println("学生的选择是："+param.get("isagree"));//ok

        log0.info("做决定-折衷决定"+"stuid"+userid+"/"+"ab:"+param.get("ability")+"/"+param.get("isagree"));
        negoService.insertNego(nego);

    }
    //折衷
    @PostMapping("/compromise")
    public double negoComp(@RequestParam Map<String, String> param, HttpSession session){

        Object userid;
        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id

        Logger log0 = LogUtils.getBussinessLogger();
        Nego nego=new Nego();

        String paperid=transPaperid(param.get("paperid"));

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        nego.setStuid((String)userid);
        nego.setPaperid(paperid);
        nego.setAbility(abToString(param.get("ability")));
        nego.setNegopt("7");
        nego.setMarka("学生选择折衷");
        nego.setEndtime(df.format(System.currentTimeMillis()));
        System.out.println("学生的选择是："+param.get("isagree"));//ok

        log0.info("做决定-学生选择折衷"+"stuid"+userid+"/"+"ab:"+param.get("ability")+"/"+param.get("isagree"));

        negoService.insertNego(nego);
        double cor=compare(Double.valueOf(param.get("self")),Double.valueOf(param.get("score")));
        return cor;
    }
    //回答学生提问
    @PostMapping("/stuAsk")
    public Map negostuAsk(@RequestParam Map<String, String> param, HttpSession session){
        Object userid;
        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id

        Nego nego=new Nego();
        Logger logaskstu = LogUtils.getBussinessLogger();

        String paperid=transPaperid(param.get("paperid"));

        List<Nego> negoList=negoService.findNegot((String)userid,paperid,"5");

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        nego.setStuid((String)userid);
        nego.setPaperid(paperid);
        nego.setAbility(abToString(param.get("ability")));
        nego.setNegopt("5");
        nego.setMarka("查看学生提问");
        nego.setEndtime(df.format(System.currentTimeMillis()));
        negoService.insertNego(nego);

        logaskstu.info("请求本周学生问题"+"stuid"+userid+"/");

        List<StuQues> stuQues=stuQueService.findstuask(paperid);
        Map stuques=new HashMap();
        stuques.put("stuQues",stuQues.get(0).getStuques());
        for(Nego s:negoList){
            stuques.put(s.getId(),s.getMarkb());
        }

        return stuques;
    }
    //提交回答学生提问
    @PostMapping("/anwAsk")
    public void negoanswerAsk(@RequestParam Map<String, String> param, HttpSession session){
        Object userid;
        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id

        Nego nego=new Nego();
        Logger logaskstu = LogUtils.getBussinessLogger();

        String paperid=transPaperid(param.get("paperid"));

        List<Nego> negoList=negoService.findNegot((String)userid,paperid,"5");

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        nego.setStuid((String)userid);
        nego.setPaperid(paperid);
        nego.setAbility(abToString(param.get("ab")));
        nego.setNegopt("5");
        nego.setMarka("填写学生提问");
        nego.setMarkb(param.get("stuawr"));
        nego.setEndtime(df.format(System.currentTimeMillis()));
        negoService.insertNego(nego);

        logaskstu.info("回答本周学生问题"+"stuid"+userid+"/"+param.get("ab")+"/"+"学生回答内容："+param.get("stuawr"));

    }
    //提问题
    @PostMapping("/returnstuQues")
    public Map negostuQues(@RequestParam Map<String, String> param, HttpSession session){
        Object userid;
        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id

        Logger logaskque = LogUtils.getBussinessLogger();

        String paperid=transPaperid(param.get("paperid"));
        List<Nego> negoList=negoService.findNegot((String)userid,paperid,"6");

        logaskque.info("学生的提问"+"stuid"+userid+"/"+param.get("ab")+"/"+paperid+"/");

        Map stuask=new HashMap();

        for(int i=0;i<negoList.size();i++){
            stuask.put(i,negoList.get(i).getMarkb());
        }

            return stuask;
    }

    //提问题
    @PostMapping("/stuQues")
    public void negoanswerQues(@RequestParam Map<String, String> param, HttpSession session){
        Object userid;
        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id

        Nego nego=new Nego();
        Logger logaskque = LogUtils.getBussinessLogger();

        String paperid=transPaperid(param.get("paperid"));

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        nego.setStuid((String)userid);
        nego.setPaperid(paperid);
        nego.setAbility(abToString(param.get("ab")));
        nego.setNegopt("6");
        nego.setMarka("提问题");
        nego.setMarkb(param.get("stuques"));
        nego.setEndtime(df.format(System.currentTimeMillis()));
        negoService.insertNego(nego);

        logaskque.info("学生的提问"+"stuid"+userid+"/"+param.get("ab")+"/"+paperid+"/"+"学生回答内容："+param.get("stuques"));

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

    public String toStringab(String ab){

        if(ab.equals("1")){
            ab="理论能力";
        }else if(ab.equals("2")){
            ab="应用能力";
        }else {
            ab="分析能力";
        }
        return ab;
    }

    public String timeTopaperid(String times){
        String paperid=null;
        if(times.equals("2018-10-10 SRL & MOOC")){
            paperid="srl02";
        }else if(times.equals("2018-10-17 SRL & flipped classrooms")){
            paperid="srl03";
        }else if(times.equals("2018-10-24 SRL & blended learning")){
            paperid="srl04";
        }
        return paperid;
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
    //将abc转换为对/错/不知道
    public String OptoString(String abc){

        String str=null;
        if(abc.equals("a")){
            str="正确";
        }else if(abc.equals("b")){
            str="错误";
        }else {
            str="不知道";
        }
        return str;
    }
    //判断高估/低估
    public double compare(double self,double sys){

        double eva=0;

        double selfeva = self;
        double syseva = sys;

        if(Math.abs(selfeva-syseva)==1||Math.abs(selfeva-syseva)==0){
            eva=0;
        }else if((selfeva-syseva==2||(selfeva-syseva==3))){
            eva=syseva+Math.ceil((selfeva-syseva)/2);
        }else if(selfeva-syseva>3){
            eva=syseva+Math.ceil((selfeva-syseva)/2);
        }else if((syseva-selfeva==2||(syseva-selfeva==3))){
            eva=selfeva +Math.ceil((syseva-selfeva)/2);
        }else if(syseva-selfeva>3){
            eva=selfeva +Math.ceil((syseva-selfeva)/2);
        }

        return eva;
    }

    //获取到当前的文章id
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
    public String transtimes(String s){

        String paperid=null;
        if(s.equals("2018-10-10 SRL")){
            paperid="1";
        }else if(s.equals("2018-10-17 SRL")){
            paperid="2";
        }else if(s.equals("2018-10-24 SRL")){
            paperid="3";
        }else if(s.equals("2018-11-7 SRL")){
            paperid="4";
        }else if(s.equals("2018-11-14 SRL")){
            paperid="5";
        }else if(s.equals("2018-11-21 SRL")){
            paperid="6";
        }else if(s.equals("2018-12-12 SRL")){
            paperid="7";
        }else if(s.equals("2018-12-19 SRL")){
            paperid="8";
        }else if(s.equals("2018-12-26 SRL")){
            paperid="9";
        }
        return paperid;
    }
    public static String format(double value) {

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        /*
         * setMinimumFractionDigits设置成2
         *
         * 如果不这么做，那么当value的值是100.00的时候返回100
         *
         * 而不是100.00
         */
        nf.setMinimumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.HALF_UP);
        /*
         * 如果想输出的格式用逗号隔开，可以设置成true
         */
        nf.setGroupingUsed(false);
        return nf.format(value);
    }
}


