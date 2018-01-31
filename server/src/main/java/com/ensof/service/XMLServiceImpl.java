package com.ensof.service;

import com.ensof.help.MetaData;
import com.ensof.persistence.XMLReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by SungHere on 2017-12-04.
 */
@Service
public class XMLServiceImpl implements XMLService {

    @Autowired
    XMLReader xmlReader;

    @Value("${mccube.msgs.path}")
    private String MCCUBE_MSGS_PATH;

    @Value("${mccube.config.path}")
    private String MCCUBE_ORG_PATH;

    public String getField(String msgs) {
        String result = "Error";

        try {
            result = xmlReader.getField(msgs);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

    public String getField(String msgs, String data) {
        String result = "Error";
        try {
            result = xmlReader.getField(msgs, data);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }


    @Value("")
    @Override
    public String reload(String command) {
        String result = "S";
        try {
            if (command.equals("MSG")) {
                MetaData.clear("MSG");
                MetaData.readMsgMeta(MCCUBE_MSGS_PATH, "/", 1, ".metaPacket","UTF-8");

            } else if (command.equals("ORG")) {
                MetaData.clear("ORG");
                MetaData.readMsgMeta(MCCUBE_ORG_PATH, "/", 2, "service.ini","EUC-KR");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "F";
        }
        return result;
    }

}
