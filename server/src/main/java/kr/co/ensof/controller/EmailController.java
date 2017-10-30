package kr.co.ensof.controller;

import kr.co.ensof.service.EmailService;
import kr.co.ensof.help.AjaxResult;
import kr.co.ensof.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by SungHere on 2017-06-28.
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService service;


    @PostMapping/* 메일보내기 */
    ResponseEntity<?> mailSend(@RequestBody Email email) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            service.sendMail(email);
            ajaxResult.setResult("SUCS");

        } catch (Exception e) {
            ajaxResult.setResult("FAIL");

        }

        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }
}
