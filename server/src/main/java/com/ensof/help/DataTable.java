package com.ensof.help;

import com.ensof.model.Cube;

import java.util.List;

/**
 * Created by SungHere on 2017-11-01.
 */
public class DataTable {

    private int recordsTotal;
    private boolean draw = true;
    private List data;
    private int recordsFiltered = 0;

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public List<Cube> getData() {
        return data;
    }

    public void setData(List data) {
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
