package com.ensof.model;


import com.ensof.help.IconUtill;

import java.util.LinkedHashMap;

/**
 * Created by SungHere on 2017-11-28.
 */
public class CubeField {

    private String tagName;

    private String path;
    private String desc;
    private String format;
    private String length = "0";
    private String name;
    private String nullable;

    private String padding = " ";
    private String trim = "false";
    private String xml = "element";
    private String defaultValue;

    private String lengthField;
    private String value = "";

    private String type;

    private String sOffset;
    private String eOffset;

    private String gNum;

    private LinkedHashMap<String, CubeField> fields;

    @Override
    public String toString() {
        if (type == null) type = tagName;

        if (tagName.equals("group")) {
            StringBuffer sb = new StringBuffer();


            sb.append("<tr class='cube-field' field-type='" + type + "'>" +
                    "<th colspan='2' class='cube-field-header'>" + getDepth() + IconUtill.getIcon("group") + desc + "&nbsp;" + "<strong>" + name + "</strong>" + "[" + gNum + "]</th><td></td></tr>");


            return sb.toString();


        }
        String resultStr = "<tr class='cube-field''>" +
                "<th class='cube-field-header'>" + getDepth() + IconUtill.getIcon(type) + desc + "&nbsp;" + name + "[" + length + "]</th><td class='cube-field-value'><span onclick='span_copy_to_clipboard(this)' class='cube-field-value-box'>" + value + "</span></td><td class='cube-field-offset'>" + sOffset + ":" + eOffset + "</td></tr>";
        return resultStr;
    }


    public String getDepth() {
        StringBuffer sb = new StringBuffer();
        int count = path.split("/").length;

        for (int i = 2; i < count; i++) {

            sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        }


        return sb.toString();

    }


    public CubeField() {
    }

    public CubeField(String tagName, String path, String desc, String format, String length, String name, String nullable, String padding, String trim, String xml, String lengthField, String type) {
        this.tagName = tagName;
        this.path = path;
        this.desc = desc;
        this.format = format;
        this.length = length;
        this.name = name;
        this.nullable = nullable;
        this.padding = padding;
        this.trim = trim;
        this.xml = xml;
        this.lengthField = lengthField;
        this.type = type;

        if (tagName.equals("group")) fields = new LinkedHashMap<>();

    }

    public String getgNum() {
        return gNum;
    }

    public void setgNum(String gNum) {
        this.gNum = gNum;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getLengthField() {
        return lengthField;
    }

    public void setLengthField(String lengthField) {
        this.lengthField = lengthField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LinkedHashMap<String, CubeField> getFields() {
        return fields;
    }

    public void setFields(LinkedHashMap<String, CubeField> fields) {
        this.fields = fields;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        if (length == null) {
            length = "0";
        }
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    public String getPadding() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getsOffset() {
        return sOffset;
    }

    public void setsOffset(String sOffset) {
        this.sOffset = sOffset;
    }

    public String geteOffset() {
        return eOffset;
    }

    public void seteOffset(String eOffset) {
        this.eOffset = eOffset;
    }
}
