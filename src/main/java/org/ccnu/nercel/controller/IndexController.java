package org.ccnu.nercel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ccnu.nercel.bean.Ability;
import org.ccnu.nercel.bean.DoPaper;
import org.ccnu.nercel.config.WebSecurityConfig;
import org.ccnu.nercel.service.AbilityService;
import org.ccnu.nercel.service.ResultService;
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

	
	@RequestMapping("/index")
	public ModelAndView index(HttpSession session) {


		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

}