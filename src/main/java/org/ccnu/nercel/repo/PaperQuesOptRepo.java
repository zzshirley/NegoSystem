package org.ccnu.nercel.repo;

import org.ccnu.nercel.bean.PaperQuesOption;
import org.ccnu.nercel.bean.paperQues;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xiaotong
 * @createTime 20180903 上午9:12
 * @description 试卷问题
 */
@Repository
public interface PaperQuesOptRepo extends CrudRepository<PaperQuesOption,String> {

    List<PaperQuesOption> findByQuestionAndIstrueoption(String question,String istrueoption);

    String findById(int id);
}
