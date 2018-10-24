package org.ccnu.nercel.repo;

import org.ccnu.nercel.bean.Ability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xiaotong
 * @createTime 20181014 下午10:07
 * @description 系统评价
 */

@Repository
public interface AbilityRepo extends JpaRepository<Ability,String> {

    List<Ability> findByStuid(String stuid);
    List<Ability> findByStuidAndTimes(String stuid,String times);
}
