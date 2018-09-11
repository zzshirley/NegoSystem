package org.ccnu.nercel.controller;

import org.ccnu.nercel.bean.DoPaper;
import org.ccnu.nercel.bean.Paper;
import org.ccnu.nercel.bean.PaperQuesOption;
import org.ccnu.nercel.bean.paperQues;
import org.ccnu.nercel.config.WebSecurityConfig;
import org.ccnu.nercel.service.DoPaperService;
import org.ccnu.nercel.service.PaperQuesOptService;
import org.ccnu.nercel.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * 试卷
 * @author xiaotong
 * @version 2018年9月2日 下午10:32:13
 */

@Controller
@RequestMapping("/")
public class PaperCotroller {

    public static int  optionnum=5;

    public static List<PaperQuesOption> num1options;//第一道题选项

    public static List<PaperQuesOption> num2options;//第二道题选项

    public static List<PaperQuesOption> num3options;//第三道题

    public static ArrayList judgeopt;

    @Autowired
    private PaperService paperService;

    @Autowired
    private PaperQuesOptService paperQuesOptService;
    
    @Autowired
    private DoPaperService dopaperservice;

    @RequestMapping("/readingtest")
    public String getpaper(Model model){
        Paper paper = new Paper();
        paper.setPaperid("srl01");
        ArrayList<String> quesnum= new ArrayList<>();
        ArrayList questions=new ArrayList();

        List<paperQues> paperQuesList = paperService.paperQues(paper.getPaperid());

        for(paperQues queslist:paperQuesList){

            quesnum.add(queslist.getQuestion());
            questions.add(queslist.getQuescontent());

        }
        System.out.println(questions+"\n");

        model.addAttribute("papers",questions);

        List<PaperQuesOption> paperQuesOptionList11 =paperQuesOptService.quesOptions(quesnum.get(0),"1");
        List<PaperQuesOption> paperQuesOptionList21 =paperQuesOptService.quesOptions(quesnum.get(1),"1");
        List<PaperQuesOption> paperQuesOptionList31 =paperQuesOptService.quesOptions(quesnum.get(2),"1");

        List<PaperQuesOption> paperQuesOptionList10 =paperQuesOptService.quesOptions(quesnum.get(0),"0");
        List<PaperQuesOption> paperQuesOptionList20=paperQuesOptService.quesOptions(quesnum.get(1),"0");
        List<PaperQuesOption> paperQuesOptionList30 =paperQuesOptService.quesOptions(quesnum.get(2),"0");


        num1options=findoption(paperQuesOptionList11,paperQuesOptionList10,2);
        num2options=findoption(paperQuesOptionList21,paperQuesOptionList20,1);
        num3options=findoption(paperQuesOptionList31,paperQuesOptionList30,2);

        judgeopt=new ArrayList();

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

        return "readingtest";
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
    //提交问卷
    @PostMapping("/testsubmit")
    public String testsubmit(@RequestParam Map req, HttpSession session){

        Object userid;
        userid=session.getAttribute(WebSecurityConfig.SESSION_KEY);

        DoPaper doPaper=new DoPaper();

        ArrayList answer=new ArrayList();//学生选择答案转换成字符串
        for(Iterator s=req.keySet().iterator();s.hasNext();){
            Object key = s.next();
            answer.add(req.get(key));
        }

        String stuanswer=String.join(",",answer);

        int score=mark(answer,judgeopt);

        doPaper.setPaperid("srl01");
        doPaper.setStuid((String)userid);
        doPaper.setScore(score);
        doPaper.setPaperanswer(stuanswer);
        doPaper.setPaperstate("1");
        
        dopaperservice.saveStuAnswer(doPaper);


        System.out.println("学生选择的答案："+answer);

        System.out.println("你的分数是："+score);

        return "index";
    }
    //计算分数
    public int mark(ArrayList answer,ArrayList judgeopt){

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
}
