package com.bracketbird.client.gui.main;

import com.bracketbird.client.LogoDiv;
import com.bracketbird.client.UserManager;
import com.bracketbird.client.event.EventManager;
import com.bracketbird.client.event.UserStateChangedEvent;
import com.bracketbird.client.event.UserStateChangedHandler;
import com.bracketbird.client.model.User;
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
    private LogoMenues logoMenues;

    private LogoPanel() {
        EventManager.eventBus.addHandler(UserStateChangedEvent.TYPE,
                new UserStateChangedHandler() {
                    public void onChange(UserStateChangedEvent event) {
                        userStateChanged();
                    }
                });
    }


    private void userStateChanged() {
        UserManager um = UserManager.getInstance();
        if (um.isInTransistionLoggedOut()) {
            signOff();
        }
        else if (um.isInTransitionLoggedIn()) {
            loggedOn();
        }
        else {
            inClub();
        }
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



    public LogoMenues getLogoMenues() {
        if (logoMenues == null) {
            logoMenues = new LogoMenues();
        }
        return logoMenues;
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


    private void loggedOn() {

        LogoMenues page = getLogoMenues();
        page.getSignIn().setVisible(false);
        page.getSignUp().setVisible(false);
        page.getExitClub().setVisible(false);
        page.getLogOff().setVisible(true);

        page.getClubName().getLabel().setText("");
        User user = UserManager.getInstance().getUser();
        page.getUserName().getLabel().setText(user.getFirstName() + " " + user.getLastName());
    }

    private void signOff() {
        LogoMenues page = getLogoMenues();

        page.getSignIn().setVisible(true);
        page.getSignUp().setVisible(true);
        page.getLogOff().setVisible(false);
        page.getExitClub().setVisible(false);

        page.getUserName().getLabel().setText("");
        page.getClubName().getLabel().setText("");
    }

    private void inClub() {
       /* loggedOn();
        Club cl = UserManager.getInstance().getClub();
        LogoMenues page = getLogoMenues();
        page.getExitClub().setVisible(true);
        page.getClubName().getLabel().setText(cl.getName() + " - ");
*/
    }

}
