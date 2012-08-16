package com.bracketbird.client.gui.rtc;

import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.TextLayout;
import com.bracketbird.clientcore.style.Vertical;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 *
 */
public class EjectInOutPanel extends FlowComponent{

    private ImageComponent ejectIn;
    private ImageComponent ejectOut;
    private ImageComponent active;
    private ClickHandler handler;

    public EjectInOutPanel(ClickHandler handler) {
        this.handler = handler;
        setImage(getEjectIn());
    }

    private void setImage(ImageComponent image){
        if(active != null){
            active.removeFromParent();
        }
        active =image;
        add(image, new TextLayout("24px", "24px"));
    }


    public ImageComponent getEjectOut() {
        if (ejectOut == null) {
            ejectOut = new ImageComponent("ejectout.png");
            ejectOut.getImage().addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    setImage(getEjectIn());
                    handler.onClick(event);
                }
            });
            ejectOut.getImage().addMouseOverHandler(MouseOver.POINTER);
            ejectOut.setTitle("Expand top panel");
        }
        return ejectOut;
    }

    public ImageComponent getEjectIn() {
        if (ejectIn == null) {
            ejectIn = new ImageComponent("ejectin.png");
            ejectIn.getImage().addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    setImage(getEjectOut());
                    handler.onClick(event);
                }
            });
            ejectIn.getImage().addMouseOverHandler(MouseOver.POINTER);
            ejectIn.setTitle("Contract top panel");
        }
        return ejectIn;
    }








}
