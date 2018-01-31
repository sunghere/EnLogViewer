package com.ensof.help;

import org.springframework.util.Base64Utils;

/**
 * Created by SungHere on 2017-11-01.
 */
public class Log {
  /*  public synchronized Log next() throws Exception {
        if(current == null) {
            if(_next()) {
                TxLog txLog = new TxLog();

                String timestamp = rset.getString("TRTIMESTAMP");
                String trNo = rset.getString("TRNO");

                txLog.timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").parse(timestamp).getTime();
                txLog.trNo = trNo;
                txLog.sysCode = rset.getString("SYSCODE");
                txLog.orgCode = rset.getString("ORGCODE");
                txLog.dstAdapter = rset.getString("DESTINATION");
                txLog.srcAdapter = rset.getString("SOURCE");
                String str = rset.getString("RQGB");
                txLog.rqGb = !EnUtility.isBlank(str) ? str.charAt(0) : ' ';
                str = rset.getString("SRGB");
                txLog.srGb = !EnUtility.isBlank(str) ? str.charAt(0) : ' ';
                txLog.messageCode = rset.getString("MSGCODE");
                txLog.txCd = rset.getString("TXCD");
                txLog.trCd = rset.getString("TRCD");
                txLog.respCd = rset.getString("RESPCD");
                txLog.traceNo = rset.getString("TRACENO");
                txLog.userData = rset.getString("USERDATA");
                txLog.etc = rset.getString("ETC1");
                txLog.channel = rset.getString("ETC2");
                txLog.ioLength = rset.getInt("INLEN");
                txLog.etime = rset.getLong("ETIME");
                txLog.rtime = rset.getInt("RTIME");
                txLog.messageLength = rset.getInt("TXLEN");
                txLog.contentType = rset.getString("ETC3");
                boolean msgEncoding = true;
                if(!EnUtility.isBlank(txLog.contentType)) {
                    String[] tokens = EnUtility.splitString(txLog.contentType, '|');
                    if(tokens.length > 0) {
                        txLog.contentType = tokens[0];
                    }
                    if(tokens.length > 1) {
                        msgEncoding = "E".equals(tokens[1]);
                    }
                    if(tokens.length > 2) {
                        txLog.encoding = tokens[2];
                    }
                }

                TxLogQueryCondition condition = ((TxLogQueryContext)getQueryContext()).getTxLogQueryCondition();
                if(condition.detailSupport) {
                    txLog.compressed = "Y".equals(rset.getString("COMPRESS_YN"));
                    int txSeq = rset.getInt("TXSEQ");

                    str = rset.getString("TXDATA");
                    StringBuffer message = new StringBuffer(str != null ? str : "");
                    while(_next()) {
                        if(timestamp.equals(rset.getString("TRTIMESTAMP")) && trNo.equals(rset.getString("TRNO")) && txSeq < rset.getInt("TXSEQ")) {
                            str = rset.getString("TXDATA");
                            if(str != null) {
                                message.append(str);
                            }
                        } else {
                            lastNextResult = Boolean.TRUE;
                            break;
                        }
                    }
                    if(message.length() > 0) {
                        if(msgEncoding) {
                            txLog.txData = Base64Coder.decode(message.toString());
                        } else {
                            if(txLog.encoding == null) {
                                txLog.txData = message.toString().getBytes();
                            } else {
                                try {
                                    txLog.txData = message.toString().getBytes(txLog.encoding);
                                } catch(UnsupportedEncodingException e) {
                                    txLog.txData = message.toString().getBytes();
                                }
                            }
                        }
                    }
                }

                current = txLog;
            }
        }

        return current;
    }
*/

}
