package org.ccnu.nercel.repo;

import java.util.List;

import org.ccnu.nercel.bean.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * @author xiaotong
 * 
 */
@Repository
public interface LoginRepo extends CrudRepository<User,Integer>{
	
	List<User> findByStuidAndPassword(String stuid, String password);
}
