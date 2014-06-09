package com.bracketbird.client.gui.rtc;

import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 *
 */
public class LockPanel extends HorizontalComponent{

    private LabelComponent label;

    public LockPanel() {
        add(getLabel(), new TextLayout(Vertical.MIDDLE).sizeSmall().colorBaseDark().paddingTop(10).underline());
    }


    public LabelComponent getLabel() {
        if (label == null) {
            label = new LabelComponent("Share");
            label.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    shareClicked();
                }
            });
            label.addMouseOver(MouseOver.POINTER);
            label.setTitle("Share this tournament.");
        }
        return label;
    }

    private void shareClicked() {
        ShareComponent gc = new ShareComponent(RTC.getInstance().getTournament().getViewUrl(), RTC.getInstance().getTournament().getUrl());
        PopupManager.show(gc, null);
        gc.getCloseButton().getButton().setFocus(true);
    }





}
