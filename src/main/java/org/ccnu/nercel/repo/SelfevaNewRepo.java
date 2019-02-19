package org.ccnu.nercel.repo;

import org.ccnu.nercel.bean.SelfevaNew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xiaotong
 * @createTime 20181223 下午9:55
 * @description 重新自评仓库
 */
@Repository
public interface SelfevaNewRepo extends JpaRepository<SelfevaNew,String> {

    List<SelfevaNew> findByStuidAndTimes(String stuid,String times);
}
