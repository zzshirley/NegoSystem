package org.ccnu.nercel.repo;

import org.ccnu.nercel.bean.AbilityNew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xiaotong
 * @createTime 20181223 下午9:50
 * @description 重新测试仓库
 */
@Repository
public interface AbilityNewRepo extends JpaRepository<AbilityNew,String> {

    List<AbilityNew> findByStuidAndPaperid(String usrid,String paperid);
}
