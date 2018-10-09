package org.ccnu.nercel.service;

import org.ccnu.nercel.bean.User;
import org.ccnu.nercel.repo.RegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Xiaotong
 * @createTime 20180925 下午4:59
 * @description 学生注册
 */
@Service
public class RegisterService {

    @Autowired
    private RegisterRepo registerRepo;

    public void stuRegister(User user){
        registerRepo.save(user);
    }

}
