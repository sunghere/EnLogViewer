package com.ensof.model;

import com.ensof.help.MetaData;
import com.ensof.help.SHUtil;

import java.text.ParseException;

/**
 * Created by SungHere on 2017-10-30.
 */

public class Cube {


    private String trTimeStamp; // D p key      yyyyMMddHHmmsssss
    private String trTimeStampPattern; // D p key      yyyyMMddHHmmsssss
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
    private String msgName;     //              전문이름
    private String msgNameSub;     //              전문이름 길이짧게...
    private String respCd;      //              -
    private String traceNo;     //              (1,2,3,4), 1,2,3,4
    private String userData;    //              -
    private String userDataSub;    //              -
    private String inlen;       // io           284,279 (길이바이트..?)
    private long eTime;      //              26.58,0.04,0.13  (?)
    private long rTime;      //              0.00, null, 0.13
    private String txlen;       // data         280,275 (길이바이트 제외한 데이터바이트)
    private String txCount;     //              1
    private String compressYn; // io           Y,N
    private String etc1;        //              Begin,End
    private String etc2;        // channel      아이피 <- 아이피
    private String etc3;        //              STREAM|E|euc-kr

    private String txSeq;       // D            숫자10자리
    private byte[] txdata;      // D            상세데이터, 암호화

    private String showTxData;
    /* 쿼리 조회용 컬럼*/
    private String sdate;
    private String edate;

    private String isError;
    private String isXML = "false"; // XML 구분자.
    private String path;      // 아이콘+


    public String getIsError() {
        if (etc1 != null && etc1.length() > 15)
            isError = "Error";
        else isError = etc1;
        return isError;
    }

    public void setIsError(String isError) {
        this.isError = isError;
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

    public String getSource() {

        return source;
    }

    public String getDestination() {


        return destination;
    }

    public String getDestinationIcon() {
        String icon = destination;


        if (rqGb != null && rqGb.equals("P")) {
            icon = source;
/*            if (srGb.equals("R")) {
                icon = "◁◁" + icon;
//                icon = "<span style='float:left;'>◁◁</span>" + icon;

            }
        } else if (rqGb.equals("Q") && srGb.equals("S")) {
//            icon = "<span style='float:left;'>▶▶</span>" + icon;
            icon = "▶▶" + icon;*/
        }
        return icon;
    }

    public String getPath() {
        String icon = "";
        path = "";

        if (srGb != null && rqGb != null)
            if (rqGb.equals("P")) {
                if (srGb.equals("R")) {
                    icon = "　　◁◁";

                } else if (srGb.equals("S")) {
                    icon = "◀◀　　";

                } else {
                    icon = "　　◁　";

                }
            } else if (rqGb.equals("Q")) {
                if (srGb.equals("S")) {
                    icon = "　　▶▶";

                } else if (srGb.equals("R")) {
                    icon = "▷▷　　";
                } else {
                    icon = "　▷　　";

                }
            }
        return icon;
    }

    public String getSourceIcon() {
        String icon = source;


        if (rqGb != null && rqGb.equals("P")) {
            icon = destination;
/*
            if (srGb.equals("S")) {
//                icon = icon + "<span style='float:right;'>◀◀</span>";
                icon = icon + "◀◀";

            }

        } else if (rqGb.equals("Q") && srGb.equals("R")) {
//            icon = icon + "<span style='float:right;'>▷▷</span>";
            icon = icon + "▷▷";*/
        }
        return icon;
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
        if (msgCode != null && msgCode.length() > 30) {
            msgCode = msgCode.substring(0, 30) + "..";
        }

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

    public String getUserDataSub() {
        if (userData != null) {

            if (userData.length() > 20) {
                userDataSub = userData.substring(0, 18) + "...";

            } else {
                userDataSub = userData;
            }

        }
        return userDataSub;
    }

    public void setUserDataSub(String userDataSub) {
        this.userDataSub = userDataSub;
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

    public String getCompressYn() {
        return compressYn;
    }

    public void setCompressYn(String compressYn) {
        this.compressYn = compressYn;
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

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getIsXML() {

        if (etc3 != null) {
            if (etc3.contains("XML"))
                isXML = "true";
        } else {
            isXML = "false";
        }
        return isXML;
    }

    public String getTrTimeStampPattern() throws ParseException {


        trTimeStampPattern = SHUtil.getTimePatternYYYYMMDDHHmmssSSS(trTimeStamp);
        return trTimeStampPattern;

    }

    public void setTrTimeStampPattern(String trTimeStampPattern) {
        this.trTimeStampPattern = trTimeStampPattern;
    }

    public String getMsgName() {
        msgName = MetaData.getMsgName(msgCode);
        return msgName;
    }

    public String getMsgNameSub() {

        msgNameSub = MetaData.getMsgName(msgCode);

        if (msgNameSub == null) msgNameSub = "";
        if (msgNameSub.length() > 20) {
            msgNameSub = msgNameSub.substring(0, 20) + "..";
        }

        return msgNameSub;
    }

    public void setMsgNameSub(String msgNameSub) {
        this.msgNameSub = msgNameSub;
    }

    public Cube() {
    }


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
                ", compressYn='" + compressYn + '\'' +
                ", etc1='" + etc1 + '\'' +
                ", etc2='" + etc2 + '\'' +
                ", etc3='" + etc3 + '\'' +
                ", txSeq='" + txSeq + '\'' +
                ", showTxData='" + showTxData + '\'' +
                ", sdate='" + sdate + '\'' +
                ", edate='" + edate + '\'' +
                ", isError='" + isError + '\'' +
                '}';
    }

    public Cube(String trTimeStamp, String trDate, String trTime, String sysCode, String orgCode, String destination, String source, String trNo, String rqGb, String srGb, String trCd, String txCd, String msgCode, String respCd, String traceNo, String userData, String inlen, long eTime, long rTime, String txlen, String txCount, String compressYn, String etc1, String etc2, String etc3, String txSeq, byte[] txdata, String sdate, String edate) {
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
        this.compressYn = compressYn;
        this.etc1 = etc1;
        this.etc2 = etc2;
        this.etc3 = etc3;
        this.txSeq = txSeq;
        this.txdata = txdata;
        this.sdate = sdate;
        this.edate = edate;
    }

    public Cube(String trTimeStamp, String sdate, String edate) {
        this.trTimeStamp = trTimeStamp;
        this.sdate = sdate;
        this.edate = edate;
    }

}
