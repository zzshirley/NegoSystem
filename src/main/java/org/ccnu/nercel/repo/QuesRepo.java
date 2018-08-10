package org.ccnu.nercel.repo;

import java.util.List;

import org.ccnu.nercel.bean.Question;
import org.ccnu.nercel.bean.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
*@author xiaotong
*@version 2018年8月5日 下午9:03:00
*/
@Repository
public interface QuesRepo extends CrudRepository<Question,String>{
	
	public List<Question> findByPaperid(String paperid);

}
