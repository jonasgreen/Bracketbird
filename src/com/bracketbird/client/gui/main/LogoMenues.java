package com.bracketbird.client.gui.main;

import com.bracketbird.client.*;
import com.bracketbird.client.gui.main.SignInPageController;
import com.bracketbird.client.gui.main.SignUpPageController;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class LogoMenues extends GuiComponent {

    private HorizontalComponent content;
    private HyperlinkLabelComponent signUp;
    private HyperlinkLabelComponent help;
    private HyperlinkLabelComponent signIn;
    private HyperlinkLabelComponent logOff;
    private HyperlinkLabelComponent exitClub;
    private LabelComponent userMail;
    private LabelComponent clubName;



    public LogoMenues() {
        super();
        this.content = new HorizontalComponent();
        initWidget(content);
        init();
    }

    public void init() {
        VerticalComponent vc = new VerticalComponent();

        content.add(vc);

        content.setCellHorizontalAlignment(vc, HasHorizontalAlignment.ALIGN_LEFT);

        Layout17 l = new TextLayout(0, 0, 0, 10).sizeSmall().colorBase();

        Layout17 lMail = new TextLayout(0, 0, 0, 10, Vertical.BOTTOM).sizeNormal().bold().alignRight().noWrap().colorBaseDark();


        //vc.add(getUserName(), lMail);
        //getUserName().setVisible(false);

        HorizontalComponent hc = new HorizontalComponent();

        hc.add(getSignUp(), l);
        hc.add(getSignIn(), l);
        hc.add(getExitClub(), l);
        hc.add(getLogOff(), l);
        getLogOff().setVisible(false);
        getExitClub().setVisible(false);
        vc.add(hc, new TextLayout(0,0,10,0));
        vc.setCellHorizontalAlignment(hc, HasHorizontalAlignment.ALIGN_RIGHT);

        HorizontalComponent hcUser = new HorizontalComponent();
        hcUser.add(getClubName(), lMail);
        hcUser.add(getUserName(), lMail);
        vc.add(hcUser, new TextLayout(Horizontal.RIGHT, Vertical.BOTTOM));

        content.add(vc, new Layout17(0, 0, 0, 0, null, Vertical.BOTTOM));
        content.setCellHorizontalAlignment(vc, HasHorizontalAlignment.ALIGN_RIGHT);
    }

    public HorizontalComponent getContent() {
        return content;
    }



    public HyperlinkLabelComponent getSignIn() {
        if (signIn == null) {
            signIn = new HyperlinkLabelComponent("Login");
            signIn.getLabel().setWordWrap(false);

            signIn.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    PageFlow.popUp(SignInPageController.getInstance());
                }
            });

        }
        return signIn;
    }

    public HyperlinkLabelComponent getSignUp() {
        if (signUp == null) {
            signUp = new HyperlinkLabelComponent("Create free account");
            signUp.getLabel().setWordWrap(false);
            signUp.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    PageFlow.popUp(SignUpPageController.getInstance());
                }
            });

        }
        return signUp;
    }



    public HyperlinkLabelComponent getLogOff() {
        if (logOff == null) {
            logOff = new HyperlinkLabelComponent("Log out");
            logOff.getLabel().setWordWrap(false);

            logOff.addClickHandler(new ClickHandler() {

                public void onClick(ClickEvent event) {
                    UserManager.getInstance().loggedOut();
                }
            });

        }
        return logOff;
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


    public LabelComponent getUserName() {
        if (userMail == null) {
            userMail = new LabelComponent("");
        }
        return userMail;
    }

    public LabelComponent getClubName() {
        if (clubName == null) {
            clubName = new LabelComponent("");
        }
        return clubName;
    }


}
