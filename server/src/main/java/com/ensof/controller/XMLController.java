package com.ensof.controller;

import com.ensof.help.AjaxResult;
import com.ensof.model.Cube;
import com.ensof.model.User;
import com.ensof.service.XMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by SungHere on 2017-11-28.
 */
@RestController
@RequestMapping("/XML")
public class XMLController {


    @Autowired
    XMLService service;

    @PostMapping
    public String getCubeXML(@RequestBody Cube cube) {

        String result = service.getField(cube.getMsgCode(), cube.getShowTxData());

        return result;
    }

    @PostMapping("/reload/MSG")
    public AjaxResult reloadMSG(HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("login");
        AjaxResult ajaxResult = new AjaxResult();

        if (user != null && Integer.parseInt(user.getAuthCd()) > 5) {
            ajaxResult.setResult(service.reload("MSG"));

        } else {
            ajaxResult.setResult("권한 부족 또는 로그인세션 만료");
            System.out.println(user);
        }


        return ajaxResult;
    }

    @PostMapping("/reload/ORG")
    public AjaxResult reloadORG(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("login");
        AjaxResult ajaxResult = new AjaxResult();

        ajaxResult.setResult(service.reload("ORG"));
        if (user != null && Integer.parseInt(user.getAuthCd()) > 5) {
            ajaxResult.setResult(service.reload("MSG"));

        } else {
            ajaxResult.setResult("권한 부족 또는 로그인세션 만료");

        }

        return ajaxResult;
    }


}
