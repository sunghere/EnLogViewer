package com.ensof.controller;

import com.ensof.help.DataTable;
import com.ensof.model.Batch;
import com.ensof.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by SungHere on 2018-01-12.
 */
@RestController
@RequestMapping("/batch")
public class BatchController {
    @Autowired
    private BatchService service;

    @PostMapping /* 리스트 가져오기*/
    public DataTable cubeList(@RequestBody Batch batch) {


        DataTable dataTable = new DataTable();
        List<Batch> bDateList = service.listAll(batch);
        dataTable.setData(bDateList);
        return dataTable;
    }
}
