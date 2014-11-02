package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.TournamentLevel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 *
 */
public class LevelEmptyPanel extends FlowPanel {

    private TournamentLevel level;

    public LevelEmptyPanel(TournamentLevel tl) {
        super();

        this.level = tl;
        Button button = new Button("Layout matches");
        button.setStyleName("primayButton");
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                RTC.getInstance().layoutMatches(level.getId());
            }
        });
    }

}
