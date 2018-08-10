package org.ccnu.nercel.service;

import java.util.List;

import org.ccnu.nercel.bean.User;
import org.ccnu.nercel.repo.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author xiaotong
 * @Date 
 * 
 */
@Service
public class LoginService {
	
	 @Autowired
	 private LoginRepo loginrepo;
	 
	 public boolean verifyLogin(User user){

	     List<User> userList = loginrepo.findByStuidAndPassword(user.getStuid(), user.getPassword());
	     
	     return userList.size()>0;
	  }

}
