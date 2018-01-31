package com.ensof.persistence;

import com.ensof.model.User;

import java.util.List;

/**
 * Created by SungHere on 2017-12-14.
 */
public interface UserDAO {


    User login(User user);
    void loginTimeUpdate(User user) throws Exception;

    List<User> loginHistory();
}
