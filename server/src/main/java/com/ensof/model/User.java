package com.ensof.model;

/**
 * Created by SungHere on 2017-12-14.
 */
public class User {


    private String userId;
    private String userPw;
    private String userNm;
    private String authCd;
    private String orgNm;
    private String useYn;
    private String loginTime;
    private String logoutTime;

    private String filler1;
    private String filler2;
    private String filler3;
    private String IPDTM;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getAuthCd() {
        return authCd;
    }

    public void setAuthCd(String authCd) {
        this.authCd = authCd;
    }

    public String getOrgNm() {
        return orgNm;
    }

    public void setOrgNm(String orgNm) {
        this.orgNm = orgNm;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getLoginTime() {


        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getFiller1() {
        return filler1;
    }

    public void setFiller1(String filler1) {
        this.filler1 = filler1;
    }

    public String getFiller2() {
        return filler2;
    }

    public void setFiller2(String filler2) {
        this.filler2 = filler2;
    }

    public String getFiller3() {
        return filler3;
    }

    public void setFiller3(String filler3) {
        this.filler3 = filler3;
    }

    public String getIPDTM() {
        return IPDTM;
    }

    public void setIPDTM(String IPDTM) {
        this.IPDTM = IPDTM;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userNm='" + userNm + '\'' +
                ", authCd='" + authCd + '\'' +
                ", orgNm='" + orgNm + '\'' +
                ", useYn='" + useYn + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", logoutTime='" + logoutTime + '\'' +
                ", filler1='" + filler1 + '\'' +
                ", filler2='" + filler2 + '\'' +
                ", filler3='" + filler3 + '\'' +
                ", IPDTM='" + IPDTM + '\'' +
                '}';
    }
}
