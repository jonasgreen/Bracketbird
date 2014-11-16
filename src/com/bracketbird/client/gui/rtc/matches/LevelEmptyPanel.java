package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.Css;
import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.TournamentLevel;
import com.bracketbird.client.model.tournament.TournamentLevelEvent;
import com.bracketbird.client.model.tournament.TournamentListener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 *
 */
public class LevelEmptyPanel extends FlowPanel {

    private TournamentLevel level;
    private Button button;

    public LevelEmptyPanel(TournamentLevel tl) {
        super();
        this.level = tl;
        setStyleName("levelEmptyPanel");

        RTC.getInstance().getTournament().addLevelListener(new TournamentListener<TournamentLevelEvent>() {
            @Override
            public void onChange(TournamentLevelEvent event) {
                System.out.println("Whaaaat");
                getButton().setText(getButtonName());
            }
        });

        add(getButton());

    }

    public Button getButton() {
        if (button == null) {
            button = Css.style(new Button(getButtonName()), "primaryButton");
            button.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    RTC.getInstance().layoutMatches(level.getId());
                }
            });
        }
        return button;
    }

    private String getButtonName() {
        if(RTC.getInstance().getTournament().getLevels().size() > 1){
            System.out.println("SIZE: "+RTC.getInstance().getTournament().getLevels().size());
            return "Layout matches - " + level.getName() + " stage";
        }
        return "Layout matches";
    }

}
