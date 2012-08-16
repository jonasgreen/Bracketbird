package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PushDownImage extends VerticalComponent {

    private List<PushDownImageHandler> handlers = new ArrayList<PushDownImageHandler>();

    private ImageComponent onImage;
    private ImageComponent offImage;

    private boolean selected = false;


    public PushDownImage(ImageComponent on, ImageComponent off) {
        super();
        onImage = on;
        offImage = off;

        this.add(off);
        addClicker(new ClickHandler() {
            public void onClick(ClickEvent event) {
                for (PushDownImageHandler handler : handlers) {
                    handler.pushedDown(PushDownImage.this);
                }
                setSelected(true);
            }
        });

        addMouseOver(new MouseOverHandler() {
            public void onMouseOver(MouseOverEvent event) {
                if (!isSelected()) {
                    offImage.getImage().getElement().getStyle().setProperty("cursor", "pointer");
                }
                else {
                    onImage.getElement().getStyle().setProperty("cursor", "auto");

                }
            }
        });
    }

    private void addClicker(ClickHandler c) {
        onImage.getImage().addClickHandler(c);
        offImage.getImage().addClickHandler(c);

    }

    private void addMouseOver(MouseOverHandler h) {
        onImage.getImage().addMouseOverHandler(h);
        offImage.getImage().addMouseOverHandler(h);
    }


    public void addPushDownHandler(PushDownImageHandler handler) {
        handlers.add(handler);
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean setAsSelected) {
        if (setAsSelected == selected) {
            return;
        }
        selected = setAsSelected;
        if (selected) {
            offImage.removeFromParent();
            this.add(onImage);
        }
        else {
            onImage.removeFromParent();
            this.add(offImage);
        }
    }

    public ImageComponent getOnImage() {
        return onImage;
    }

    public ImageComponent getOffImage() {
        return offImage;
    }
}