package kr.co.ensof.model;

/**
 * Created by SungHere on 2017-06-14.
 */
public class ENUser {

    private String id;


    private String name;

    private String pwd;

    private String postion;
    private String email;


    public ENUser() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPostion() {
        return postion;
    }

    public void setPostion(String postion) {
        this.postion = postion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ENUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", postion='" + postion + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public ENUser(String id, String name, String pwd, String postion, String email) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.postion = postion;
        this.email = email;
    }
}
