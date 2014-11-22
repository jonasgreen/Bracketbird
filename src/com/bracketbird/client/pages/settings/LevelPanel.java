package com.bracketbird.client.pages.settings;

import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.TournamentStage;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LevelPanel extends FlowPanel {


    private List<LevelComponent> levelComponents = new ArrayList<LevelComponent>();
    private AddStageDropDown popup;

    private Button button;
    private FlowPanel levelHolder;


    public LevelPanel() {
        addStyleName("flex_alignItems_center");
        add(new BeginLevelComponent());

        //Holds all added levels
        add(getLevelHolder());

        add(new EndLevelComponent());
        add(getButton());

    }

    public FlowPanel getLevelHolder() {
        if (levelHolder == null) {
            levelHolder = new FlowPanel();
            levelHolder.addStyleName("flex_alignItems_center");
            levelHolder.add(new AllEmptyLevelComponent());
        }
        return levelHolder;
    }

    public Button getButton() {
        if (button == null) {
            button = new Button("Add stage/level");
            button.setStyleName("primaryButton");
            button.getElement().getStyle().setMarginLeft(40, Style.Unit.PX);
            button.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    if (popup == null) {
                        popup = new AddStageDropDown();
                        popup.setWidth(button.getOffsetWidth()-4 + "px");
                        popup.show();
                        popup.getElement().getStyle().setZIndex(10);
                        popup.getElement().getStyle().setTop(button.getAbsoluteTop() + button.getOffsetHeight() -2, Style.Unit.PX);
                        popup.getElement().getStyle().setLeft(button.getAbsoluteLeft()+1, Style.Unit.PX);
                        popup.addCloseHandler(new CloseHandler<PopupPanel>() {
                            @Override
                            public void onClose(CloseEvent<PopupPanel> event) {
                                popup = null;
                            }
                        });
                        popup.addAutoHidePartner(button.getElement());
                    } else {
                        popup.hide();
                    }
                }
            });
        }
        return button;
    }


    public void addLevel(final TournamentStage level) {
        if (levelComponents.isEmpty()) {
            getLevelHolder().clear();
        }

        final LevelComponent lc = new LevelComponent(level);
        getLevelHolder().add(lc);

        levelComponents.add(lc);
        updateButtonText();
        com.google.gwt.core.client.Scheduler.get().scheduleDeferred(new com.google.gwt.core.client.Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                lc.getInnerPanel().setWidth("80px");
                lc.getInnerPanel().setHeight("50px");
                Timer t = new Timer() {
                    @Override
                    public void run() {
                        lc.getNameLabel().setText(level.getType().getLevelName());
                    }
                };
                t.schedule(300);
            }
        });

    }

    private void updateButtonText() {

        getButton().setText(RTC.getInstance().getTournament().getLevels().isEmpty() ? "Add stage/level" : "Add another stage/level");
    }

    public void removeLevel(TournamentStage level) {
        LevelComponent found = null;
        for (LevelComponent lc : levelComponents) {
            if (lc.getLevel().equals(level)) {
                found = lc;
                break;
            }
        }

        if (found != null) {
            final LevelComponent finalLevel = found;
            if(levelComponents.size() > 1){
                finalLevel.getInnerPanel().setWidth("0px");
                finalLevel.getSeparationLine().setWidth("0px");
            }
            else{
                finalLevel.getInnerPanel().setWidth("40px");//same size as '? component'
            }

            finalLevel.getInnerPanel().setHeight("0px");
            finalLevel.getNameLabel().setText("");

            Timer t = new Timer() {
                @Override
                public void run() {
                    finalLevel.removeFromParent();
                    levelComponents.remove(finalLevel);
                    
                    if (levelComponents.isEmpty()) {
                        getLevelHolder().add(new AllEmptyLevelComponent());
                    }
                }
            };
            t.schedule(300);


        }


        updateButtonText();
    }


}
