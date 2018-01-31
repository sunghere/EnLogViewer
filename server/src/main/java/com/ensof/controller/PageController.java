package com.ensof.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by SungHere on 2017-12-20.
 */
@Controller
public class PageController {

    @RequestMapping("/hello")
    public String start(Map<String, Object> model, HttpServletRequest request) {
        System.out.println("TEST start");
        return "hello";
    }
}
