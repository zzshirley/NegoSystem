package org.ccnu.nercel.service;

import org.ccnu.nercel.bean.DoQues;
import org.ccnu.nercel.repo.DoQuesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**写入问卷
 *@author xiaotong
 *@version 2018年9月2日 下午9:59:02
 */
@Service
public class DoQuesService {

    @Autowired
    private DoQuesRepo doquesrepo;

    public void Doques(DoQues doQues) {

        doquesrepo.save(doQues);

    }

}
