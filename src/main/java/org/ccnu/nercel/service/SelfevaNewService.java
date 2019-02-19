package org.ccnu.nercel.service;

import org.ccnu.nercel.bean.SelfevaNew;
import org.ccnu.nercel.repo.SelfevaNewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xiaotong
 * @createTime 20181224 下午3:02
 * @description 重新自评——协商中
 */
@Service
public class SelfevaNewService {

    @Autowired
    private SelfevaNewRepo selfevaNewRepo;

    public void savenewself(SelfevaNew selfevaNew){

        selfevaNewRepo.save(selfevaNew);

    }

    public List findnewSelf(String stuid,String times){

        List<SelfevaNew> selfevaNewList=selfevaNewRepo.findByStuidAndTimes(stuid,times);
        return selfevaNewList;
    }
}
