package org.ccnu.nercel.repo;

import org.ccnu.nercel.bean.Nego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Xiaotong
 * @createTime 20181016 下午9:57
 * @description 协商
 */
@Repository
public interface NegoRepo extends JpaRepository<Nego,String> {

}
