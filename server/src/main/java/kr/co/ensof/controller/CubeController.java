package kr.co.ensof.controller;

import kr.co.ensof.help.DataTable;
import kr.co.ensof.model.Cube;
import kr.co.ensof.service.CubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by SungHere on 2017-06-02.
 */
@RestController
@RequestMapping("/cube")
public class CubeController { /* Rest 컨트롤러 */

    @Autowired
    private CubeService service;

    @GetMapping /* 리스트 가져오기*/
    public DataTable cubeList() {
        DataTable dataTable = new DataTable();
        dataTable.setData(service.listAll());

        return dataTable;
    }
    @PostMapping /* 리스트 가져오기*/
    public DataTable cubeList(@RequestBody Cube cube) {
        DataTable dataTable = new DataTable();
        dataTable.setData(service.);

        return dataTable;
    }
    @GetMapping(value = "/{sysCode}/{trTimeStamp}") /* 하나 가져오기 */
    public Cube cubeDetail(@PathVariable String sysCode, @PathVariable String trTimeStamp) {
        Cube cube = new Cube();
        cube.setSysCode(sysCode);
        cube.setTrTimeStamp(trTimeStamp);
        Cube result = service.detail(cube);
        System.out.println(result);
        return result;
    }



  /*  @PostMapping*//* Insert ( 추가 ) *//*
    ResponseEntity<?> create(@RequestBody Cube cube, HttpServletRequest request) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            service.insert(cube);
            ajaxResult.setResult("SUCS");
        } catch (Exception e) {
            ajaxResult.setResult("FAIL");

        }

        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }


    @PutMapping(value = "/{seq}", consumes = "application/json")  수정
    ResponseEntity<?> update(@PathVariable Integer seq, @RequestBody Cube cube) {
        cube.setSeq(seq);
        AjaxResult ajaxResult = new AjaxResult();
        try {
            service.update(cube);
            ajaxResult.setResult("SUCS");
        } catch (Exception e) {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);
    }

    @PutMapping("/{seq}/detete")  삭제
    ResponseEntity<?> delete(@PathVariable Integer seq) {
        AjaxResult ajaxResult = new AjaxResult();

        try {
            service.delete(seq);
            ajaxResult.setResult("SUCS");
        } catch (Exception e) {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }*/
}
