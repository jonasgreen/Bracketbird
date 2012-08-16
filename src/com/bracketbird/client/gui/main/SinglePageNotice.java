package com.bracketbird.client.gui.main;


import com.bracketbird.client.url.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.util.*;

/**
 *
 */
public class SinglePageNotice extends SimplePanelComponent {
    private LabelComponent title;
    private LabelComponent subtitle;


    public SinglePageNotice(String title, String message) {
        super();
        VerticalComponent vc = new VerticalComponent();
        vc.add(getTitleLabel(), new TextLayout(40, 0, 0, 40).sizeTitle().colorWhite().noWrap());
        getTitleLabel().setText(title);

        vc.add(getSubtitleLabel(), new TextLayout(20, 0, 0, 40).sizeNormal().colorWhite());

        if (!StringUtil.isEmpty(message)) {
            getSubtitleLabel().setText(message);
        }

        ButtonComponent bc = new ButtonComponent("www.bracketbird.com");
        bc.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Window.Location.replace(UrlUtil.getBaseUrl());
            }
        });

        vc.add(bc, new TextLayout(100, 0, 0, 40).sizeSmall().colorBaseDark());
        add(vc);
        setBackgroundColor(Color.backgroundBase());


    }

    public LabelComponent getSubtitleLabel() {
        if (subtitle == null) {
            subtitle = new LabelComponent("");
        }
        return subtitle;
    }

    public LabelComponent getTitleLabel() {
        if (title == null) {
            title = new LabelComponent("");
        }
        return title;
    }


    public void show() {
        RootPanel.get("panel").add(this);
        RootPanel.get("panel").setHeight("100%");

        this.setWidth("100%");
        this.setHeight("100%");

    }
}
