package org.ccnu.nercel.controller;

import java.util.ArrayList;
import java.util.List;

import org.ccnu.nercel.bean.Question;
import org.ccnu.nercel.service.LoginService;
import org.ccnu.nercel.service.QuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
*@author xiaotong
*@version 2018年8月1日 下午9:02:30
*/

@Controller
@RequestMapping("/")
public class QuesController {
	
	@Autowired
	private QuesService quesService;
	
	@RequestMapping("/questionnaire")
	public String questionnaire(Model model) {				
		Question ques = new Question();
		ques.setPaperid("0");
		List<Question> listques=quesService.QuesList(ques);
		model.addAttribute("ques", listques);
		return "questionnaire";		
	}
	
	@PostMapping("/quessubmit")
	public String quessubmit() {
		return "questionnaire";
		
	}

}
