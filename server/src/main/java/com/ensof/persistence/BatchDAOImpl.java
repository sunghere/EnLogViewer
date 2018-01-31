package com.ensof.persistence;

import com.ensof.model.Batch;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SungHere on 2018-01-12.
 */
@Repository
public class BatchDAOImpl implements BatchDAO {
    private String ns = "Batch.";
    @Autowired
    SqlSession sqlSession;

    @Override
    public List<Batch> listAll(Batch batch) {
        return sqlSession.selectList(ns + "listAll", batch);
    }
}
