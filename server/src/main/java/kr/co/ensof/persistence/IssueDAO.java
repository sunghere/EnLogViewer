package kr.co.ensof.persistence;

import kr.co.ensof.model.Issue;

import java.util.List;

/**
 * Created by SungHere on 2017-06-14.
 */
public interface IssueDAO {


    void delete(Integer seq); /* 삭제 */


    void update(Issue issue); /* 수정 */


    void complete(Issue issue) throws Exception; /* 완료 */

    void stepup(Issue issue) throws Exception; /* 단계변경 */


    void insert(Issue issue);/*작성*/


    List<Issue> listAll();/*모든리스트*/


    Issue detail(Issue issue);/*하나의 리스트 */

}
