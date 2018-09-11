package org.ccnu.nercel.repo;

import org.ccnu.nercel.bean.Paper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 试卷仓库
 * @author xiaotong
 * @version 2018年9月2日 下午1:28:13
 */
@Repository
public interface PaperRepo extends CrudRepository<Paper,String> {

    List<Paper> findByPaperid(String paperid);
}
