package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.*;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class LevelEmptyPanel extends VerticalComponent {

    private LabelComponent layoutMatchesButton;
    private TournamentLevel level;

    public LevelEmptyPanel(TournamentLevel tl) {
        super();
        this.level = tl;
        init();
    }

    private void init() {
        add(getLayoutMatchesButton(), new TextLayout(Horizontal.LEFT));
    }

    public LabelComponent getLayoutMatchesButton() {
        if (layoutMatchesButton == null) {
            layoutMatchesButton = new LabelComponent("Layout matches");
            layoutMatchesButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    RTC.getInstance().layoutMatches(level.getId());
                }
            });
            layoutMatchesButton.setStyleName("colorbutton4");

        }
        return layoutMatchesButton;
    }

}
