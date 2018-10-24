package org.ccnu.nercel.repo;

import org.ccnu.nercel.bean.StuQues;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xiaotong
 * @createTime 20181023 下午3:15
 * @description 学生的问题
 */
@Repository
public interface StuQueRepo extends CrudRepository<StuQues,String> {

    List<StuQues> findByPaperid(String paperid);
}
