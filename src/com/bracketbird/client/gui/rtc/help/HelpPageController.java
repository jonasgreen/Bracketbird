package com.bracketbird.client.gui.rtc.help;

import com.bracketbird.client.gui.rtc.RunningTournamentPageController;
import com.bracketbird.client.model.*;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.util.*;

/**
 *
 */
public class HelpPageController extends PageController<HelpPage> {

    private static HelpPageController instance;
    public static String HISTORY_NAME = "HelpPage";
    private ImageComponent imgOut = new ImageComponent("help1.png");
    private ImageComponent imgOver = new ImageComponent("helpYellow1.png");


    private HelpPageController() {
        super(RunningTournamentPageController.getInstance(), HISTORY_NAME);
    }

    public static HelpPageController getInstance() {
        if (instance == null) {
            instance = new HelpPageController();
        }
        return instance;
    }

    public void afterLoad() {
    }

    public HelpPage newInstance() {
        return new HelpPage();
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("help.png", "Help");
    }

    public UserStateConstant getLegalState() {
        return UserStateConstant.LOGGED_OUT;
    }


    public SimplePanelComponent getHelpImage() {
        final SimplePanelComponent help = new SimplePanelComponent();
        help.add(imgOut);
        help.addMouseOverHandler(new MouseOverHandler() {
            public void onMouseOver(MouseOverEvent event) {
                help.add(imgOver);

            }
        });
        help.addMouseOverHandler(MouseOver.POINTER);


        help.addMouseOutHandler(new MouseOutHandler() {
            public void onMouseOut(MouseOutEvent event) {
                help.add(imgOut);
            }
        });

        help.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                PopupManager.show(HelpPageController.getInstance());
            }
        });

        help.setTitle("Help");
        return help;
    }


}