package com.bracketbird.clientcore.gui;

import com.google.gwt.user.client.ui.HTML;

/**
 *
 */
public class HtmlComponent extends GuiComponent{
    private HTML htmlContent;

    public HtmlComponent(){
        this("");
    }

    public HtmlComponent(String html) {
        super();
        htmlContent = new HTML(html);
        initWidget(htmlContent);
    }

    public void setHtml(String html){
        htmlContent.setHTML(html);
    }

    public HTML getHtmlContent() {
        return htmlContent;
    }
}
