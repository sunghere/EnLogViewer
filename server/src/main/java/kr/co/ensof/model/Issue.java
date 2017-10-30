package kr.co.ensof.model;

import java.io.Serializable;

/**
 * Created by SungHere on 2017-06-02.
 */

public class Issue implements Serializable {

    private int seq;
    private String title;
    private String content;
    private int step;
    private int del;

    private String category;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Issue(int seq, String title, String content, int step, String category) {
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.step = step;
        this.category = category;
    }

    public Issue(int seq, String title, String content, int step, int del, String category) {

        this.seq = seq;
        this.title = title;
        this.content = content;
        this.step = step;
        this.del = del;
        this.category = category;
    }

    public Issue() {

    }
}
