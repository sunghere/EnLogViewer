package kr.co.ensof.persistence;

import kr.co.ensof.model.ENUser;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by SungHere on 2017-06-14.
 */
@Repository
public class ENUserDAOImpl implements ENUserDAO {
    private String ns = "ENUser.";

    @Autowired
    SqlSession sqlSession;

    @Override
    public ENUser login(ENUser user) {
        return sqlSession.selectOne(ns + "login", user);
    }

    @Override
    public void regi(ENUser user) throws Exception {
        sqlSession.insert(ns + "complete", user);


    }

    @Override
    public void update(ENUser user) throws Exception {
        sqlSession.update(ns + "update", user);
    }

    @Override
    public void changes(ENUser user) throws Exception {
        sqlSession.update(ns + "changes", user);
    }
}
