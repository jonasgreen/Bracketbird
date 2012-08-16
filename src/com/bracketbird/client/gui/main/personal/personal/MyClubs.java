package com.bracketbird.client.gui.main.personal.personal;


import com.bracketbird.client.*;
import com.bracketbird.client.model.*;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class MyClubs extends VerticalComponent {

    private SimplePanelComponent clubsHolder;
    private LabelComponent createClub;

    public MyClubs() {
        super();
        init();
    }


    public void init() {
        HorizontalComponent hc = new HorizontalComponent();
        hc.add(getClubsHolder(), new TextLayout(Horizontal.LEFT));
        hc.add(getCreateClub(), new TextLayout(0,0,10,60,Horizontal.RIGHT, Vertical.BOTTOM));
        add(hc);
    }


    public SimplePanelComponent getClubsHolder() {
        if (clubsHolder == null) {
            clubsHolder = new SimplePanelComponent();
        }
        return clubsHolder;
    }

    public void updateClubs(Collection<Club> clubs) {
        if (clubs.isEmpty()) {
            return;
        }
        TextLayout tl = new TextLayout(0, 0, 10, 0);
        VerticalComponent vc = new VerticalComponent();
        vc.add(new LabelComponent("Enter one of your clubs:"), new TextLayout(0, 0, 15, 0).sizeH3().colorBaseDark());
        for (Club club : clubs) {
            vc.add(createClubLabel(club), tl);
        }
        getClubsHolder().add(vc);
    }

    public LabelComponent getCreateClub() {
        if (createClub == null) {
            createClub = new LabelComponent("Create a new club");
            createClub.setStyleName("colorbutton");
            createClub.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    PopupManager.show(CreateClubPageController.getInstance());
                }
            });
        }
        return createClub;
    }



    
    public LabelComponent createClubLabel(final Club club) {
        LabelComponent lc = new LabelComponent(club.getName());
        lc.setStyleName("colorbutton2");
        lc.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                UserManager.getInstance().loggedIntoClub(club);
            }
        });

        return lc;
    }

}