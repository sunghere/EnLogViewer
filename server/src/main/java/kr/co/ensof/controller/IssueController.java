package kr.co.ensof.controller;

import kr.co.ensof.service.IssueService;
import kr.co.ensof.help.AjaxResult;
import kr.co.ensof.model.ENUser;
import kr.co.ensof.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by SungHere on 2017-06-02.
 */
@RestController
@RequestMapping("/issue")
public class IssueController { /* Rest 컨트롤러 */

    @Autowired
    private IssueService service;


    @GetMapping(value = "/{seq}") /* 하나 가져오기 */
    Issue projectDetail(@PathVariable Integer seq) throws Exception {
        Issue issue = new Issue();
        issue.setSeq(seq);
        return service.detail(issue);
    }

    @GetMapping /* 리스트 가져오기*/
    List<Issue> projectList() throws Exception {
        return service.listAll();
    }

    @PostMapping/* Insert (프로젝트 추가 ) */
    ResponseEntity<?> create(@RequestBody Issue issue, HttpServletRequest request) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        ENUser user = (ENUser) request.getSession().getAttribute("login");
        if (user != null) {
            try {
                service.insert(issue);
                ajaxResult.setResult("SUCS");
            } catch (Exception e) {
                ajaxResult.setResult("FAIL");

            }

        } else {
            ajaxResult.setResult("FAIL");

        }

        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }

    @PutMapping(value = "/{seq}/completed", consumes = "application/json") /* 완료 여부 */
    ResponseEntity<?> completed(@PathVariable Integer seq, @RequestBody Issue issue) throws Exception {
        issue.setSeq(seq);
        AjaxResult ajaxResult = new AjaxResult();
        try {
            service.complete(issue);
            ajaxResult.setResult("SUCS");
        } catch (Exception e) {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);
    }

    @PutMapping(value = "/{seq}", consumes = "application/json") /* 수정 */
    ResponseEntity<?> update(@PathVariable Integer seq, @RequestBody Issue issue) {
        issue.setSeq(seq);
        AjaxResult ajaxResult = new AjaxResult();
        try {
            service.update(issue);
            ajaxResult.setResult("SUCS");
        } catch (Exception e) {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);
    }

    @PutMapping("/{seq}/detete") /* 삭제 */
    ResponseEntity<?> delete(@PathVariable Integer seq) {
        AjaxResult ajaxResult = new AjaxResult();

        try {
            service.delete(seq);
            ajaxResult.setResult("SUCS");
        } catch (Exception e) {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }
}
