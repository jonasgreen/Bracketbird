package com.bracketbird.client.gui.main;


import com.bracketbird.client.gui.main.personal.personal.CreateTournamentPageController;
import com.bracketbird.clientcore.appcontrol.Page;
import com.bracketbird.clientcore.appcontrol.PageFlow;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.Horizontal;
import com.bracketbird.clientcore.style.TextLayout;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;


/**
 *
 */
public class FrontPage extends Page<FrontPageController> implements TopPanelHolder {

    private FlowComponent content;
    private HorizontalComponent topLoggedOut;

    public static double LOGO_HEIGHT = 70;
    public static double PAGE_WIDTH = 900;
    private LabelComponent createTournament;
    private HorizontalComponent centerPanel;
    private FlowComponent slogans;

    public FrontPage() {
        super();
        content = new FlowComponent();
        CustomScrollPanelComponent customerScrollPanelComponent = new CustomScrollPanelComponent(content);
        customerScrollPanelComponent.getScrollPanel().setWidth("100%");
        initWidget(customerScrollPanelComponent);
    }

    public void init() {
        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                content.setWidth(event.getWidth()+ "px");
            }
        });

        FlowComponent fl = new FlowComponent();
        fl.getElement().getStyle().setProperty("margin", "0 auto");
        fl.setWidth("900px");
        content.add(fl);
        fl.add(getTopPanel(), new TextLayout(null, "900px"));


        FlowComponent div = new FlowComponent();
        div.add(getCenterPanel(), new TextLayout(Horizontal.CENTER).paddingTop(140).paddingBottom(80));
        content.add(div, new TextLayout(null, "100%").margin(0,0,30,0));
       // div.getElement().setClassName("shadowFrontPage");


        div = new FlowComponent();
        div.setStyleName("sloganPage");
        content.add(div);
        addSlogan(div);


    }

    private void addSlogan(FlowComponent div) {
        FlowComponent fl2 = new FlowComponent();
        SloganPage sloganPage = new SloganPage();
        sloganPage.getElement().getStyle().setProperty("margin", "0 auto");
        sloganPage.setWidth("900px");
        fl2.add(sloganPage);
        div.add(fl2);
    }


    protected void setSubPageHolder(Page subPage) {
        //ignore
    }

    public GuiComponent getTopPanel() {
        if (topLoggedOut == null) {
            topLoggedOut = new HorizontalComponent();
            topLoggedOut.add(LogoPanel.getInstance().getPanel(), new TextLayout(LOGO_HEIGHT + "px", PAGE_WIDTH + "px", Horizontal.CENTER));


        }
        return topLoggedOut;
    }

    public HorizontalComponent getCenterPanel() {
        if (centerPanel == null) {

            centerPanel = new HorizontalComponent();
            //centerPanel.add(new ImageComponent("front2.png"), new TextLayout(null, "252px"));
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
                    PageFlow.popUp(CreateTournamentPageController.getInstance());
                }
            });
        }
        return createTournament;
    }


    public FlowComponent getContent() {
        return content;
    }
}
