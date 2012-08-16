package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.gui.rtc.RTCLayoutFac2;
import com.bracketbird.client.gui.rtc.settings.SettingsPageController;
import com.bracketbird.client.gui.rtc.teams.TeamsPageController;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class SetupPanel extends SimplePanelComponent {

    public SetupPanel() {
        super();
        init();
    }

    private void init() {
    }



    public void show(boolean hasTeams, boolean hasSettings) {
        VerticalComponent vc = new VerticalComponent();
        vc.add(new LabelComponent("Tournament NOT ready to start. You need to:"), RTCLayoutFac2.h1().bold().margin(0, 0, 30, 0));
        int i = 1;
        if (!hasTeams) {
            vc.add(getTeamsNotAdded(String.valueOf(i++)));
        }
        if (!hasSettings) {
            vc.add(getSettingsNotSetComponent(String.valueOf(i)), new TextLayout(10, 0, 0, 0));
        }
        add(vc);
    }


    public HorizontalComponent getTeamsNotAdded(String number) {
        HorizontalComponent one = new HorizontalComponent();
        LabelComponent l = new LabelComponent(number + ") Register  ");
        one.add(l, RTCLayoutFac2.h2().noWrap());


        HyperlinkLabelComponent link = new HyperlinkLabelComponent("teams and players.", RTCLayoutFac2.h2().underline().paddingLeft(6));
        link.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                PageFlow.show(TeamsPageController.getInstance());
            }
        });
        one.add(link, new TextLayout(Vertical.MIDDLE));
        return one;
    }

    public HorizontalComponent getSettingsNotSetComponent(String number) {
        HorizontalComponent one = new HorizontalComponent();
        LabelComponent l = new LabelComponent(number + ") Enter  ");
        one.add(l, RTCLayoutFac2.h2().noWrap());


        HyperlinkLabelComponent link = new HyperlinkLabelComponent("tournament settings.", RTCLayoutFac2.h2().underline().paddingLeft(6));
        link.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                PageFlow.show(SettingsPageController.getInstance());
            }
        });
        one.add(link, new TextLayout(Vertical.MIDDLE));
        return one;
    }

}
