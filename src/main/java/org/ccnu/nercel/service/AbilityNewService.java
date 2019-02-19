package org.ccnu.nercel.service;

import org.ccnu.nercel.bean.Ability;
import org.ccnu.nercel.bean.AbilityNew;
import org.ccnu.nercel.repo.AbilityNewRepo;
import org.ccnu.nercel.repo.AbilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xiaotong
 * @createTime 20181223 下午11:34
 * @description 重新测试service
 */
@Service
public class AbilityNewService {

    @Autowired
    private AbilityNewRepo abilitynewRepo;

    public void AbilityNew(AbilityNew abilityNew){
        abilitynewRepo.save(abilityNew);
    }

    public List findAbilitynew(String usrid,String paperid){
        List<AbilityNew> abilityNewList=abilitynewRepo.findByStuidAndPaperid(usrid,paperid);
        return abilityNewList;
    }
}
