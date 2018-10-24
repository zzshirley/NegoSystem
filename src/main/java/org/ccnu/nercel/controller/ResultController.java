package org.ccnu.nercel.controller;

import org.ccnu.nercel.bean.Ability;
import org.ccnu.nercel.bean.DoPaper;
import org.ccnu.nercel.bean.Nego;
import org.ccnu.nercel.bean.Selfeva;
import org.ccnu.nercel.config.WebSecurityConfig;
import org.ccnu.nercel.service.AbilityService;
import org.ccnu.nercel.service.ResultService;
import org.ccnu.nercel.service.SelfevaService;
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

    @RequestMapping("/result")
    public String result(@RequestParam String times, Model model, HttpSession session){

        Object userid;

        Nego nego=new Nego();

        userid = session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id

        String date=timeTodate(times);//pagehead;
        String paperid=timeTopaperid(times);//根据次数转换为paperid；

        List<Selfeva> selfevaList=selfevaService.getSelfEvaBytime((String)userid,times);//学生自我评价list

        List<Ability> abilityList=abilityService.findAbilityTimes((String)userid,times);//系统评价

        List<DoPaper> doPaperList=resultService.getpaperAndpaperid((String)userid,paperid);

        if(selfevaList.isEmpty()||doPaperList.isEmpty()){
            model.addAttribute("checkError",true);
            return "testindex";
        }else {
            model.addAttribute("date",date);

            String selfa=null;
            String selfb=null;
            String selfc=null;

            String sysa=null;
            String sysb=null;
            String sysc=null;

            String grade=null;//系统判断（高估/低估/轻微高估/轻微高估。。）
            if(selfevaList.size()>0){
                if(times.equals("1")||times.equals("2")){
                    selfa=StringtoNum(selfevaList.get(selfevaList.size()-1).getAbt1());
                    selfb=StringtoNum(selfevaList.get(selfevaList.size()-1).getAbt2());
                    selfc=StringtoNum(selfevaList.get(selfevaList.size()-1).getAbt3());
                }else {
                    selfa=selfevaList.get(selfevaList.size()-1).getAbt1();
                    selfb=selfevaList.get(selfevaList.size()-1).getAbt2();
                    selfc=selfevaList.get(selfevaList.size()-1).getAbt3();
                }

                model.addAttribute("selfscorea",selfa);
                model.addAttribute("selfscoreb",selfb);
                model.addAttribute("selfscorec",selfc);
            }

            if(times.equals("1")||times.equals("2")){
                if(abilityList.size()>0){
                    sysa=StringtoNum(abilityList.get(abilityList.size()-1).getAbt1());
                    sysb=StringtoNum(abilityList.get(abilityList.size()-1).getAbt2());
                    sysc=StringtoNum(abilityList.get(abilityList.size()-1).getAbt3());
                }
            }else{
                if(doPaperList.size()>0){
                    sysa=String.valueOf(doPaperList.get(doPaperList.size()-1).getScorea());
                    sysb=String.valueOf(doPaperList.get(doPaperList.size()-1).getScoreb());
                    sysc=String.valueOf(doPaperList.get(doPaperList.size()-1).getScorec());
                }
            }

            model.addAttribute("sysscorea",sysa);
            model.addAttribute("sysscoreb",sysb);
            model.addAttribute("sysscorec",sysc);
            if(sysa!=null&&selfa!=null){
                model.addAttribute("underorovera",compare(selfa,sysa));
            }
            if(sysb!=null&&selfb!=null){
                model.addAttribute("underoroverb",compare(selfb,sysb));
            }
            if(sysc!=null&&selfc!=null){
                model.addAttribute("underoroverc",compare(selfc,sysc));
            }
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
        }
        return paperid;
    }
    //判断高估/低估
    public String compare(String self,String sys){

        String eva=null;

        int selfeva = Integer.valueOf(self).intValue();
        int syseva = Integer.valueOf(sys).intValue();

        if(Math.abs(selfeva-syseva)==1||Math.abs(selfeva-syseva)==0){
            eva="一致";
        }else if((selfeva-syseva==2||(selfeva-syseva==3))){
            eva="轻微高估";
        }else if(selfeva-syseva>3){
            eva="严重高估";
        }else if((syseva-selfeva==2||(syseva-selfeva==3))){
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
