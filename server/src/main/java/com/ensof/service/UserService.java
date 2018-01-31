package com.ensof.service;

import com.ensof.model.User;

import java.util.List;

/**
 * Created by SungHere on 2017-12-14.
 */
public interface UserService {
    User login(User user);

    List<User> loginHistory() throws Exception;

}
