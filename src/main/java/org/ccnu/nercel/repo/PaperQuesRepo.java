package org.ccnu.nercel.repo;

import org.ccnu.nercel.bean.Paper;
import org.ccnu.nercel.bean.paperQues;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 试卷问题仓库
 * @author xiaotong
 * @version 2018年9月2日 下午10:28:13
 */
@Repository
public interface PaperQuesRepo extends CrudRepository<paperQues,String> {

    List<paperQues> findByPaperid(String paperid);
}
