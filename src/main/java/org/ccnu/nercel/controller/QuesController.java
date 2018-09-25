package org.ccnu.nercel.controller;

import java.util.*;

import org.ccnu.nercel.bean.DoQues;
import org.ccnu.nercel.bean.Question;
import org.ccnu.nercel.bean.User;
import java.text.SimpleDateFormat;
import org.ccnu.nercel.config.WebSecurityConfig;
import org.ccnu.nercel.service.DoQuesService;
import org.ccnu.nercel.service.QuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 问卷调查
*@author xiaotong
*@version 2018年8月1日 下午9:02:30
*/

@Controller
@RequestMapping("/")
public class QuesController {
	
	@Autowired
	private QuesService quesService;

	@Autowired
	private DoQuesService doQuesService;

	@RequestMapping("/questionnaire")
	public String questionnaire(Model model) {				
		Question ques = new Question();
		ques.setPaperid("0");
		List<Question> listques=quesService.QuesList(ques);
		model.addAttribute("ques", listques);
		return "questionnaire";		
	}
	
	@PostMapping("/quessubmit")
	public String quessubmit(@RequestParam Map req, HttpSession session) {

		Object userid;
		userid=session.getAttribute(WebSecurityConfig.SESSION_KEY);
		List answer=new ArrayList();//学生选择答案转换成字符串
		for(Iterator s=req.keySet().iterator();s.hasNext();){
			Object key = s.next();
			answer.add(req.get(key));
		}
		String stuanswer=String.join(",",answer);

		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		DoQues doques=new DoQues();
		doques.setStuid((String)userid);
		doques.setStuanswer(stuanswer);
		doques.setEndtime(df.format(System.currentTimeMillis()));
		doQuesService.Doques(doques);
		return "index";
		
	}

}
