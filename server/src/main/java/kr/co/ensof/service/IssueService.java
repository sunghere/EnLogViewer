package kr.co.ensof.service;

import kr.co.ensof.model.Issue;

import java.util.List;

/**
 * Created by SungHere on 2017-06-14.
 */
public interface IssueService {

    void delete(Integer seq); /* 삭제 */


    void update(Issue issue); /* 수정 */


    int complete(Issue issue); /* 완료 */

    int stepup(Issue issue); /* 단계변경 */


    void insert(Issue issue);/*작성*/


    List<Issue> listAll();/*모든리스트*/


    Issue detail(Issue issue);/*하나의 리스트 */
}
