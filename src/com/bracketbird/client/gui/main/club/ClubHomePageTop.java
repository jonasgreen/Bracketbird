package com.bracketbird.client.gui.main.club;


import com.bracketbird.client.*;
import com.bracketbird.client.gui.main.personal.help.HelpPageController;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class ClubHomePageTop extends HorizontalComponent {

    private HyperlinkLabelComponent logOut;
    private VerticalComponent menuLinks;
    private HyperlinkLabelComponent feedback;
    private HyperlinkLabelComponent exitClub;
    private HyperlinkLabelComponent help;
    private ClubHomePageMenuPanel clubHomePageMenuPanel;


    public ClubHomePageTop() {
        super();
        init();
    }


    private void init() {
        VerticalComponent vc = new VerticalComponent();
        vc.add(new ImageComponent("bracketbird.png"), new TextLayout("40px", "175px", Horizontal.LEFT, Vertical.TOP).paddingLeft(18).paddingBottom(20).paddingTop(14).paddingRight(20));


        HorizontalComponent hcOne = new HorizontalComponent();
        hcOne.add(new LabelComponent(UserManager.getInstance().getClub().getName()),new TextLayout(Horizontal.LEFT, Vertical.BOTTOM).sizeH3().colorWhite().italic().paddingLeft(20));
        hcOne.add(getExitClub(), new TextLayout(Horizontal.LEFT, Vertical.BOTTOM).sizeSmall().colorWhite().italic().underline().paddingLeft(20));
        vc.add(hcOne, new TextLayout("100%",null, Horizontal.LEFT, Vertical.BOTTOM).paddingBottom(10));

        add(vc, new TextLayout("100%", "100%", Horizontal.LEFT));

        HorizontalComponent hc = new HorizontalComponent();
        hc.add(getMenuLinks(), new TextLayout(10, 10, 10, 10, Vertical.BOTTOM));
        hc.add(getClubHomePageMenuPanel().getPanel(), new TextLayout(10, 0, 0, 0, Vertical.BOTTOM));

        add(hc, new TextLayout(Horizontal.RIGHT, Vertical.BOTTOM));
        setBackgroundColor(Color.backgroundBase());
    }

    public ClubHomePageMenuPanel getClubHomePageMenuPanel() {
        if (clubHomePageMenuPanel == null) {
            clubHomePageMenuPanel = new ClubHomePageMenuPanel();
        }
        return clubHomePageMenuPanel;
    }


    public HyperlinkLabelComponent getExitClub() {
        if (exitClub == null) {
            exitClub = new HyperlinkLabelComponent("Exit club");
            exitClub.getLabel().setWordWrap(false);
            exitClub.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    UserManager.getInstance().loggedOutOfClub();
                }
            });
        }
        return exitClub;
    }


    public HyperlinkLabelComponent getLogOut() {
        if (logOut == null) {
            logOut = new HyperlinkLabelComponent("Log out");
            logOut.getLabel().setWordWrap(false);
            logOut.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    UserManager.getInstance().loggedOut();
                }
            });
        }
        return logOut;
    }
    public HyperlinkLabelComponent getHelp() {
        if (help == null) {
            help = new HyperlinkLabelComponent("Help");
            help.getLabel().setWordWrap(false);
            help.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    PageFlow.show(HelpPageController.getInstance());
                    getClubHomePageMenuPanel().deselectAll();

                }
            });
        }
        return help;
    }



    public HyperlinkLabelComponent getFeedback() {
        if (feedback == null) {
            feedback = new HyperlinkLabelComponent("Feedback");
            feedback.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    ApplicationController.showFeedbackDialog();
                }
            });
        }
        return feedback;
    }


    public VerticalComponent getMenuLinks() {
        if (menuLinks == null) {
            menuLinks = new VerticalComponent();
            TextLayout linkMenu = new TextLayout(2, 0, 2, 0).sizeSmall().colorWhite().underline().paddingRight(10).alignRight();
            menuLinks.add(getHelp(), linkMenu);
            //menuLinks.add(getExitClub(), linkMenu);
            menuLinks.add(getLogOut(), linkMenu);
            menuLinks.add(getFeedback(), linkMenu);

        }
        return menuLinks;
    }
}