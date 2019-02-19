package org.ccnu.nercel.service;

import org.ccnu.nercel.bean.Papersdo;
import org.ccnu.nercel.repo.PapersdoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Xiaotong
 * @createTime 20181210 下午2:30
 * @description 重新计算成绩
 */
@Service
public class PapersdoService {

    @Autowired
    private PapersdoRepo papersdoRepo;

    public void papersdo(Papersdo papersdo){

        papersdoRepo.save(papersdo);
    }
}
