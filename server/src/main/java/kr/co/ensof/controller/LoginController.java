package kr.co.ensof.controller;

import kr.co.ensof.service.ENUserService;
import kr.co.ensof.help.AjaxResult;
import kr.co.ensof.help.EncryptUtil;
import kr.co.ensof.model.ENUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by SungHere on 2017-06-14.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    ENUserService service;

    @PostMapping(value = "/login")/* 로그인  */
    @ResponseBody
    ResponseEntity<?> login(@RequestBody ENUser enUser, HttpServletRequest request) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        ENUser user = null;
        try {
            enUser.setPwd(EncryptUtil.getSHA256(enUser.getPwd()));
            user = service.login(enUser);

            if (user != null) {
                request.getSession().setAttribute("login", user);
                request.getSession().setMaxInactiveInterval(30 * 60);
                ajaxResult.setResult("SUCS");
            } else {
                ajaxResult.setResult("FAIL");

            }

        } catch (Exception e) {
            ajaxResult.setResult("FAIL");

        }

        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }

    @PostMapping /* 회원가입  */
    @ResponseBody
    ResponseEntity<?> regi(@RequestBody ENUser enUser, HttpServletRequest request) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            enUser.setPwd(EncryptUtil.getSHA256(enUser.getPwd()));

            int isSuccess = service.regi(enUser);

            if (isSuccess == 1) {

                ajaxResult.setResult("SUCS");
            } else {
                ajaxResult.setResult("FAIL");

            }

        } catch (Exception e) {
            ajaxResult.setResult("FAIL");


        }

        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }

    @PutMapping(value = "/position/{id}", consumes = "application/json") /* 정보수정  */
    @ResponseBody
    ResponseEntity<?> changes(@RequestBody ENUser enUser, HttpServletRequest request) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        ENUser loginUser = (ENUser) request.getAttribute("login");

        if (loginUser.getId().equals(enUser.getId()))
            try {

                int isSuccess = service.changes(enUser);

                if (isSuccess == 1) {

                    ajaxResult.setResult("SUCS");
                } else {
                    ajaxResult.setResult("FAIL");

                }

            } catch (Exception e) {
                ajaxResult.setResult("FAIL");

            }

        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }

    @PutMapping(value = "/update/{id}", consumes = "application/json") /* 정보수정  */
    @ResponseBody
    ResponseEntity<?> update(@PathVariable String id, @RequestBody ENUser enUser, HttpServletRequest request) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        ENUser loginUser = (ENUser) request.getAttribute("login");
        enUser.setId(id);

        if (loginUser.getId().equals(enUser.getId()))
            try {

                int isSuccess = service.update(enUser);

                if (isSuccess == 1) {

                    ajaxResult.setResult("SUCS");
                } else {
                    ajaxResult.setResult("FAIL");

                }

            } catch (Exception e) {
                ajaxResult.setResult("FAIL");

            }

        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }

}
