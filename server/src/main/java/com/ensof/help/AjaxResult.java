package com.ensof.help;

import com.ensof.model.Cube;
import com.ensof.model.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by SungHere on 2017-06-02.
 */
/* Ajax 결과 리턴용 Helper class*/
public class AjaxResult {

    private String result;/*결과담을 필드*/

    private Cube cube;

    private User user;

    private HashMap<String, HashMap<String, String>> orglist;
    private List<User> history;

    public Cube getCube() {
        return cube;
    }

    public void setCube(Cube cube) {
        this.cube = cube;
    }

    public AjaxResult() {
    }

    public AjaxResult(String result) {
        this.result = result;
    }

    public String getResult() {

        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HashMap<String, HashMap<String, String>> getOrglist() {
        return orglist;
    }

    public void setOrglist(HashMap<String, HashMap<String, String>> orglist) {
        this.orglist = orglist;
    }

    public List<User> getHistory() {
        return history;
    }

    public void setHistory(List<User> history) {
        this.history = history;
    }
}
