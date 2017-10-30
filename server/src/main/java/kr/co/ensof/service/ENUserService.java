package kr.co.ensof.service;

import kr.co.ensof.model.ENUser;

/**
 * Created by SungHere on 2017-06-14.
 */
public interface ENUserService {

    ENUser login(ENUser user);

    int update(ENUser user); // 비밀번호변경

    int changes(ENUser user);// 직급변경

    int regi(ENUser user);
}
