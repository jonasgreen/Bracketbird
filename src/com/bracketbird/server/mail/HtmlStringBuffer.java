package com.bracketbird.server.mail;

import com.bracketbird.server.services.EmailStyle;

import java.util.List;

/**
 *
 */
public class HtmlStringBuffer {
    private StringBuffer sb = new StringBuffer();

    private boolean styleInElement = false;

    public HtmlStringBuffer(boolean styleInElem) {
        this.styleInElement = styleInElem;
    }


    public HtmlStringBuffer start(String name) {
        return start(name, null);
    }

    public HtmlStringBuffer start(String name, String cssClass) {
        return start(name, cssClass, null);
    }

    public HtmlStringBuffer start(String name, String cssClass, String attributes) {
        sb.append("<").append(name);
        addStyle(cssClass);
        if(attributes != null){
            append(" ").append(attributes);
        }
        sb.append(">");
        return this;
    }

    public HtmlStringBuffer p(String content) {
        return p(content, "p");
    }


    public HtmlStringBuffer p(String content, String cssClass) {
        start("p", cssClass);
        sb.append(content);
        return end("p");
    }

    public HtmlStringBuffer end(String name) {
        sb.append("</").append(name).append(">\n");
        return this;
    }

    public HtmlStringBuffer closed(String name, String cssClass) {
        return closed(name, cssClass, null);
    }

    public HtmlStringBuffer closed(String name, String cssClass, String attributes) {
        return start(name, cssClass, attributes).end(name);
    }

    public HtmlStringBuffer append(String s) {
        sb.append(s);
        return this;
    }

    public String toString() {
        return sb.toString();
    }

    private HtmlStringBuffer addStyle(String cssClass) {
        List<String> list = EmailStyle.getStyle(cssClass);
        if (list == null || list.isEmpty()) {
            return this;
        }
        if(!styleInElement){
            append(" class=").append(cssClass);
            return this;
        }
        append(" style=\"");
        for (String s : list) {
            sb.append(s).append(";");
        }
        append("\"");
        return this;
    }


}
