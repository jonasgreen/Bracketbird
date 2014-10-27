package com.bracketbird.client.pages.settingspage;

import com.bracketbird.client.Flex;
import com.bracketbird.client.model.tournament.TournamentLevel;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
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
        setStyleName(Flex.FLEX);
        addStyleName(Flex.ALIGN_ITEMS_CENTER);
        add(new BeginLevelComponent());

        //Holds all added levels
        add(getLevelHolder());

        add(new EndLevelComponent());
        add(getButton());

    }

    public FlowPanel getLevelHolder() {
        if (levelHolder == null) {
            levelHolder = new FlowPanel();
            levelHolder.setStyleName(Flex.FLEX);
            levelHolder.addStyleName(Flex.ALIGN_ITEMS_CENTER);
            levelHolder.add(new AllEmptyLevelComponent());
        }
        return levelHolder;
    }

    public Button getButton() {
        if (button == null) {
            button = new Button("Add Stage/Level");
            button.setStyleName("primaryButton");
            button.getElement().getStyle().setMarginLeft(40, Style.Unit.PX);
            button.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    if (popup == null) {
                        popup = new AddStageDropDown();
                        popup.setWidth(button.getOffsetWidth() + "px");
                        popup.show();
                        popup.getElement().getStyle().setTop(button.getAbsoluteTop() + button.getOffsetHeight(), Style.Unit.PX);
                        popup.getElement().getStyle().setLeft(button.getAbsoluteLeft(), Style.Unit.PX);
                        popup.addCloseHandler(new CloseHandler<PopupPanel>() {
                            @Override
                            public void onClose(CloseEvent<PopupPanel> event) {
                                popup = null;
                            }
                        });
                        popup.addAutoHidePartner(button.getElement());
                    }
                    else{
                        popup.hide();
                    }
                }
            });
        }
        return button;
    }


    public void addLevel(TournamentLevel level){
        if(levelComponents.isEmpty()){
            getLevelHolder().clear();
        }

        LevelComponent lc = new LevelComponent(level);

        getLevelHolder().add(lc);
        levelComponents.add(lc);
    }

    public void removeLevel(TournamentLevel level){
        LevelComponent found = null;
        for (LevelComponent lc : levelComponents) {
            if(lc.getLevel().equals(level)){
                found = lc;
                break;
            }
        }

        if(found != null){
            found.removeFromParent();
            levelComponents.remove(found);
        }

        if(levelComponents.isEmpty()){
            getLevelHolder().add(new AllEmptyLevelComponent());
        }
    }


}
