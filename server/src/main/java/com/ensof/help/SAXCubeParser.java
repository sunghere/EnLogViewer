package com.ensof.help;

import com.ensof.model.CubeField;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Stack;

/**
 * Created by SungHere on 2017-11-28.
 */
public abstract class SAXCubeParser extends DefaultHandler {


    private Stack<String> pathStack = new Stack<>();
    private LinkedHashMap<String, CubeField> cubeFields;
    private int includeCnt = 0;
    private int sOffset = 0;
    private int eOffset = 0;
    private Stack<CubeField> groupStack = new Stack<>();
    private StringBuffer result = new StringBuffer("<table>" +
            "<thead>" +
            "       <tr>" +
            "          <th>info</th><th>value</th><th>offset<th>" +
            "       </tr>" +
            "</thead><tbody>");

    private byte[] data;

    public String parse(String path) throws SAXException {
        SAXParser parser = null;
        try {
            System.out.println("[MSG READING START] " + path);
            SAXParserFactory spf = SAXParserFactory.newInstance();
            parser = spf.newSAXParser();
            parser.parse(path, this);
        } catch (FileNotFoundException e) {
            throw new SAXException("SAX parser Path Error[" + path + "]", e);

        } catch (SAXException e) {
            throw e;
        } catch (Exception e) {
            throw new SAXException("SAX parser Error", e);
        }

        if (includeCnt == 0)
            return cubeMapping();
        else
            return null;
    }

    public Stack<CubeField> getGroupStack() {
        return groupStack;
    }

    protected String getPath() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < pathStack.size(); i++) {
            buffer.append("/").append(pathStack.get(i));
        }
        return buffer.toString();
    }

    protected boolean tagContains(String tag) {

        boolean result = false;
        tag = tag.toLowerCase();
        if (tag.equals("tag"))
            result = true;
        else if (tag.equals("packet"))
            result = true;
        else if (tag.equals("mapping"))
            result = true;
        else if (tag.equals("element"))
            result = true;

        return result;
    }

    protected LinkedHashMap<String, CubeField> getCubeFields() {
        return cubeFields;
    }

    protected synchronized String cubeMapping() {


        Iterator<String> its = cubeFields.keySet().iterator();
        while (its.hasNext()) {
            CubeField cube = cubeFields.get(its.next());


            cubeFields.put(cube.getName(), valueMapping(cube));

        }

        result.append("</tbody></table>");
        return result.toString();
    }

    public synchronized CubeField valueMapping(CubeField cube) {
        if (cube.getLengthField() != null && !cube.getLengthField().trim().equals("")) {
            CubeField tempField = cubeFields.get(cube.getLengthField());
            String tempLength = tempField.getValue();
            cube.setLength(tempField.getValue());

            if (tempLength.equals("") || tempLength == null) {
                System.err.println("LengthField value NotFound [FieldName : " + cube.getName() + "/ LengthField : " + cube.getLengthField() + "][Value : " + tempField.getValue() + "][offset : " + tempField.getsOffset() + ":" + tempField.geteOffset());
            }

        }


        if (cube.getTagName().equals("group")) {
            int gLength = 0;
            try {
                gLength = Integer.parseInt(cube.getLength().trim());
            } catch (NumberFormatException e) {
                System.err.println("NumberFormatException[ FieldName : " + cube.getName() + "] [ LengthField : " + cube.getLengthField() + "][TagName : " + cube.getTagName() + "]" + e);
            }
            for (int i = 0; i < gLength; i++) {
                Iterator<String> its = cube.getFields().keySet().iterator();
                cube.setgNum((i + 1) + "");
                result.append(cube);

                while (its.hasNext()) {
                    CubeField temp = cube.getFields().get(its.next());
                    cube.getFields().put(temp.getName(), valueMapping(temp));

                }
            }


        } else {
            int length = 0;

            try {
                length = Integer.parseInt(cube.getLength().trim());
            } catch (NumberFormatException e) {
                System.err.println("NumberFormatException[FieldName : " + cube.getName() + "][Last Offset :" + sOffset + ":+" + eOffset + "]" + "[Length :" + cube.getLength() + "]" + e);
            } catch (Exception e) {
                System.err.println("Exception [FieldName : " + cube.getName() + "][Last Offset :" + sOffset + ":+" + eOffset + "]" + "[Length :" + cube.getLength() + "]" + e);

            }

            eOffset = sOffset + length;
            cube.setsOffset(sOffset + "");
            cube.seteOffset(eOffset - 1 + "");
            try {
                byte[] subData = new byte[eOffset - sOffset];

                System.arraycopy(data, sOffset, subData, 0, eOffset - sOffset);
                cube.setValue(new String(subData));

            } catch (Exception e) {
                cube.setValue("Mapping Error");
                System.out.println("Mapping Error [sOffset :" + sOffset + "| eOffset : " + eOffset + "]" + e);
            }
            sOffset = eOffset;
            result.append(cube);

        }
        return cube;
    }

    protected SAXCubeParser() {

        if (getCubeFields() != null) cubeFields.clear();

        cubeFields = new LinkedHashMap<String, CubeField>();
    }

    protected SAXCubeParser(String data) {
        this();
        if (data != null) this.data = data.getBytes();
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);


        if (tagContains(qName)) { // 필요없는 태그들 제거
            return;
        }

        if (qName.equals("include")) {
            includeCnt++;
        } else {
            pathStack.push(qName);
        }
        process(getPath(), qName, attributes);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if (tagContains(qName)) { // 필요없는 태그들 제거
            return;
        }


        if (qName.toLowerCase().equals("include")) {
            includeCnt--;
            if (includeCnt < 0) {
                throw new SAXException("include Check Error");
            }
        } else if (qName.toLowerCase().equals("group")) {
            CubeField cubeField = groupStack.pop();
            if (groupStack.empty()) {
                cubeFields.put(cubeField.getName(), cubeField);
            } else {
                groupStack.peek().getFields().put(cubeField.getName(), cubeField);
            }
            pathStack.pop();

        } else {
            pathStack.pop();
        }

    }


    protected abstract void process(String path, String qName, Attributes attributes) throws SAXException;


}
