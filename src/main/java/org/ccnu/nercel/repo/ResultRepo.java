package org.ccnu.nercel.repo;

import org.ccnu.nercel.bean.DoPaper;
import org.ccnu.nercel.bean.PaperQuesOption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xiaotong
 * @createTime 20180918 下午3:04
 * @description 历史成绩
 */
@Repository
public interface ResultRepo extends CrudRepository<DoPaper,String> {

    List<DoPaper> findByStuid(String Stuid);
    List<DoPaper> findByStuidAndAndPaperid(String stuid,String paperid);
    List<DoPaper> findAll();
}
