package org.ccnu.nercel.service;

import org.ccnu.nercel.bean.DoPaper;
import org.ccnu.nercel.repo.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xiaotong
 * @createTime 20180918 下午3:23
 * @description result
 */
@Service
public class ResultService {

    @Autowired
    private ResultRepo resultRepo;

    public List<DoPaper> getdopaper(String stuid){

        List<DoPaper> doPaperList=resultRepo.findByStuid(stuid);

        return doPaperList;
    }
}
