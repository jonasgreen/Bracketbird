package com.bracketbird.client.pages.settingspage;

import com.bracketbird.client.Flex;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class BeginLevelComponent extends FlowPanel{


    public BeginLevelComponent() {
        setStyleName(Flex.FLEX_CENTER);

        FlowPanel innerPanel = new FlowPanel();
        innerPanel.setStyleName(Flex.FLEX_CENTER);
        innerPanel.addStyleName("beginAndEndComponent_innerPanel");

        Label l = new Label("Begin");
        l.setStyleName("levelComponent_name");
        innerPanel.add(l);
        add(innerPanel);

        FlowPanel beginArrow = new FlowPanel();
        beginArrow.setStyleName("levelComponent_beginArrow");
        add(beginArrow);

        FlowPanel separationLine = new FlowPanel();
        separationLine.setStyleName("levelComponent_sep");
        separationLine.add(new Label(""));
        add(separationLine);

  }


}
