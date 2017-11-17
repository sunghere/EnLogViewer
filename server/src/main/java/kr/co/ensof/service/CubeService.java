package kr.co.ensof.service;

import kr.co.ensof.model.Cube;

import java.util.List;

/**
 * Created by SungHere on 2017-06-14.
 */
public interface CubeService {


    List<Cube> listAll();/*모든리스트*/

    List<Cube> listByTime(Cube cube); /* 시간당 리스트 */


    Cube detail(Cube cube);/*하나의 리스트 */
}
