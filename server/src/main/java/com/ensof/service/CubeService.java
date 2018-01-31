package com.ensof.service;

import com.ensof.model.Cube;

import java.util.List;

/**
 * Created by SungHere on 2017-06-14.
 */
public interface CubeService {


    List<Cube> listAll();/*모든리스트*/

    List<Cube> listByTime(Cube cube); /* 시간 리스트 */

    List<Cube> listByTrNo(Cube cube); /* 전문추적번호 리스트 */


    Cube detail(Cube cube);/*하나의 리스트 */
}
