package com.ensof.service;

import com.ensof.model.User;
import com.ensof.persistence.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SungHere on 2017-12-14.
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDAO dao;

    @Override
    @Transactional
    public User login(User user) {
        User result = null;
        try {
            dao.loginTimeUpdate(user);
            result = dao.login(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> loginHistory() throws Exception{
        return dao.loginHistory();
    }
}
