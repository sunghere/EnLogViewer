package com.ensof.help;

/**
 * Created by SungHere on 2017-12-05.
 */
public class IconUtill {


    public static String getIcon(String name) {
        String icon = "";

        if (name.equals("group")) {
            icon = "<i class=\"fa fa-object-group fa-2\" aria-hidden=\"true\"></i>&nbsp;";
        } else if (name.equals("end")) {

            icon = "<i class=\"fa fa-code\" aria-hidden=\"true\"></i>&nbsp;";
        } else if (name.equals("number")) {
            icon = "<i class=\"mdi mdi-numeric mdi-18px mdi-inactive\"></i>";

        } else if (name.equals("string")) {
            icon = "<i class=\"mdi mdi-alphabetical mdi-18px mdi-inactive\"></i>";

        } else if (name.equals("date")) {
            icon = "<i class=\"fa fa-calendar fa-2\" aria-hidden=\"true\"></i>";
        } else if (name.equals("in")) {
            icon = "<i class=\"mdi mdi-information-variant mdi-18px\"></i>";
        } else if (name.equals("out")) {
            icon = "<i class=\"fa fa-arrow-circle-right fa-2\" aria-hidden=\"true\"></i> ";
        }  else if (name.equals("info")) {
            icon = "<i class=\"mdi mdi-information-variant mdi-18px\"></i>";

        } else {
            icon = "<i class=\"fa fa-asterisk fa-2\" aria-hidden=\"true\"></i>";
        }



        return icon;
    }
}
