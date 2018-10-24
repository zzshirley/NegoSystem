package org.ccnu.nercel.repo;

import org.ccnu.nercel.bean.DoPaper;
import org.ccnu.nercel.bean.Selfeva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Xiaotong
 * @createTime 20181010 下午3:06
 * @description 自我评价仓库
 */
public interface SelfevaRepo extends JpaRepository<Selfeva,Integer> {

    List<Selfeva> findByStuid(String stuid);

    List<Selfeva> findByStuidAndTimes(String stuid,String times);

}
