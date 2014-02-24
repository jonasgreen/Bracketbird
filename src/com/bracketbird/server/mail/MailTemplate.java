package com.bracketbird.server.mail;

import com.bracketbird.server.services.*;
import com.bracketbird.clientcore.language.*;

/**
 *
 */
public class MailTemplate {

    private String title;
    private Language language;
    private String from;

    public MailTemplate(Language l, String title) {
        this(l, title, "Jonas Green, Bracketbird");
    }

    public MailTemplate(Language l, String title, String from) {
        this.from = from;
        this.language = l == null ? Language.ENGLISH : l;
        this.title = title;
    }


    public String createMail(String content, boolean styleInElem) {
        HtmlStringBuffer sb = new HtmlStringBuffer(styleInElem);
        sb.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        sb.append("<html>\n<head>\n");
        if (!styleInElem) {
            sb.append(EmailStyle.getStyle());
        }
        sb.append("\n</head>\n");
        sb.append("<body>\n");
        appendHeader(sb);
        sb.append(content);
        appendGreetings(sb);
        sb.append("\n</body>\n</html>");
        return sb.toString();
    }


    private void appendHeader(HtmlStringBuffer sb) {
        //sb.start("composittable", null).start("row", "logo");
        //sb.start("td", "logo");
        //start(sb, "div", "logo");
        sb.start("a", null, "href=\"http://www.bracketbird.com\"");
        sb.closed("img", "logoImg", "src=\"http://www.bracketbird.appspot.com/bracketbird.png\"");
        sb.end("a");

        //sb.end("td");
        //sb.start("td", null);

        //sb.end("td").end("row").end("composittable");


    }

    private void appendGreetings(HtmlStringBuffer sb) {
        sb.start("div", "divgreetings");
        if (language.isLanguageDanish()) {
            sb.p("Med venlig hilsen", "greetings");
        }
        else{
            sb.p("Best regards", "greetings");
        }
        sb.end("div");

        sb.p("<b>"+from+"</b>", "visitcard");
        sb.p("<a href=\"http://www.bracketbird.com\">www.bracketbird.com</a>", "visitcard");

        // vision

        sb.end("div");

    }



}
