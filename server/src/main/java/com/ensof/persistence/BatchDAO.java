package com.ensof.persistence;

import com.ensof.model.Batch;

import java.util.List;

/**
 * Created by SungHere on 2018-01-12.
 */
public interface BatchDAO {

    List<Batch> listAll(Batch batch);
}
