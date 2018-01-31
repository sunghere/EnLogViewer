package com.ensof.persistence;

import com.ensof.model.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by SungHere on 2017-12-14.
 */
@Repository
public class UserDAOImpl implements UserDAO {
    private String ns = "User.";

    @Autowired
    private SqlSession sqlSession;


    @Override
    public User login(User user) {
        return sqlSession.selectOne(ns + "login", user);
    }

    @Override
    public void loginTimeUpdate(User user) throws Exception {
        sqlSession.update(ns + "loginTime", user);
    }

    @Override
    public List<User> loginHistory() {
        User user = new User();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today= simpleDateFormat.format(new Date());
        user.setLoginTime(today);
        return sqlSession.selectList(ns + "history",user);
    }
}
