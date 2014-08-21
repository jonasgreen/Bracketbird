package com.bracketbird.client.gui.main;


import com.bracketbird.client.url.UrlUtil;
import com.bracketbird.clientcore.appcontrol.Page;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.Horizontal;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;


/**
 *
 */
public class ErrorPage extends Page<ErrorPageController> {

    private FlowComponent content;

    public static double LOGO_HEIGHT = 70;
    public static double PAGE_WIDTH = 900;
    private LabelComponent header;
    private LabelComponent text;
    private LabelComponent button;

    public ErrorPage() {
        super();
        content = new FlowComponent();
        ScrollPanelComponent customerScrollPanelComponent = new ScrollPanelComponent(content);
        //add(customerScrollPanelComponent);
    }

    public void init() {
        content.getElement().getStyle().setProperty("marginLeft", "auto");
        content.getElement().getStyle().setProperty("marginRight", "auto");

        SimplePanelComponent holder = new SimplePanelComponent();
        VerticalComponent vc = new VerticalComponent();

        vc.add(getHeader(), new TextLayout().sizeH1().colorWhite().paddingTop(40));
        vc.add(getText(), new TextLayout().sizeH3().colorWhite().paddingTop(20).paddingBottom(200));
        vc.add(getButton());


        holder.add(vc, new TextLayout(null, PAGE_WIDTH + "px", Horizontal.CENTER));
        content.add(holder, new TextLayout("400px", "100%").margin(100,0,0,0).backgroundBlue());
        StyleIt.add(this, new TextLayout().backgroundBlue());

    }


    public LabelComponent getHeader() {
        if (header == null) {
            header = new LabelComponent("Unable to load page");
        }
        return header;
    }

    public LabelComponent getText() {
        if (text == null) {
            text = new LabelComponent("");
        }
        return text;
    }

    public LabelComponent getButton() {
        if (button == null) {
            button = new LabelComponent("Return to Bracketbird.com");
            button.setStyleName("colorbutton3");

            button.addMouseOver(MouseOver.POINTER);
            button.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    Window.Location.assign(UrlUtil.getBaseUrl());
                }
            });

        }
        return button;
    }

    protected void setSubPageHolder(Page subPage) {
        //ignore
    }






}
