package kr.co.ensof.persistence;

import kr.co.ensof.model.ENUser;

/**
 * Created by SungHere on 2017-06-14.
 */
public interface ENUserDAO {

    ENUser login(ENUser user);

    void regi(ENUser user) throws Exception;

    void update(ENUser user) throws Exception; // 비밀번호변경

    void changes(ENUser user) throws Exception;// 직급변경
}
