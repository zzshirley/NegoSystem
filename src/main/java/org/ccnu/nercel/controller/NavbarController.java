package org.ccnu.nercel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Xiaotong
 * @createTime 20180926 下午1:28
 * @description 注销
 */
@Controller
public class NavbarController {
    //退出
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }
}
