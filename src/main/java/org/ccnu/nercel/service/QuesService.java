package org.ccnu.nercel.service;

import java.util.List;

import org.ccnu.nercel.bean.Question;
import org.ccnu.nercel.bean.User;
import org.ccnu.nercel.repo.LoginRepo;
import org.ccnu.nercel.repo.QuesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author xiaotong
*@version 2018年8月5日 下午9:08:21
*/
@Service
public class QuesService {
	
	@Autowired
	private QuesRepo quesrepo;
	
	public List<Question> QuesList(Question ques){
		
	     List<Question> quesList = quesrepo.findByPaperid(ques.getPaperid());
	     return quesList;
	  }
	
	
}
