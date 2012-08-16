package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.gui.rtc.matches.MatchComponent;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.util.*;

import java.util.*;

/**
 *
 */
public class RoundPanel extends VerticalComponent {

    public static TableHeader hBeforeOne = new TableHeader("#", "40px", 0);
    public static TableHeader hOne = new TableHeader("Team personal", "175px", 0);
    public static TableHeader hTwo = new TableHeader("-", "10px", 30);
    public static TableHeader hThree = new TableHeader("Team away", "175px", 30);
    public static TableHeader hFour = new TableHeader("Result", "250px", 30);

    public static TableHeader hFive = new TableHeader("Field", "100%", 30);

    private List<MatchComponent> matches = new ArrayList<MatchComponent>();
    private VerticalFocusManager focusManager = new VerticalFocusManager();
    private HorizontalComponent headerPanel;
    private Round round;
    private boolean firstRound;

    public RoundPanel(VerticalFocusManager vfm, Round round, boolean first) {
        super();
        this.focusManager = vfm;
        this.round = round;
        this.firstRound = first;
        init();
    }

    public void init() {

        TextLayout l = new TextLayout(null, "100%");
        if (firstRound) {
            add(getHeaderPanel(), new TextLayout(0, 0, 4, 0));
        }
        else{
            add(new LabelComponent(round.getName()), new TextLayout(0, 0, 0, 0).sizeSmall().colorBaseDark().italic());
        }
        for (Match m : round.getMatches()) {
            MatchComponent match = createMatch(m);
            matches.add(match);
            focusManager.addChild(match);
            add(match, l);
        }
    }


    public HorizontalComponent getHeaderPanel() {
        if (headerPanel == null) {
            headerPanel = TableHeader.createHeaderPanel(TableHeader.HEADER_LAYOUT,hBeforeOne, hOne, hTwo, hThree, hFour, hFive);
        }
        return headerPanel;
    }


    private MatchComponent createMatch(Match m) {
        return new MatchComponent(m);
    }


    public void setFocus(boolean focus) {
        if (focus) {
            matches.get(0).setFocus(true);
        }
    }

}
