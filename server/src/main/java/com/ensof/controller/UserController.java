package com.ensof.controller;

import com.ensof.help.AjaxResult;
import com.ensof.model.User;
import com.ensof.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by SungHere on 2017-12-14.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public AjaxResult login(@RequestBody User user, HttpServletRequest request) {

        User loginData = service.login(user);
  /*      User loginData = null;
        if (user.getUserId().equals("admin") && user.getUserPw().equals("admin")) {
            loginData = user;
            loginData.setOrgNm("관리자");
            loginData.setUserPw("");
            loginData.setUserNm("MCCUBE");

        }*/

        AjaxResult result = new AjaxResult();

        if (loginData != null) {

            result.setUser(loginData);

            result.setResult("S");

            HttpSession session = request.getSession();


            session.setMaxInactiveInterval(20 * 60);
            session.setAttribute("login", loginData);

        } else {
            result.setResult("F");

        }


        return result;
    }

    @GetMapping
    public void logout(HttpServletRequest request) {

        request.getSession().invalidate();


    }

    @PostMapping("/history")
    public AjaxResult getHistory(HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("login");
        AjaxResult result = new AjaxResult();

        List<User> list = null;
        try {
            list = service.loginHistory();
//            list = new ArrayList<>();
            result.setHistory(list);
            result.setResult("S");

        } catch (Exception e) {
            result.setResult("Error");

        }

        return result;
    }
}
