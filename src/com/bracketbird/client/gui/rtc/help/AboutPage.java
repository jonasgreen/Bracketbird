package com.bracketbird.client.gui.rtc.help;


import com.bracketbird.client.gui.rtc.RTCLayoutFac2;
import com.bracketbird.clientcore.appcontrol.Page;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.Horizontal;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;
import com.bracketbird.clientcore.style.Vertical;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;

/**
 *
 */
public class AboutPage extends Page<AboutPageController> {

    private FlowComponent content;

    public AboutPage() {
        super();
        content = new FlowComponent();
        initWidget(content);
    }

    public void init() {
        content.add(new ImageComponent("timetoplay.png"), new TextLayout("300px", "720px"));
        content.add(new LabelComponent("About"), RTCLayoutFac2.h1());

        StringBuilder sb = new StringBuilder(".l kaædlkfjasældkjfæalskd fæalksdjf æalksdjf ælaksdj fælaksdjfæ laksdjfæ laksjdfæ lakjsd.l kaædlkfjasældkjfæalskd fæalksdjf æalksdjf ælaksdj fælaksdjfæ laksdjfæ laksjdfæ lakjsd.l kaædlkfjasældkjfæalskd fæalksdjf æalksdjf ælaksdj fælaksdjfæ laksdjfæ laksjdfæ lakjsd.l kaædlkfjasældkjfæalskd fæalksdjf æalksdjf ælaksdj fælaksdjfæ laksdjfæ laksjdfæ lakjsd.l kaædlkfjasældkjfæalskd fæalksdjf æalksdjf ælaksdj fælaksdjfæ laksdjfæ laksjdfæ lakjsd.l kaædlkfjasældkjfæalskd fæalksdjf æalksdjf ælaksdj fælaksdjfæ laksdjfæ laksjdfæ lakjsd.l kaædlkfjasældkjfæalskd fæalksdjf æalksdjf ælaksdj fælaksdjfæ laksdjfæ laksjdfæ lakjsd.l kaædlkfjasældkjfæalskd fæalksdjf æalksdjf ælaksdj fælaksdjfæ laksdjfæ laksjdfæ lakjsd.l kaædlkfjasældkjfæalskd fæalksdjf æalksdjf ælaksdj fælaksdjfæ laksdjfæ laksjdfæ lakjsd.l kaædlkfjasældkjfæalskd fæalksdjf æalksdjf ælaksdj fælaksdjfæ laksdjfæ laksjdfæ lakjsd.l kaædlkfjasældkjfæalskd fæalksdjf æalksdjf ælaksdj fælaksdjfæ laksdjfæ laksjdfæ lakjsd. l kaædlkfjasældkjfæalskd fæalksdjf æalksdjf ælaksdj fælaksdjfæ laksdjfæ laksjdfæ lakjsd");
        content.add(new HtmlComponent(sb.toString()), new TextLayout(null, "700px"));

        ButtonComponent close = new ButtonComponent("close");
        close.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                PopupManager.hide();
            }
        });
        content.add(close, LayoutFac.button().margin(20,0,0,0).horizontalAlignRight());
    }




    protected void setSubPageHolder(Page subPage) {
        //ignore
    }
}