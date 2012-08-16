package com.bracketbird.client.gui.rtc.help;


import com.bracketbird.client.gui.rtc.RTCLayoutFac2;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class HelpPage extends Page<HelpPageController> {

    private VerticalComponent content;

    private LabelComponent header;
    private VerticalComponent textTeams;

    public HelpPage() {
        super();
        content = new VerticalComponent();
        initWidget(content);
    }

    public void init() {
        StyleIt.add(content, RTCLayoutFac2.CONTENT);
        content.add(getHeader(), RTCLayoutFac2.h1());



        content.add(new LabelComponent("Setting up and running a tournament"), RTCLayoutFac2.h2().margin(20, 0,10,0));

        TextLayout tl = new TextLayout(0,20,0,0, "120px", "140px").backgroundBase();

        HorizontalComponent hc = new HorizontalComponent();
        hc.add(createAStep("1", "Enter teams and players", "teamsSmall.png"), tl);
        hc.add(createAStep("2", "Set tournament settings", "settingsSmall.png"), tl);
        hc.add(createAStep("3", "Start tournament and enter matches", "resultsSmall.png"), tl);
        hc.add(createAStep("4", "View Ranking", "rankingSmall.png"), tl.margin(0,0,0,0));




        content.add(hc);

        ButtonComponent close = new ButtonComponent("close");
        close.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                PopupManager.hide();
            }
        });
        content.add(close, LayoutFac.button().margin(20,0,0,0).horizontalAlignRight());
    }

    public VerticalComponent getTextTeams() {
        if (textTeams == null) {
            textTeams = new VerticalComponent();
            textTeams.add(new LabelComponent("Enter teams and players"), RTCLayoutFac2.h2().margin(0,0,10,0));
            textTeams.add(new LabelComponent("You enter all the teams and players participating in the tournament."));
        }
        return textTeams;
    }

    private VerticalComponent createAStep(String number, String text, String imageUrl){
        VerticalComponent vc = new VerticalComponent();
        HorizontalComponent hc = new HorizontalComponent();
        hc.add(new LabelComponent(number), new TextLayout(Horizontal.LEFT).sizeH1().bold().colorWhite().padding(10));
        hc.add(new ImageComponent(imageUrl), new TextLayout(Horizontal.RIGHT).padding(10));
        vc.add(hc, new TextLayout(null, "100%", Vertical.TOP));
        vc.add(new LabelComponent(text), new TextLayout(Vertical.BOTTOM).sizeNormal().padding(10));
        return vc;
    }



    public LabelComponent getHeader() {
        if (header == null) {
            header = new LabelComponent("Help");
        }
        return header;
    }

    protected void setSubPageHolder(Page subPage) {
        //ignore
    }
}