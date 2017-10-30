package kr.co.ensof.persistence;

import kr.co.ensof.model.IssueFile;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SungHere on 2017-10-10.
 */
@Repository
public class IssueFileDAO {

    String ns = "IssueFIle.";

    @Autowired
    SqlSession sqlSession;

    public List<IssueFile> select(IssueFile file) {

        return sqlSession.selectList(ns + "select");

    }

    public List<IssueFile> selectAll(IssueFile file) {

        return sqlSession.selectList(ns + "selectAll");

    }


    public void upload(IssueFile file) throws Exception {

        sqlSession.insert(ns + "upload", file);

    }

    public void delete(IssueFile file) throws Exception {

        sqlSession.delete(ns + "remove", file);

    }
}
