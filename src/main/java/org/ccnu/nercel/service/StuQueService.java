package org.ccnu.nercel.service;

import org.ccnu.nercel.bean.StuQues;
import org.ccnu.nercel.repo.StuQueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xiaotong
 * @createTime 20181023 下午3:17
 * @description 学生问题
 */
@Service
public class StuQueService {

    @Autowired
    private StuQueRepo stuQueRepo;

    public List<StuQues> findstuask(String paperid){

        List<StuQues> stuQuesList=stuQueRepo.findByPaperid(paperid);

        return stuQuesList;
    }
}
