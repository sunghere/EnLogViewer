package com.ensof.persistence;

import com.ensof.model.Cube;

import java.util.List;

/**
 * Created by SungHere on 2017-06-14.
 */
public interface CubeDAO {


    List<Cube> listAll();/*모든리스트*/

    List<Cube> listByTime(Cube cube); /* 시간당 리스트 */

    List<Cube> listByTrNo(Cube cube); /* 시간당 리스트 */

    Cube detail(Cube cube);/*하나의 리스트 */

    String getMsgName(Cube cube);/* 전문 이름 */
}
