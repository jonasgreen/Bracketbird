package com.bracketbird.client.gui.main;


import com.bracketbird.client.Logo;
import com.bracketbird.client.LogoDiv;
import com.bracketbird.clientcore.appcontrol.Application;
import com.bracketbird.clientcore.appcontrol.Page;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.Horizontal;
import com.bracketbird.clientcore.style.TextLayout;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;


/**
 *
 */
public class FrontPage extends Page<FrontPageController> implements TopPanelHolder {
    public static double LOGO_HEIGHT = 70;
    public static double PAGE_WIDTH = 900;
    private LabelComponent createTournament;
    private HorizontalComponent centerPanel;
    private FlowComponent slogans;
    public FlowPanel content = new FlowPanel();
    private FlowPanel top;
    private FlowPanel bottom;
    private Logo logoDiv;


    public FrontPage() {
        super();
        initWidget(content);
    }

    public void init() {
        content.setStyleName("frontPage");
        content.add(getLogoDiv());
        content.add(getTop());
        content.add(getBottom());
    }

    public Logo getLogoDiv() {
        if (logoDiv == null) {
            logoDiv = new Logo();
        }
        return logoDiv;
    }

    public FlowPanel getTop() {
        if (top == null) {
            top = new FlowPanel();
        }
        return top;
    }

    public FlowPanel getBottom() {
        if (bottom == null) {
            bottom = new FlowPanel();
            SloganPage sloganPage = new SloganPage();
            sloganPage.setWidth("900px");
            bottom.add(sloganPage);
        }
        return bottom;
    }


    private void addSlogan(FlowComponent div) {
        FlowComponent fl2 = new FlowComponent();
            }


    protected void setSubPageHolder(Page subPage) {
        //ignore
    }

    public HorizontalComponent getCenterPanel() {
        if (centerPanel == null) {

            centerPanel = new HorizontalComponent();
            VerticalComponent centerRight = new VerticalComponent();
            TextLayout headerLayout = new TextLayout(null, "600px").colorBaseDark().sizeGiga().paddingTop(0).paddingBottom(25).alignCenter();
            centerRight.add(new LabelComponent("Instant tournaments"), headerLayout);
            centerRight.add(getCreateTournament(), new TextLayout(Horizontal.CENTER));

            centerRight.add(new LabelComponent("No account required"), new TextLayout().sizeSmall().colorGrey().paddingTop(6).alignCenter().paddingBottom(45));

            centerPanel.add(centerRight, new TextLayout().paddingLeft(0));



        }
        return centerPanel;
    }


    public LabelComponent getCreateTournament() {
        if (createTournament == null) {
            createTournament = new LabelComponent("Create a tournament");
            createTournament.setStyleName("colorbutton3");

            createTournament.addMouseOver(MouseOver.POINTER);
            createTournament.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                  Application.popUp(CreateTournamentPageController.getInstance());
                }
            });
        }
        return createTournament;
    }


    @Override
    public GuiComponent getTopPanel() {
        return null;
    }
}
