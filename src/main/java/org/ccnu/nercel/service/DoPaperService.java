package org.ccnu.nercel.service;

import org.ccnu.nercel.bean.DoPaper;
import org.ccnu.nercel.repo.DoPaperRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Xiaotong
 * @createTime 20180905 上午10:10
 * @description 试卷答案写入数据库
 */
@Service
public class DoPaperService {

    @Autowired
    private DoPaperRepo doPaperRepo;

    public void saveStuAnswer(DoPaper doPaper){

           doPaperRepo.save(doPaper);

    }
}
