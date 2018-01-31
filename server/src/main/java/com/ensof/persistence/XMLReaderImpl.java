package com.ensof.persistence;

import com.ensof.help.SAXCubeParser;
import com.ensof.model.CubeField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Stack;

/**
 * Created by SungHere on 2017-11-28.
 */
@Repository
public class XMLReaderImpl implements XMLReader {


    public static SAXCubeParser saxCubeParser;

    @Value("${mccube.msgs.path}")
    private String MCCUBE_MSGS_PATH;


    @Override
    public String getField(String msgs) throws ParserConfigurationException, SAXException, IOException {
        return getField(msgs, null);
    }

    @Override
    public String getField(String msgs, String data) throws ParserConfigurationException, SAXException, IOException {

        saxCubeParser = new SAXCubeParser(data) {
            @Override
            protected void process(String path, String qName, Attributes attributes) throws SAXException {


                CubeField cube = null;


                if (qName.equals("include")) {
                    XMLReaderImpl.saxCubeParser.parse(MCCUBE_MSGS_PATH + attributes.getValue("path"));

                } else {

                    String name = attributes.getValue("name");


                    cube = new CubeField(
                            qName,
                            path,
                            attributes.getValue("desc"),
                            attributes.getValue("format"),
                            attributes.getValue("length"),
                            name,
                            attributes.getValue("nullable"),
                            attributes.getValue("padding"),
                            attributes.getValue("trim"),
                            attributes.getValue("xml"),
                            attributes.getValue("lengthField"),
                            attributes.getValue("type")

                    );
                    Stack<CubeField> groupStack = super.getGroupStack();

                    if (qName.toLowerCase().equals("group")) {

                        groupStack.push(cube);

                    } else {

                        if (!groupStack.empty()) {
                            groupStack.peek().getFields().put(name, cube);
                        } else {
                            this.getCubeFields().put(name, cube);
                        }
                    }
                }
            }
        }; //saxCubeParser impl end


        String result = saxCubeParser.parse(MCCUBE_MSGS_PATH + msgs + ".xml");

        return result;
    }


}
