package com.bracketbird.client.gui.rtc.health;


import com.bracketbird.client.gui.rtc.RTCLayoutFac2;
import com.bracketbird.clientcore.appcontrol.Page;
import com.bracketbird.clientcore.gui.LabelComponent;
import com.bracketbird.clientcore.gui.VerticalComponent;
import com.bracketbird.clientcore.style.StyleIt;

/**
 *
 */
public class LogPage extends Page<LogPageController> {

    private VerticalComponent content;

    public LogPage() {
        super();
        content = new VerticalComponent();
        add(content);
    }

    public void init() {
        StyleIt.add(content, RTCLayoutFac2.CONTENT.backgroundWhite());

        content.add(new LabelComponent("Activity log"), RTCLayoutFac2.h1().margin(0, 0, 30, 0));

    }


    protected void setSubPageHolder(Page subPage) {
        //ignore
    }

    public VerticalComponent getContent() {
        return content;
    }
}