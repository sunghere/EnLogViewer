package kr.co.ensof.model;

import java.util.Arrays;

/**
 * Created by SungHere on 2017-10-30.
 */

public class Cube {


    private String trTimeStamp; // D p key      yyyyMMddHHmmsssss
    private String trDate;      //              yyyyMMdd
    private String trTime;      //              HHmmsssss
    private String sysCode;     // D            Online,TEST
    private String orgCode;     // D            HOST,KCB
    private String destination; // D            KCB, ->KCB, <-KCB, <-TEST, -
    private String source;      // D            HOST,KCB,Sender
    private String trNo;        // D            1
    private String rqGb;        // D            Q,P,
    private String srGb;        // D            (R,S), R, S
    private String trCd;        //              100       업무구분 코드
    private String txCd;        //              0100,0110 전문종별 코드
    private String msgCode;     //              /KCB/KCB_0100_100
    private String respCd;      //              -
    private String traceNo;     //              (1,2,3,4), 1,2,3,4
    private String userData;    //              -
    private String inlen;       // io           284,279 (길이바이트..?)
    private long eTime;      //              26.58,0.04,0.13  (?)
    private long rTime;      //              0.00, null, 0.13
    private String txlen;       // data         280,275 (길이바이트 제외한 데이터바이트)
    private String txCount;     //              1
    private String compress_yn; // io           Y,N
    private String etc1;        //              Begin,End
    private String etc2;        // channel      아이피 <- 아이피
    private String etc3;        //              STREAM|E|euc-kr

    private String txSeq;       // D            숫자10자리
    private byte[] txdata;      // D            상세데이터, 암호화

    private String showTxData;
    /* 쿼리 조회용 컬럼*/
    private String sTimeStamp;
    private String eTimeStamp;

    @Override
    public String toString() {
        return "Cube{" +
                "trTimeStamp='" + trTimeStamp + '\'' +
                ", trDate='" + trDate + '\'' +
                ", trTime='" + trTime + '\'' +
                ", sysCode='" + sysCode + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", destination='" + destination + '\'' +
                ", source='" + source + '\'' +
                ", trNo='" + trNo + '\'' +
                ", rqGb='" + rqGb + '\'' +
                ", srGb='" + srGb + '\'' +
                ", trCd='" + trCd + '\'' +
                ", txCd='" + txCd + '\'' +
                ", msgCode='" + msgCode + '\'' +
                ", respCd='" + respCd + '\'' +
                ", traceNo='" + traceNo + '\'' +
                ", userData='" + userData + '\'' +
                ", inlen='" + inlen + '\'' +
                ", eTime=" + eTime +
                ", rTime=" + rTime +
                ", txlen='" + txlen + '\'' +
                ", txCount='" + txCount + '\'' +
                ", compress_yn='" + compress_yn + '\'' +
                ", etc1='" + etc1 + '\'' +
                ", etc2='" + etc2 + '\'' +
                ", etc3='" + etc3 + '\'' +
                ", txSeq='" + txSeq + '\'' +
                ", txdata=" + Arrays.toString(txdata) +
                ", showTxData='" + showTxData + '\'' +
                ", sTimeStamp='" + sTimeStamp + '\'' +
                ", eTimeStamp='" + eTimeStamp + '\'' +
                '}';
    }

    public String getTrTimeStamp() {
        return trTimeStamp;
    }

    public void setTrTimeStamp(String trTimeStamp) {
        this.trTimeStamp = trTimeStamp;
    }

    public String getTrDate() {
        return trDate;
    }

    public void setTrDate(String trDate) {
        this.trDate = trDate;
    }

    public String getTrTime() {
        return trTime;
    }

    public void setTrTime(String trTime) {
        this.trTime = trTime;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }


    public String getDestination() {
        if (etc2.contains("<"))
            destination = "<i class=\"fa fa-arrow-circle-o-left\" aria-hidden=\"true\"></i> " + destination;
        else if (etc2.contains(">"))
            destination = "<i class=\"fa fa-arrow-circle-right\" aria-hidden=\"true\"></i> " + destination;

        return destination;
    }

    public String getShowTxData() {
        return showTxData;
    }

