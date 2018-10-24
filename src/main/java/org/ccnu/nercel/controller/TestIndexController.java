package org.ccnu.nercel.controller;

import org.ccnu.nercel.repo.PaperRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Xiaotong
 * @createTime 20181022 下午3:31
 * @description 阅读测试/自我评价/查看成绩共同入口
 */
@Controller
public class TestIndexController {

    private PaperRepo paperRepo;

    @RequestMapping("/testindex")
    public String testIndex(){

        return "testindex";

    }
}
