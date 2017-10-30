package kr.co.ensof.model;

/**
 * Created by SungHere on 2017-10-10.
 */
public class IssueFile {
    public IssueFile() {
    }

    @Override
    public String toString() {
        return "IssueFile{" +
                "seq=" + seq +
                ", pseq=" + pseq +
                ", filename='" + filename + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    private int seq;
    private int pseq;
    private String filename;
    private String path;


    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getPseq() {
        return pseq;
    }

    public void setPseq(int pseq) {
        this.pseq = pseq;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
