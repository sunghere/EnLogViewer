package kr.co.ensof.service;

import kr.co.ensof.model.Cube;
import kr.co.ensof.persistence.CubeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SungHere on 2017-06-02.
 */
@Service
public class CubeServiceImpl implements CubeService {

    @Autowired
    private CubeDAO dao;


    @Override
    @Transactional
    public List<Cube> listAll() {
        return dao.listAll();
    }

    @Override
    @Transactional
    public List<Cube> listByTime(Cube cube) {
        return dao.listByTime(cube);
    }

    @Override
    @Transactional
    public Cube detail(Cube cube) {
        return dao.detail(cube);
    }
}
