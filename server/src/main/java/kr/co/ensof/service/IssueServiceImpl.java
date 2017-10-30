package kr.co.ensof.service;

import kr.co.ensof.model.Issue;
import kr.co.ensof.persistence.IssueDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SungHere on 2017-06-02.
 */
@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueDAO dao;


    @Override
    @Transactional
    public void delete(Integer seq) {
        dao.delete(seq);
    }

    @Override
    @Transactional
    public void update(Issue issue) {
        dao.update(issue);
    }

    @Override
    @Transactional
    public int complete(Issue issue) {
        try {
            dao.complete(issue);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public int stepup(Issue issue) {
        try {
            dao.stepup(issue);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    @Transactional
    public void insert(Issue issue) {
        dao.insert(issue);
    }

    @Override
    @Transactional
    public List<Issue> listAll() {
        return dao.listAll();
    }

    @Override
    @Transactional
    public Issue detail(Issue issue) {
        return dao.detail(issue);
    }
}
