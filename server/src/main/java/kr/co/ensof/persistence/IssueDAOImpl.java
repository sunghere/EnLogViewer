package kr.co.ensof.persistence;

import kr.co.ensof.model.Issue;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IssueDAOImpl implements IssueDAO {

    private String ns = "Issue.";

    @Autowired
    SqlSession sqlSession;


    @Override
    public void delete(Integer seq) {

        sqlSession.update(ns + "delete", seq);
    }

    @Override
    public void update(Issue issue) {
        sqlSession.update(ns + "update", issue);

    }

    @Override
    public void complete(Issue issue) {

        sqlSession.update(ns + "complete", issue);


    }

    @Override
    public void stepup(Issue issue) throws Exception {
        sqlSession.update(ns + "stepup", issue);


    }

    @Override
    public void insert(Issue issue) {
        sqlSession.insert(ns + "insert", issue);

    }

    @Override
    public List<Issue> listAll() {
        return sqlSession.selectList(ns + "listAll");
    }

    @Override
    public Issue detail(Issue issue) {
        return sqlSession.selectOne(ns + "detail", issue);
    }
}
