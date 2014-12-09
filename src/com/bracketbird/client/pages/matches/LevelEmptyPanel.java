package com.bracketbird.client.pages.matches;


import com.bracketbird.client.Css;
import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.event.ModelEvent;
import com.bracketbird.client.gui.rtc.event.ModelEventHandler;
import com.bracketbird.client.model.tournament.Stage;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 *
 */
public class LevelEmptyPanel extends FlowPanel {

    private Stage level;
    private Button button;

    public LevelEmptyPanel(Stage tl) {
        super();
        this.level = tl;
        setStyleName("levelEmptyPanel");


        RTC.getInstance().getTournament().stagesEventHandlers.addHandler(new ModelEventHandler<Stage>() {
            @Override
            public void handleEvent(ModelEvent<Stage> event) {
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
        if(RTC.getInstance().getTournament().getStages().size() > 1){
            return "Layout matches - " + level.getName() + " stage";
        }
        return "Layout matches";
    }

}
