package org.ccnu.nercel.controller;

import org.ccnu.nercel.service.LoginService;

import javax.servlet.http.HttpSession;
import org.ccnu.nercel.bean.User;
import org.ccnu.nercel.config.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

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
	
	@GetMapping("/")
	public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY)String account,Model model){
		return "login";
	}
	
	@RequestMapping("/login")
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
			return "/index";
		} else {	
			model.addAttribute("loginError",true);
			return "redirect:/login";
		}
    }
}
