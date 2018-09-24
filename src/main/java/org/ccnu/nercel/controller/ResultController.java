package org.ccnu.nercel.controller;

import org.ccnu.nercel.bean.DoPaper;
import org.ccnu.nercel.config.WebSecurityConfig;
import org.ccnu.nercel.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("result")
    public String result(Model model,HttpSession session){

        Object userid;
        userid=session.getAttribute(WebSecurityConfig.SESSION_KEY);//用户id
        List<DoPaper> resultList=resultService.getdopaper((String)userid);
        model.addAttribute("resultList",resultList);
        return "result";
    }
}
