package org.ccnu.nercel.controller;

import java.util.*;

import org.ccnu.nercel.Util.LogUtils;
import org.ccnu.nercel.bean.Ability;
import org.ccnu.nercel.bean.DoPaper;
import org.ccnu.nercel.bean.Papersdo;
import org.ccnu.nercel.config.WebSecurityConfig;
import org.ccnu.nercel.service.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
*@author xiaotong              
*@version ${date} ${time}                  
*/                  

@RestController
public class IndexController {


	@Autowired
	private DoPaperService dopaperservice;

	@Autowired
	private ResultService resultService;

	@Autowired
	private PaperQuesOptService paperQuesOptService;

	@Autowired
	private PapersdoService papersdoService;


	@RequestMapping("/index")
	public ModelAndView index(HttpSession session) {

		ModelAndView mv = new ModelAndView("index");

		/*Papersdo papersdo=new Papersdo();
		Logger logger= LogUtils.getBussinessLogger();
		List<DoPaper> dopaperlist=resultService.getAllpaper();


		int score=0;
		int scorea=0;
		int scoreb=0;
		int scorec=0;

		for(int i=0;i<dopaperlist.size();i++){

			String queslist=dopaperlist.get(i).getQueslist();
			String stulist=dopaperlist.get(i).getPaperanswer();
			System.out.println(queslist);

			if(queslist==null){
				System.out.println("null");
			}else {
				List isTrue=new ArrayList();
				List<String> strsToList = java.util.Arrays.asList(queslist.split(","));
				List<String> strsToList0 = java.util.Arrays.asList(stulist.split(","));
				for(int j=0;j<strsToList.size();j++){
					isTrue.add(paperQuesOptService.quesOptions(Integer.valueOf(strsToList.get(j)).intValue()).get(0).getIstrueoption());
					//System.out.println(strsToList0);
				}
				score=mark(strsToList0,isTrue);
				scorea=mark(strsToList0.subList(0,5),isTrue.subList(0,5));
				scoreb=mark(strsToList0.subList(5,10),isTrue.subList(5,10));
				papersdo.setPaperstate(isTrue.toString());
				System.out.println(isTrue);
			}
			papersdo.setBegintime(dopaperlist.get(i).getBegintime());
			papersdo.setEndtime(dopaperlist.get(i).getEndtime());
			papersdo.setPaperanswer(dopaperlist.get(i).getPaperanswer());
			papersdo.setPaperid(dopaperlist.get(i).getPaperid());
			papersdo.setQueslist(dopaperlist.get(i).getQueslist());
			papersdo.setScore(score);
			papersdo.setScorea(scorea);
			papersdo.setScoreb(scoreb);
			papersdo.setScorec(score-scorea-scoreb);
			papersdo.setStuid(dopaperlist.get(i).getStuid());
			System.out.println(dopaperlist.get(i).getId());
			System.out.println(score);
			System.out.println(scorea);
			System.out.println(scoreb);
			System.out.println(score-scorea-scoreb);
			logger.info("id:"+dopaperlist.get(i).getId()+"score:"+score+"scorea"+scorea+"scoreb:"+scoreb+"scorec:"+(score-scorea-scoreb)+" "+dopaperlist.get(i).getPaperanswer());
			papersdoService.papersdo(papersdo);
		}*/
		return mv;
	}

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

}