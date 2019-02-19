package org.ccnu.nercel.service;

import org.ccnu.nercel.bean.Ability;
import org.ccnu.nercel.repo.AbilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xiaotong
 * @createTime 20181014 下午10:11
 * @description 获取用户的系统评价等级
 */
@Service
public class AbilityService {

    @Autowired
    private AbilityRepo abilityRepo;

    public List<Ability> findAbility(String stuid){

        List<Ability> abilityList=abilityRepo.findByStuid(stuid);

        return abilityList;
    }

    public List<Ability> findAbilityTimes(String stuid,String times){

        List<Ability> abilityList=abilityRepo.findByStuidAndTimes(stuid,times);

        return abilityList;
    }
    public void initAbility(Ability ab){
        abilityRepo.save(ab);
    }
}
