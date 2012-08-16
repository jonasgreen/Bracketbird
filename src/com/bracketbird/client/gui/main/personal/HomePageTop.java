package com.bracketbird.client.gui.main.personal;

import com.bracketbird.client.*;
import com.bracketbird.client.gui.main.personal.help.HelpPageController;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class HomePageTop extends HorizontalComponent {

    private LabelComponent tournamentName;
    private HyperlinkLabelComponent logout;
    private VerticalComponent menuLinks;
    private HyperlinkLabelComponent feedback;
    private HyperlinkLabelComponent help;
    private HomePageMenuPanel homePageMenuPanel;


    public HomePageTop() {
        super();
        init();
    }



    private void init() {
        VerticalComponent vc = new VerticalComponent();
        vc.add(new ImageComponent("bracketbird.png"), new TextLayout("40px", "175px", Horizontal.LEFT, Vertical.TOP).paddingLeft(18).paddingBottom(20).paddingTop(14).paddingRight(20));
        vc.add(getTournamentName(), new TextLayout(Horizontal.LEFT, Vertical.BOTTOM).sizeH3().colorWhite().italic().paddingLeft(20).paddingBottom(20));

        add(vc, new TextLayout("100%", "100%", Horizontal.LEFT));

        HorizontalComponent hc = new HorizontalComponent();
        hc.add(getMenuLinks(), new TextLayout(10, 10, 10, 10, Vertical.BOTTOM));
        hc.add(getHomePageMenuPanel().getPanel(), new TextLayout(10, 0, 0, 0, Vertical.BOTTOM));

        add(hc, new TextLayout(Horizontal.RIGHT, Vertical.BOTTOM));
        setBackgroundColor(Color.backgroundBase());
    }

    public HomePageMenuPanel getHomePageMenuPanel() {
        if (homePageMenuPanel == null) {
            homePageMenuPanel = new HomePageMenuPanel();
        }
        return homePageMenuPanel;
    }

    public HyperlinkLabelComponent getLogout() {
        if (logout == null) {
            logout = new HyperlinkLabelComponent("Log out");
            logout.getLabel().setWordWrap(false);
            logout.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    UserManager.getInstance().loggedOut();
                }
            });
        }
        return logout;
    }

      public HyperlinkLabelComponent getHelp() {
        if (help == null) {
            help = new HyperlinkLabelComponent("Help");
            help.getLabel().setWordWrap(false);
            help.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    PageFlow.show(HelpPageController.getInstance());
                    getHomePageMenuPanel().deselectAll();
                }
            });
        }
        return help;
    }


    public HyperlinkLabelComponent getFeedback() {
        if (feedback == null) {
            feedback = new HyperlinkLabelComponent("About");
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
            TextLayout linkMenu = new TextLayout(2,0,2,0).sizeSmall().colorWhite().underline().paddingRight(10).alignRight();

            menuLinks.add(getHelp(), linkMenu);
            menuLinks.add(getLogout(), linkMenu);
            menuLinks.add(getFeedback(), linkMenu);

        }
        return menuLinks;
    }

    public LabelComponent getTournamentName() {
        if (tournamentName == null) {
            tournamentName = new LabelComponent("");
        }
        return tournamentName;
    }

}