package org.ccnu.nercel.service;

import org.ccnu.nercel.bean.PaperQuesOption;
import org.ccnu.nercel.repo.PaperQuesOptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xiaotong
 * @createTime 20180903 上午9:18
 * @description 试卷随机选择问题
 */
@Service
public class PaperQuesOptService {

    @Autowired
    private PaperQuesOptRepo paperQuesOptRepo;

    public List<PaperQuesOption> quesOptions(String question,String tof){

        List<PaperQuesOption> quesOptionList = paperQuesOptRepo.findByQuestionAndIstrueoption(question,tof);

        return quesOptionList;
    }

    public List<PaperQuesOption> quesOptions(int id){

        List<PaperQuesOption> quesOptions=paperQuesOptRepo.findById(id);

        return quesOptions;
    }

}
