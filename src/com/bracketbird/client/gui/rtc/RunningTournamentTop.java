package com.bracketbird.client.gui.rtc;

import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class RunningTournamentTop extends FlowComponent implements TournamentListener<TournamentNameChangedEvent> {

    private static RunningTournamentTop instance;
    private LabelComponent tournamentName;
    private VerticalComponent menuLinks;
    private LockPanel lockPanel;
    private LabelComponent feedback;
    private HorizontalComponent content;


    private RunningTournamentTop() {
        super();
        RTC.getInstance().getTournament().addNameListener(this);
        init();
    }

    public static RunningTournamentTop getInstance() {
        if (instance == null) {
            instance = new RunningTournamentTop();

        }
        return instance;
    }


    private void init() {

        if (!RTC.getInstance().getTournament().isViewOnly()) {
            HorizontalComponent content = getContent();
            content.setStyleName("topPanel");

            VerticalComponent vc = new VerticalComponent();
            content.add(vc, new TextLayout("100%", "100%", Horizontal.LEFT));

            HorizontalComponent images = new HorizontalComponent();
            images.add(getTournamentName(), new TextLayout(Horizontal.LEFT, Vertical.MIDDLE).sizeTitle().colorBaseDark().bold().paddingLeft(10).paddingBottom(10));
            vc.add(images, new TextLayout(Horizontal.LEFT, Vertical.MIDDLE).paddingLeft(10).paddingRight(20).paddingTop(14));


            images.add(getLockPanel(), new TextLayout("38px", "32px", Vertical.TOP).margin(0, 0, 0, 20));

            HorizontalComponent hc = new HorizontalComponent();
            hc.add(getMenuLinks(), new TextLayout("100%", null));
            hc.add(new RuningTournamentMenuPanel().getPanel(), new TextLayout(0, 0, -1, 0, Vertical.BOTTOM));


            content.add(hc, new TextLayout(Horizontal.RIGHT, Vertical.BOTTOM));
            add(content);
            //StyleIt.add(this, new TextLayout().backgroundBase());
        }
        else{
            add(getTournamentName(), new TextLayout(null, "100%").sizeTitle().colorBaseDark().bold().paddingTop(15).paddingLeft(20));
        }
    }


    public HorizontalComponent getContent() {
        if (content == null) {
            content = new HorizontalComponent();
        }
        return content;
    }

    public LockPanel getLockPanel() {
        if (lockPanel == null) {
            lockPanel = new LockPanel();
        }
        return lockPanel;
    }


    public VerticalComponent getMenuLinks() {
        if (menuLinks == null) {
            menuLinks = new VerticalComponent();

            //menuLinks.add(getEjectIn(), new TextLayout(22,20,0,0,"20px", "20px", Horizontal.RIGHT, Vertical.TOP));

            FlowComponent vc = new FlowComponent();

            //vc.add(getHelp(), new TextLayout().sizeSmall().colorBaseDark().underline().padding(20,14, 4, 0).alignRight());
            vc.add(getFeedback(), new TextLayout().sizeSmall().colorBaseDark().underline().padding(25, 20, 10, 0).alignRight());
            menuLinks.add(vc, new TextLayout().sizeSmall().colorBlack().underline().alignRight());


        }
        return menuLinks;
    }

    public LabelComponent getFeedback() {
        if (feedback == null) {
            feedback = new LabelComponent("Feedback");
            feedback.setTitle("Opens a new window");
            feedback.addMouseOver(MouseOver.POINTER);
            feedback.getLabel().addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    openUrl("http://www.facebook.com/Bracketbird", "Bracketbird");
                }
            });
        }
        return feedback;
    }

    public LabelComponent getTournamentName() {
        if (tournamentName == null) {
            tournamentName = new LabelComponent("");
        }
        return tournamentName;
    }

    public void onChange(TournamentNameChangedEvent event) {
        getTournamentName().setText(RTC.getInstance().getTournament().getName());
        Window.setTitle(RTC.getInstance().getTournament().getName());
    }


    public static native void openUrl(String url, String name)/*-{
        $wnd.open(url, name);
    }-*/;



}