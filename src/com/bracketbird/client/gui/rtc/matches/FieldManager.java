package com.bracketbird.client.gui.rtc.matches;

import com.bracketbird.clientcore.gui.FlowComponent;
import com.bracketbird.clientcore.gui.ImageComponent;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 *
 */
public class FieldManager extends FlowComponent{

    private ImageComponent logoImage;
    private static FieldManager instance;
    private boolean on = false;

    private FieldManager() {
        setStyleName("fieldManager");
        init();
    }

    private void init() {
        add(getLogoImage());
    }

    public static FieldManager getInstance() {
        if (instance == null) {
            instance = new FieldManager();
        }
        return instance;
    }

    public ImageComponent getLogoImage() {
        if (logoImage == null) {
            logoImage = new ImageComponent("fieldManager_greyscale.png");
            logoImage.getImage().setStyleName("fieldManager_logoImage");
            logoImage.getImage().addMouseOverHandler(MouseOver.POINTER);
            logoImage.setTitle("Manage fields");
            logoImage.getImage().addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    logoImageClicked();
                }
            });
        }

        return logoImage;
    }

    private void logoImageClicked() {
        on = !on;
        getLogoImage().getImage().setUrl(on ? "fieldManager.png" : "fieldManager_greyscale.png");
        if(on){
            getLogoImage().addStyleName("rotating");
        }
        else{
            getLogoImage().removeStyleName("rotating");
        }


    }
}
