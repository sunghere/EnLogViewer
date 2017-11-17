package kr.co.ensof.help;

import kr.co.ensof.model.Cube;

import java.util.List;

/**
 * Created by SungHere on 2017-11-01.
 */
public class DataTable {

    private int recordsTotal;
    private int draw = 1;
    private List<Cube> data;
    private int recordsFiltered=0;

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public List<Cube> getData() {
        return data;
    }

    public void setData(List<Cube> data) {
        recordsTotal = data.size();
        this.data = data;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
}
