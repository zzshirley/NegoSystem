package org.ccnu.nercel.repo;

import org.ccnu.nercel.bean.DoPaper;
import org.ccnu.nercel.bean.Papersdo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Xiaotong
 * @createTime 20181210 下午2:29
 * @description 重新计算成绩
 */
@Repository
public interface PapersdoRepo extends JpaRepository<Papersdo,Integer> {
}
