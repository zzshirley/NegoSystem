package org.ccnu.nercel.service;

import org.ccnu.nercel.bean.Nego;
import org.ccnu.nercel.repo.NegoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Xiaotong
 * @createTime 20181016 下午10:00
 * @description 协商
 */
@Service
public class NegoService {

    @Autowired
    private NegoRepo negoRepo;

    public void insertNego(Nego nego){

        negoRepo.save(nego);
    }
}
