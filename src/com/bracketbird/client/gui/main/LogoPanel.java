package com.bracketbird.client.gui.main;

import com.bracketbird.client.LogoDiv;
import com.bracketbird.clientcore.gui.HorizontalComponent;
import com.bracketbird.clientcore.gui.ImageComponent;
import com.bracketbird.clientcore.gui.SimplePanelComponent;
import com.bracketbird.clientcore.style.Horizontal;
import com.bracketbird.clientcore.style.TextLayout;
import com.bracketbird.clientcore.style.Vertical;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 *
 */
public class LogoPanel {

    private HorizontalComponent content;

    private static LogoPanel instance;

    private SimplePanelComponent imageHolder;

    private LogoPanel() {
    }



    public static LogoPanel getInstance() {
        if (instance == null) {
            instance = new LogoPanel();
        }
        return instance;
    }

    public SimplePanelComponent getImageHolder() {
        if (imageHolder == null) {
            imageHolder = new SimplePanelComponent();
        }
        return imageHolder;
    }

    public void setImage(String url){
        ImageComponent logoImage = new ImageComponent(url);
        //logoImage.getImage().addMouseOverHandler(MouseOver.POINTER);
        logoImage.getImage().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                //UserManager.getInstance().loadTransitionPage();
            }
        });
        getImageHolder().add(logoImage);
    }




      public HorizontalComponent getPanel() {
        if (content == null) {
            content = new HorizontalComponent();
            content.add(new LogoDiv(), new TextLayout(0,0,10,0,"40px", "175px", Horizontal.LEFT, Vertical.BOTTOM));
            content.add(new SimplePanelComponent(), new TextLayout(null, "100%"));
            //content.add(getLogoMenues(), new TextLayout(0,0,10,0,Horizontal.RIGHT, Vertical.BOTTOM));
            //setImage("bracketbird.png");
        }
        return content;
    }



}
