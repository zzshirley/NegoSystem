package org.ccnu.nercel.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@author xiaotong
 *@version 2018年8月31日 下午10:23:20
 */

@Controller
public class ResourceController {

    @RequestMapping("/resource")
    public String resource() {
        return "resource";
    }
}
