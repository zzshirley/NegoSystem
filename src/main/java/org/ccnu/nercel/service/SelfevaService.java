package org.ccnu.nercel.service;

import org.ccnu.nercel.bean.Selfeva;
import org.ccnu.nercel.repo.SelfevaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xiaotong
 * @createTime 20181010 下午3:07
 * @description 自我评价
 */
@Service
public class SelfevaService {

    @Autowired
    private SelfevaRepo selfevaRepo;

    //将学生的自我评价存入数据库
    public void selfeva(Selfeva selfeva){

        selfevaRepo.save(selfeva);
    }

    //获取学生的自我评价
    public List<Selfeva> getSelfEva(String stuid){

        List<Selfeva> selfevaList=selfevaRepo.findByStuid(stuid);

        return selfevaList;
    }

    //获取学生不同周的自我评价
    public List<Selfeva> getSelfEvaBytime(String stuid,String times){

        List<Selfeva> selfevaList=selfevaRepo.findByStuidAndTimes(stuid,times);

        return selfevaList;
    }

}
