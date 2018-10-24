package org.ccnu.nercel.controller;

import org.ccnu.nercel.Util.LogUtils;
import org.ccnu.nercel.bean.Nego;
import org.ccnu.nercel.bean.Selfeva;
import org.ccnu.nercel.config.WebSecurityConfig;
import org.ccnu.nercel.service.AbilityService;
import org.ccnu.nercel.service.NegoService;
import org.ccnu.nercel.service.SelfevaService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Xiaotong
 * @createTime 20181010 下午3:11
 * @description 自我评价
 */


@Controller
@RequestMapping("/")
public class SelfevaController {

    @Autowired
    private SelfevaService selfevaService;


    public String time;


    @RequestMapping("/selfevaluate")
    public String selfeva(@RequestParam String times, HttpSession session, Model model){

        Object userid;
        userid=session.getAttribute(WebSecurityConfig.SESSION_KEY);
        time=times;
        List<Selfeva> selfevas=selfevaService.getSelfEvaBytime((String)userid,time);
        if(selfevas.isEmpty()){
            return "selfevaluate";
        }else {
            model.addAttribute("selfevaError",true);
            return "testindex";
        }
    }

    @PostMapping("/subself")
    public String subself(String abt1,String abt2,String abt3, HttpSession session){

        Selfeva selfeva=new Selfeva();

        Object userid;
        userid=session.getAttribute(WebSecurityConfig.SESSION_KEY);

        List select=new ArrayList();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        selfeva.setStuid((String)userid);
        selfeva.setAbt1(abt1);
        selfeva.setAbt2(abt2);
        selfeva.setAbt3(abt3);
        selfeva.setTimes(time);
        selfeva.setEndtime(df.format(System.currentTimeMillis()));

        selfevaService.selfeva(selfeva);

        return "testindex";
    }
}
