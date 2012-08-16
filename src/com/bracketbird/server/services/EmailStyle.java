package com.bracketbird.server.services;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 *
 */public class EmailStyle {

    private static String BACKGROUND = "background";
    private static String HEIGHT = "height";
    private static String WIDTH = "width";

    private static Map<String, List<String>> styles = new HashMap<String, List<String>>();

    private static String css;
    private static StringBuffer sb;

    public static String getStyle() {
        if (css == null) {
            sb = new StringBuffer();
            sb.append("<style TYPE=\"text/css\">\n");

            addCssElements();

            sb.append("</style>\n");
            css = sb.toString();
        }
        return css;
    }


    private static void addCssElements() {
        add("p", "margin-right:10px");
        add(".menu", BACKGROUND + ":#466c3a", HEIGHT + ":35px", WIDTH + ":100%", "margin-top:10px", "margin-bottom:30px");
        add(".logo",WIDTH + ":100%");
        add(".logoImg", "border:none");
        add(".title", "color:white", "padding-top:10px", "padding-left:10px", "margin:0px");

        add(".menuBottom", BACKGROUND + ":#0582B3", HEIGHT + ":1px", WIDTH + ":100%", "margin-top:50px", "margin-bottom:10px");

        add(".tablegreetings", "padding-top:45px", "padding-bottom:10px", "padding-left:0px", "border-width:0px", "border-spacing:0px");
        add(".divgreetings", "padding-top:45px", "padding-bottom:10px", "padding-left:0px");

        add(".greetings", "margin-bottom:0px");
        add(".visitcard", "margin-top:0px","margin-bottom:0px");
        add(".visitcardLast", "margin-top:0px");


        add(".vision", "margin-left:8px", "margin-right:7px", "font-size:85%", "background:#FF8B4D", "border:1px solid", "border-color:#0582B3");
        add(".pvision", "padding-left:5px", "padding-right:5px");
        add(".tablevision", "padding-top:15px", "margin-left:0px", "border-width:0px", "border-spacing:0px");



    }


    private static void add(String name, String... values) {
        List<String> list = new ArrayList<String>();
        if (name.startsWith(".")) {
            styles.put(name.substring(1), list);
        }
        else {
            styles.put(name, list);
        }

        sb.append(name).append("{\n");
        for (String value : values) {
            list.add(value);
            sb.append("    ").append(value).append(";\n");
        }
        sb.append("}\n");

    }

    public static List<String> getStyle(String cssClass) {
        if (css == null) {
            sb = new StringBuffer();
            sb.append("<style TYPE=\"text/css\">\n");

            addCssElements();

            sb.append("</style>\n");
            css = sb.toString();
        }
        return styles.get(cssClass);
    }
}
