package org.ccnu.nercel.repo;

import org.ccnu.nercel.bean.DoQues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**写问卷
 *@author xiaotong
 *@version 2018年9月2日 上午9:44:00
 */
@Repository
public interface DoQuesRepo extends JpaRepository<DoQues,Integer> {

}
