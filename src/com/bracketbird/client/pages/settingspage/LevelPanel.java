package com.bracketbird.client.pages.settingspage;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class LevelPanel extends FlowPanel {

    public LevelPanel() {
        setStyleName("levelPanel");

        LevelComponent begin = new LevelComponent("Begin");
        begin.addStyleName("levelComponent_beginAndEnd");
        add(begin);

        FlowPanel beginArrow = new FlowPanel();
        beginArrow.setStyleName("levelComponent_beginArrow");
        add(beginArrow);

        addSep();

        addLeftEar();
        LevelComponent group = new LevelComponent("Group");
        group.addStyleName("levelComponentHover");

        add(group);
        addRightEar();

        addSep();

        addLeftEar();
        LevelComponent knockout = new LevelComponent("Knockout");
        knockout.addStyleName("levelComponentHover");

        add(knockout);
        addRightEar();
        addSep();

        addLeftEar();
        LevelComponent end = new LevelComponent("End");
        end.addStyleName("levelComponent_beginAndEnd");
        add(end);



    }

    private void addRightEar() {
        FlowPanel rightEar = new FlowPanel();
        rightEar.setStyleName("levelComponent_rightEar");
        add(rightEar);
    }

    private void addLeftEar() {
        FlowPanel leftEar;
        leftEar = new FlowPanel();
        leftEar.setStyleName("levelComponent_leftEar");
        add(leftEar);
    }

    private void addSep() {
        FlowPanel l = new FlowPanel();
        l.setStyleName("levelComponent_sep");
        l.add(new Label(""));
        add(l);
    }
}