    public void setShowTxData(String showTxData) {
        this.showTxData = showTxData;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTrNo() {
        return trNo;
    }

    public void setTrNo(String trNo) {
        this.trNo = trNo;
    }

    public String getRqGb() {
        return rqGb;
    }

    public void setRqGb(String rqGb) {
        this.rqGb = rqGb;
    }

    public String getSrGb() {
        return srGb;
    }

    public void setSrGb(String srGb) {
        this.srGb = srGb;
    }

    public String getTrCd() {
        return trCd;
    }

    public void setTrCd(String trCd) {
        this.trCd = trCd;
    }

    public String getTxCd() {
        return txCd;
    }

    public void setTxCd(String txCd) {
        this.txCd = txCd;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getRespCd() {
        return respCd;
    }

    public void setRespCd(String respCd) {
        this.respCd = respCd;
    }

    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public String getInlen() {
        return inlen;
    }

    public void setInlen(String inlen) {
        this.inlen = inlen;
    }

    public long geteTime() {
        return eTime;
    }

    public void seteTime(long eTime) {
        this.eTime = eTime;
    }

    public long getrTime() {
        return rTime;
    }

    public void setrTime(long rTime) {
        this.rTime = rTime;
    }

    public String getTxlen() {
        return txlen;
    }

    public void setTxlen(String txlen) {
        this.txlen = txlen;
    }

    public String getTxCount() {
        return txCount;
    }

    public void setTxCount(String txCount) {
        this.txCount = txCount;
    }

    public String getCompress_yn() {
        return compress_yn;
    }

    public void setCompress_yn(String compress_yn) {
        this.compress_yn = compress_yn;
    }

    public String getEtc1() {
        return etc1;
    }

    public void setEtc1(String etc1) {
        this.etc1 = etc1;
    }

    public String getEtc2() {
        return etc2;
    }

    public void setEtc2(String etc2) {
        this.etc2 = etc2;
    }

    public String getEtc3() {
        return etc3;
    }

    public void setEtc3(String etc3) {
        this.etc3 = etc3;
    }

    public String getTxSeq() {
        return txSeq;
    }

    public void setTxSeq(String txSeq) {
        this.txSeq = txSeq;
    }

    public byte[] getTxdata() {
        return txdata;
    }

    public void setTxdata(byte[] txdata) {
        this.txdata = txdata;
    }

    public String getsTimeStamp() {
        return sTimeStamp;
    }

    public void setsTimeStamp(String sTimeStamp) {
        this.sTimeStamp = sTimeStamp;
    }

    public String geteTimeStamp() {
        return eTimeStamp;
    }

    public void seteTimeStamp(String eTimeStamp) {
        this.eTimeStamp = eTimeStamp;
    }

    public Cube() {
    }

    public Cube(String trTimeStamp, String trDate, String trTime, String sysCode, String orgCode, String destination, String source, String trNo, String rqGb, String srGb, String trCd, String txCd, String msgCode, String respCd, String traceNo, String userData, String inlen, long eTime, long rTime, String txlen, String txCount, String compress_yn, String etc1, String etc2, String etc3, String txSeq, byte[] txdata, String sTimeStamp, String eTimeStamp) {
        this.trTimeStamp = trTimeStamp;
        this.trDate = trDate;
        this.trTime = trTime;
        this.sysCode = sysCode;
        this.orgCode = orgCode;
        this.destination = destination;
        this.source = source;
        this.trNo = trNo;
        this.rqGb = rqGb;
        this.srGb = srGb;
        this.trCd = trCd;
        this.txCd = txCd;
        this.msgCode = msgCode;
        this.respCd = respCd;
        this.traceNo = traceNo;
        this.userData = userData;
        this.inlen = inlen;
        this.eTime = eTime;
        this.rTime = rTime;
        this.txlen = txlen;
        this.txCount = txCount;
        this.compress_yn = compress_yn;
        this.etc1 = etc1;
        this.etc2 = etc2;
        this.etc3 = etc3;
        this.txSeq = txSeq;
        this.txdata = txdata;
        this.sTimeStamp = sTimeStamp;
        this.eTimeStamp = eTimeStamp;
    }

    public Cube(String trTimeStamp, String sTimeStamp, String eTimeStamp) {
        this.trTimeStamp = trTimeStamp;
        this.sTimeStamp = sTimeStamp;
        this.eTimeStamp = eTimeStamp;
    }

}
