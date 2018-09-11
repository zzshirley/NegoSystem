package org.ccnu.nercel.repo;

import org.ccnu.nercel.bean.DoPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Xiaotong
 * @createTime 20180905 上午10:09
 * @description 提交试卷
 */
@Repository
public interface DoPaperRepo extends JpaRepository<DoPaper,Integer> {

}
