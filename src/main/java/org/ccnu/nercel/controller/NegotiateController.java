package org.ccnu.nercel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiaotong
 * @createTime 20180908 下午3:05
 * @description 协商
 */
@Controller
@RequestMapping("/")
public class NegotiateController {

    @RequestMapping("/negotiate")
    public ModelAndView negotiate() {
        ModelAndView mv = new ModelAndView("negotiate");
        return mv;
    }

    @PostMapping("/getScore")
    public Map getScore(@RequestParam Map<String, String> param) {
        System.out.println(param.get("btnNum"));
        Map<String, String> ret = new HashMap<>();
        ret.put("msg", "你说啥呢？");
        return ret;
    }
}


