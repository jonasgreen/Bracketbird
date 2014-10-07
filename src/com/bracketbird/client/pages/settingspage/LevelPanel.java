package com.bracketbird.client.pages.settingspage;

import com.bracketbird.client.Flex;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LevelPanel extends FlowPanel {


    private List<LevelComponent> levelComponents = new ArrayList<LevelComponent>();

    private Button button;


    public LevelPanel() {
        setStyleName(Flex.FLEX);
        addStyleName(Flex.ALIGN_ITEMS_CENTER);

        add(new BeginLevelComponent());

        add(new LevelComponent("Group"));
        add(new LevelComponent("Knockout"));

        add(new EndLevelComponent());
        //add(new AllEmptyLevelComponent());
        //add(new EndLevelComponent());
        add(getButton());

    }


    public Button getButton() {
        if (button == null) {
            button = new Button("Add Stage/Level");
            button.setStyleName("primaryButton");

            button.getElement().getStyle().setMarginLeft(40, Style.Unit.PX);

        }
        return button;
    }

    private boolean isEmpty(){
        return levelComponents.size() == 1 && levelComponents.get(0) instanceof AllEmptyLevelComponent;
    }









}
