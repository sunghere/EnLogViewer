package com.ensof.persistence;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by SungHere on 2017-11-28.
 */


public interface XMLReader {


    String getField(String msgs) throws ParserConfigurationException, SAXException, IOException;

    String getField(String msgs, String data) throws ParserConfigurationException, SAXException, IOException;


}
