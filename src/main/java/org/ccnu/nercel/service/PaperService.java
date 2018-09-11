package org.ccnu.nercel.service;

import org.ccnu.nercel.bean.Paper;
import org.ccnu.nercel.bean.paperQues;
import org.ccnu.nercel.repo.PaperQuesRepo;
import org.ccnu.nercel.repo.PaperRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 试卷
 * @author xiaotong
 * @version 2018年9月2日 下午10:32:13
 */
@Service
public class PaperService {

    @Autowired
    private PaperRepo paperrepo;

    @Autowired
    private PaperQuesRepo paperQuesrepo;

    public List<Paper> paper(String paperid){

        List<Paper> papers=paperrepo.findByPaperid(paperid);
        //System.out.println(papers);

        return papers;
    }

    public List<paperQues> paperQues(String paperid){

        List<paperQues> paperQuesList=paperQuesrepo.findByPaperid(paperid);
        //System.out.println(paperQuesList);

        return paperQuesList;
    }
}
