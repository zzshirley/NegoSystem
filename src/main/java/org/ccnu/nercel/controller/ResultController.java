package org.ccnu.nercel.controller;

import org.ccnu.nercel.Util.LogUtils;
import org.ccnu.nercel.bean.*;
import org.ccnu.nercel.config.WebSecurityConfig;
import org.ccnu.nercel.service.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Xiaotong
 * @createTime 2018/0918 上午11:06
 * @description 测试历史/能力评估
 */
@Controller
public class ResultController {

    @Autowired
    private ResultService resultService;

    @Autowired
    private SelfevaService selfevaService;

    @Autowired
    private AbilityService abilityService;

    @Autowired
    private AbilityNewService abilityNewService;

    @Autowired
    private SelfevaNewService selfevaNewService;

    @RequestMapping("/result")
    public String result(@RequestParam String times, Model model, HttpSession session){

        Object userid;

        Logger logresult = LogUtils.getBussinessLogger();

        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id

        String date=timeTodate(times);//pagehead;
        String paperid=timeTopaperid(times);//根据次数转换为paperid；

        List<Selfeva> selfevaList=selfevaService.getSelfEvaBytime((String)userid,times);//学生自我评价list

        List<Ability> abilityList=abilityService.findAbilityTimes((String)userid,times);//系统评价

        List<AbilityNew> abilityNewList=abilityNewService.findAbilitynew((String)userid,timeTopaperid(times));//更新过的成绩

        List<SelfevaNew> selfevaNewList=selfevaNewService.findnewSelf((String)userid,timeTopaperid(times));

        List<DoPaper> doPaperList=resultService.getpaperAndpaperid((String)userid,paperid);

        if(times.equals("0")){
            return "result";
        }

        if(selfevaList.isEmpty()||doPaperList.isEmpty()){
            model.addAttribute("checkError",true);
            return "testindex";
        }else {
            model.addAttribute("date",date);

            double selfa=100;
            double selfb=100;
            double selfc=100;

            double sysa=100;
            double sysb=100;
            double sysc=100;

            String grade=null;//系统判断（高估/低估/轻微高估/轻微高估。。
            if(selfevaNewList.size()>0){
                selfa=selfevaNewList.get(selfevaNewList.size()-1).getAbt1();
                selfb=selfevaNewList.get(selfevaNewList.size()-1).getAbt2();
                selfc=selfevaNewList.get(selfevaNewList.size()-1).getAbt3();

                model.addAttribute("selfscorea",selfa);
                model.addAttribute("selfscoreb",selfb);
                model.addAttribute("selfscorec",selfc);

            }else if(selfevaList.size()>0){
                if(times.equals("1")||times.equals("2")){
                    selfa=Double.valueOf(StringtoNum(selfevaList.get(selfevaList.size()-1).getAbt1()));
                    selfb=Double.valueOf(StringtoNum(selfevaList.get(selfevaList.size()-1).getAbt2()));
                    selfc=Double.valueOf(StringtoNum(selfevaList.get(selfevaList.size()-1).getAbt3()));
                }else {
                    selfa=Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt1());
                    selfb=Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt2());
                    selfc=Double.valueOf(selfevaList.get(selfevaList.size()-1).getAbt3());
                }

                model.addAttribute("selfscorea",selfa);
                model.addAttribute("selfscoreb",selfb);
                model.addAttribute("selfscorec",selfc);
            }

            if(times.equals("1")||times.equals("2")){
                if(abilityNewList.size()>0){
                    sysa=Double.valueOf(abilityNewList.get(abilityNewList.size()-1).getAbt1());
                    sysb=Double.valueOf(abilityNewList.get(abilityNewList.size()-1).getAbt2());
                    sysc=Double.valueOf(abilityNewList.get(abilityNewList.size()-1).getAbt3());
                }else if(abilityList.size()>0){
                    sysa=Double.valueOf(StringtoNum(abilityList.get(abilityList.size()-1).getAbt1()));
                    sysb=Double.valueOf(StringtoNum(abilityList.get(abilityList.size()-1).getAbt2()));
                    sysc=Double.valueOf(StringtoNum(abilityList.get(abilityList.size()-1).getAbt3()));
                }
            }else{
                if(abilityNewList.size()>0){
                    sysa=Double.valueOf(abilityNewList.get(abilityNewList.size()-1).getAbt1());
                    sysb=Double.valueOf(abilityNewList.get(abilityNewList.size()-1).getAbt2());
                    sysc=Double.valueOf(abilityNewList.get(abilityNewList.size()-1).getAbt3());
                }else if(doPaperList.size()>0){
                    sysa=Double.valueOf(doPaperList.get(doPaperList.size()-1).getScorea());
                    sysb=Double.valueOf(doPaperList.get(doPaperList.size()-1).getScoreb());
                    sysc=Double.valueOf(doPaperList.get(doPaperList.size()-1).getScorec());
                }
            }

            model.addAttribute("sysscorea",sysa);
            model.addAttribute("sysscoreb",sysb);
            model.addAttribute("sysscorec",sysc);
            if(sysa!=100&&selfa!=100){
                model.addAttribute("underorovera",compare(selfa,sysa));
            }
            if(sysb!=100&&selfb!=100){
                model.addAttribute("underoroverb",compare(selfb,sysb));
            }
            if(sysc!=100&&selfc!=100){
                model.addAttribute("underoroverc",compare(selfc,sysc));
            }
            logresult.info("查看成绩"+" "+paperid+" "+userid);
            return "result";
        }


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

    public String timeTodate(String times){
        String date=null;
        if(times.equals("1")){
            date="2018-10-10 SRL&MOOC";
        }else if(times.equals("2")){
            date="2018-10-17 SRL&flipped classrooms";
        }else if(times.equals("3")){
            date="2018-10-24 SRL&blended learning";
        }else if(times.equals("4")){
            date="2018-11-7 SRL&games";
        }else if(times.equals("5")){
            date="2018-11-14 SRL&mobile learning tools";
        }else if(times.equals("6")){
            date="2018-11-21 SRL&mobile learning analytics";
        }else if(times.equals("7")){
            date="2018-12-12 SRL&ITS";
        }else if(times.equals("8")){
            date="2018-12-19 SRL&teachable agents";
        }else if(times.equals("9")){
            date="2018-12-26 SRL&behavioral analysis";
        }
        return date;
    }

    public String timeTopaperid(String times){
        String paperid=null;
        if(times.equals("1")){
            paperid="srl02";
        }else if(times.equals("2")){
            paperid="srl03";
        }else if(times.equals("3")){
            paperid="srl04";
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
    //判断高估/低估
    public String compare(Double self,Double sys){

        String eva=null;

        double selfeva = self;
        double syseva = sys;

        if(Math.abs(selfeva-syseva)<=1){
            eva="一致";
        }else if((selfeva-syseva<=3)&&(selfeva-syseva>0)){
            eva="轻微高估";
        }else if(selfeva-syseva>3){
            eva="严重高估";
        }else if((syseva-selfeva<=3)&&(syseva-selfeva>0)){
            eva="轻微低估";
        }else if(syseva-selfeva>3){
            eva="严重低估";
        }

        return eva;
    }

    public String StringtoNum(String abt){
        String grade = null;

        if(abt.equals("完全理解")){
            grade="8";
        }else if(abt.equals("理解")){
            grade="6";
        }else if (abt.equals("一知半解")){
            grade="4";
        }else if(abt.equals("不理解")){
            grade="2";
        }else if(abt.equals("完全不理解")){
            grade="0";
        }
        return grade;
    }
}
