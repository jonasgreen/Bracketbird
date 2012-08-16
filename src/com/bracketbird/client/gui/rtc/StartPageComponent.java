package com.bracketbird.client.gui.rtc;

import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.gui.FlowComponent;
import com.bracketbird.clientcore.gui.ImageComponent;
import com.bracketbird.clientcore.gui.LabelComponent;
import com.bracketbird.clientcore.style.TextLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;

/**
 *
 */
public class StartPageComponent extends FlowComponent {

    private LabelComponent label;
    private String labelValue;
    private String imageUrl;
    private ImageComponent image;
    private StartPageComponentManager manager;
    private PageController pageController;
    private ClickHandler clickHandler = new ClickHandler() {
        public void onClick(ClickEvent event) {
            if (manager != null) {
                manager.clicked(StartPageComponent.this);
            }
        }
    };
    private int number;

    public StartPageComponent(PageController ctrl, String labelValue, String imageUrl, int number) {
        this.pageController = ctrl;
        this.labelValue = labelValue;
        this.imageUrl = imageUrl;
        this.number = number;
        init();
    }

    public void init() {
        add(getImage());
        add(getLabel(), new TextLayout().colorBaseDark().sizeH1().padding(90, 0, 0, 170).underline());
    }


    public void animateImage() {
        getImage().getElement().getStyle().setProperty("width", "60px");
        getImage().getElement().getStyle().setProperty("height", "60px");

        int transformToX = Window.getClientWidth() - 450 + (number*80);
        int transformToY = -120 - (number * 155);
        String translateValue = "translate(" + transformToX + "px, "+ transformToY + "px)";

        getImage().getElement().getStyle().setProperty("transform", translateValue);
        getImage().getElement().getStyle().setProperty("MozTransform", translateValue);
        getImage().getElement().getStyle().setProperty("WebkitTransform", translateValue);
        getImage().getElement().getStyle().setProperty("OTransform", translateValue);

    }

    public void animateLabel() {
        getLabel().getElement().getStyle().setProperty("opacity", "0.0");
    }

    public ImageComponent getImage() {
        if (image == null) {
            image = new ImageComponent(imageUrl);
            image.setStyleName("startImage");
            image.getImage().addClickHandler(clickHandler);
        }
        return image;
    }

    public LabelComponent getLabel() {
        if (label == null) {
            label = new LabelComponent(labelValue);
            label.setStyleName("startLabel");
            label.addClickHandler(clickHandler);
        }
        return label;
    }

    public void setManager(StartPageComponentManager manager) {
        this.manager = manager;
    }

    public PageController getPageController() {
        return pageController;
    }
}
