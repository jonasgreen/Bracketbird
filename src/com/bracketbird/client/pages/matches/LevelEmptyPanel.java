package com.bracketbird.client.pages.matches;


import com.bracketbird.client.Css;
import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.event.ModelEvent;
import com.bracketbird.client.gui.rtc.event.ModelEventHandler;
import com.bracketbird.client.model.tournament.TournamentStage;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 *
 */
public class LevelEmptyPanel extends FlowPanel {

    private TournamentStage level;
    private Button button;

    public LevelEmptyPanel(TournamentStage tl) {
        super();
        this.level = tl;
        setStyleName("levelEmptyPanel");


        RTC.getInstance().getTournament().levelsEventHandlers.addHandler(new ModelEventHandler<TournamentStage>() {
            @Override
            public void handleEvent(ModelEvent<TournamentStage> event) {
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
