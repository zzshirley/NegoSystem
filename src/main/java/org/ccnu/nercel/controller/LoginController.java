package org.ccnu.nercel.controller;

import org.ccnu.nercel.service.LoginService;

import javax.servlet.http.HttpSession;
import org.ccnu.nercel.bean.User;
import org.ccnu.nercel.config.WebSecurityConfig;
import org.ccnu.nercel.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

/**
 * @author xiaotong
 * @
 * 
 */
@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@Autowired
	private RegisterService registerService;


	@GetMapping("/")
	public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY)String account,Model model){
		return "login";
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login(){				
		return "login";
	}
	
	@PostMapping("/loginVerify")
    public String loginVerify(String stuid,String password,HttpSession session,Model model){
        User user = new User();
        user.setStuid(stuid);
        user.setPassword(password);
        boolean verify = loginService.verifyLogin(user);
		if (verify) {
			session.setAttribute(WebSecurityConfig.SESSION_KEY, stuid);			
			return "redirect:index";
		} else {	
			model.addAttribute("loginError",true);
			return "login";
		}
    }
	@PostMapping(value = "/loginregister")
	public String registerverify(String stuid,String password,String stuname,String major,String gender){
		User user = new User();
		user.setStuid(stuid);
		user.setPassword(password);
		user.setStuname(stuname);

		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		user.setRegdate(df.format(System.currentTimeMillis()));
		if(major.equals("计算机应用技术")){
			user.setMajor(0);
		}else if (major.equals("教育技术学")){
			user.setMajor(1);
		}else if (major.equals("现代教育技术")){
			user.setMajor(2);
		}else if (major.equals("软件工程")){
			user.setMajor(3);
		}else if (major.equals("数据科学与大数据技术")){
			user.setMajor(4);
		}

		if(gender.equals("男孩子")){
			user.setGender("1");
		}else {
			user.setGender("0");
		}

		registerService.stuRegister(user);
		return "login";
	}

}
