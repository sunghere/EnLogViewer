package com.ensof.service;

import com.ensof.model.Batch;
import com.ensof.persistence.BatchDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SungHere on 2018-01-12.
 */
@Service
public class BatchServiceImpl implements BatchService {
    @Autowired
    BatchDAO dao;

    @Override
    @Transactional(readOnly = true)
    public List<Batch> listAll(Batch batch) {
        return dao.listAll(batch);
    }
}
