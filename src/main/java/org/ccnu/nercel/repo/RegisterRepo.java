package org.ccnu.nercel.repo;

import org.ccnu.nercel.bean.DoQues;
import org.ccnu.nercel.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Xiaotong
 * @createTime 20180925 下午4:57
 * @description 注册
 */

@Repository
public interface RegisterRepo extends JpaRepository<User,Integer> {

}
