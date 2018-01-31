package com.ensof.controller;

import com.ensof.help.AjaxResult;
import com.ensof.help.MetaData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by SungHere on 2017-12-15.
 */
@RestController
@RequestMapping("/info")
public class InfoController {

    @PostMapping("/orgname")
    public AjaxResult orglist() {
        AjaxResult ajaxResult = new AjaxResult();



        HashMap<String, HashMap<String, String>> data = MetaData.getOrgData();
        if (!data.isEmpty()) {
            ajaxResult.setOrglist(data);
            ajaxResult.setResult("S");
        } else {
            ajaxResult.setResult("F");

        }
        return ajaxResult;
    }
}
