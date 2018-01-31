package com.ensof.model;

import com.ensof.help.SHUtil;

/**
 * Created by SungHere on 2018-01-12.
 */
public class Batch {
    private String sTimeStamp;      // D p key      yyyyMMddHHmmsssss
    private String sTimeStampPattern;      // D p key      yyyyMMddHHmmsssss
    private String startDate;       // D p key      yyyyMMdd
    private String startTime;       //              HHmmssSSS
    private String eTimeStamp;      // D        yyyyMMddHHmmsssss
    private String eTimeStampPattern;      // D        yyyyMMddHHmmsssss
    private String endDate;         //
    private String endTime;         //
    private String sysCode;         //
    private String orgCode;         //
    private String operation;       //
    private String operationKR;       // A= 자동 M = 수동
    private String protocol;        //
    private String srGb;            //
    private String process;         //
    private String status;          //
    private String statusKR;          // D=정상 E = 에러
    private String orgName;         //
    private String orgPath;         //
    private String orgLastMod;      //
    private String orgLength;       //
    private long eTime = 0;         //
    private String transfer;        //              -
    private String baseDate;        //
    private String repName;         //
    private String holiday;         //
    private String overlapPath;      //
    private String workPath;        //
    private String donePath;        //
    private String errorPath;       //
    private String error;           //

    /* 쿼리 조회용 컬럼*/
    private String sdate;
    private String edate;


    public String getsTimeStamp() {
        return sTimeStamp;
    }

    public void setsTimeStamp(String sTimeStamp) {
        this.sTimeStamp = sTimeStamp;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String geteTimeStamp() {
        return eTimeStamp;
    }

    public void seteTimeStamp(String eTimeStamp) {
        this.eTimeStamp = eTimeStamp;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getSrGb() {
        return srGb;
    }

    public void setSrGb(String srGb) {
        this.srGb = srGb;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgPath() {
        return orgPath;
    }

    public void setOrgPath(String orgPath) {
        this.orgPath = orgPath;
    }

    public String getOrgLastMod() {
        return orgLastMod;
    }

    public void setOrgLastMod(String orgLastMod) {
        this.orgLastMod = orgLastMod;
    }

    public String getOrgLength() {
        return orgLength;
    }

    public void setOrgLength(String orgLength) {
        this.orgLength = orgLength;
    }

    public long geteTime() {
        return eTime;
    }

    public void seteTime(long eTime) {
        this.eTime = eTime;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(String baseDate) {
        this.baseDate = baseDate;
    }

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getOverlapPath() {
        return overlapPath;
    }

    public void setOverlapPath(String overlapPath) {
        this.overlapPath = overlapPath;
    }

    public String getWorkPath() {
        return workPath;
    }

    public void setWorkPath(String workPath) {
        this.workPath = workPath;
    }

    public String getDonePath() {
        return donePath;
    }

    public void setDonePath(String donePath) {
        this.donePath = donePath;
    }

    public String getErrorPath() {
        return errorPath;
    }

    public void setErrorPath(String errorPath) {
        this.errorPath = errorPath;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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

    public String getOperationKR() {
        operationKR = "";
        if (operation != null) {
            if (operation.equals("A")) operationKR = "자동";
            else if (operation.equals("M")) operationKR = "수동";
        }
        return operationKR;
    }

    public void setOperationKR(String operationKR) {
        this.operationKR = operationKR;
    }

    public String getStatusKR() {
        statusKR = "";
        if (status != null) {
            if (status.equals("D")) statusKR = "정상";
            else if (status.equals("E")) statusKR = "에러";
        }
        return statusKR;
    }

    public void setStatusKR(String statusKR) {
        this.statusKR = statusKR;
    }

    public Batch() {
    }

    public Batch(String sTimeStamp, String startDate, String startTime, String eTimeStamp, String endDate, String endTime, String sysCode, String orgCode, String operation, String protocol, String srGb, String process, String status, String orgName, String orgPath, String orgLastMod, String orgLength, long eTime, String transfer, String baseDate, String repName, String holiday, String overlapPath, String workPath, String donePath, String errorPath, String error, String sdate, String edate) {
        this.sTimeStamp = sTimeStamp;
        this.startDate = startDate;
        this.startTime = startTime;
        this.eTimeStamp = eTimeStamp;
        this.endDate = endDate;
        this.endTime = endTime;
        this.sysCode = sysCode;
        this.orgCode = orgCode;
        this.operation = operation;
        this.protocol = protocol;
        this.srGb = srGb;
        this.process = process;
        this.status = status;
        this.orgName = orgName;
        this.orgPath = orgPath;
        this.orgLastMod = orgLastMod;
        this.orgLength = orgLength;
        this.eTime = eTime;
        this.transfer = transfer;
        this.baseDate = baseDate;
        this.repName = repName;
        this.holiday = holiday;
        this.overlapPath = overlapPath;
        this.workPath = workPath;
        this.donePath = donePath;
        this.errorPath = errorPath;
        this.error = error;
        this.sdate = sdate;
        this.edate = edate;
    }

    public String getsTimeStampPattern() {

        sTimeStampPattern = SHUtil.getTimePatternYYYYMMDDHHmmssSSS(sTimeStamp);
        return sTimeStampPattern;
    }

    public void setsTimeStampPattern(String sTimeStampPattern) {
        this.sTimeStampPattern = sTimeStampPattern;
    }

    public String geteTimeStampPattern() {
        eTimeStampPattern = SHUtil.getTimePatternYYYYMMDDHHmmssSSS(eTimeStamp);

        return eTimeStampPattern;
    }

    public void seteTimeStampPattern(String eTimeStampPattern) {
        this.eTimeStampPattern = eTimeStampPattern;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "sTimeStamp='" + sTimeStamp + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", eTimeStamp='" + eTimeStamp + '\'' +
                ", endDate='" + endDate + '\'' +
                ", endTime='" + endTime + '\'' +
                ", sysCode='" + sysCode + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", operation='" + operation + '\'' +
                ", protocol='" + protocol + '\'' +
                ", srGb='" + srGb + '\'' +
                ", process='" + process + '\'' +
                ", status='" + status + '\'' +
                ", orgName='" + orgName + '\'' +
                ", orgPath='" + orgPath + '\'' +
                ", orgLastMod='" + orgLastMod + '\'' +
                ", orgLength='" + orgLength + '\'' +
                ", eTime=" + eTime +
                ", transfer='" + transfer + '\'' +
                ", baseDate='" + baseDate + '\'' +
                ", repName='" + repName + '\'' +
                ", holiday='" + holiday + '\'' +
                ", overlapPath='" + overlapPath + '\'' +
                ", workPath='" + workPath + '\'' +
                ", donePath='" + donePath + '\'' +
                ", errorPath='" + errorPath + '\'' +
                ", error='" + error + '\'' +
                ", sdate='" + sdate + '\'' +
                ", edate='" + edate + '\'' +
                '}';
    }
}
