package kr.co.ensof.service;

import kr.co.ensof.model.ENUser;
import kr.co.ensof.persistence.ENUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SungHere on 2017-06-14.
 */
@Service
public class ENUserServiceImpl implements ENUserService {
    @Autowired
    ENUserDAO enUserDAO;

    @Override
    public ENUser login(ENUser user) {
        return enUserDAO.login(user);
    }

    @Override
    public int update(ENUser user) {
        try {
            enUserDAO.update(user);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public int changes(ENUser user) {
        try {
            enUserDAO.changes(user);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public int regi(ENUser user) {
        try {
            enUserDAO.regi(user);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
