package com.bracketbird.client.pages.settingspage;

import com.bracketbird.client.Flex;
import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.TournamentLevel;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class LevelComponent extends FlowPanel{

    protected FlowPanel innerPanel;
    private Label nameLabel;

    private FlowPanel separationLine;
    private FlowPanel rightEar;
    private FlowPanel leftEar;

    private TournamentLevel level;
    private FlowPanel deleteIcon;

    public LevelComponent(TournamentLevel level) {
        setStyleName(Flex.FLEX_CENTER);
        this.level = level;
        getNameLabel().setText(level == null ? "?" : level.getType().getLevelName());
        add(getLeftEar());
        add(getInnerPanel());
        add(getRightEar());
        add(getSeparationLine());
        addDomHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                getDeleteIcon().setVisible(true);
            }
        }, MouseOverEvent.getType());

        addDomHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                getDeleteIcon().setVisible(false);
            }
        }, MouseOutEvent.getType());
    }


    public FlowPanel getInnerPanel() {
        if (innerPanel == null) {
            innerPanel = new FlowPanel();
            innerPanel.setStyleName(Flex.FLEX_CENTER);
            innerPanel.addStyleName("levelComponent_innerPanel");
            innerPanel.add(getNameLabel());
            innerPanel.add(getDeleteIcon());

        }
        return innerPanel;
    }

    public Label getNameLabel() {
        if (nameLabel == null) {
            nameLabel = new Label();
            nameLabel.setStyleName("levelComponent_name");
        }
        return nameLabel;
    }

    public FlowPanel getSeparationLine() {
        if (separationLine == null) {
            separationLine = new FlowPanel();
            separationLine.setStyleName("levelComponent_sep");
            separationLine.add(new Label(""));
        }
        return separationLine;
    }

    public FlowPanel getRightEar() {
        if (rightEar == null) {
            rightEar = new FlowPanel();
            rightEar.setStyleName("levelComponent_ear");
            rightEar.addStyleName("levelComponent_rightEar");
        }
        return rightEar;
    }

    public FlowPanel getLeftEar() {
        if (leftEar == null) {
            leftEar = new FlowPanel();
            leftEar.setStyleName("levelComponent_ear");
            leftEar.addStyleName("levelComponent_leftEar");
        }
        return leftEar;
    }


    public FlowPanel getDeleteIcon() {
        if (deleteIcon == null) {
            deleteIcon = new FlowPanel();
            deleteIcon.setStyleName("icon-uniE600");
            deleteIcon.addStyleName("level_deleteIcon");
            deleteIcon.setVisible(false);
            deleteIcon.setTitle("Delete level");
            deleteIcon.addDomHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    RTC.getInstance().deleteLevel(level.getId());
                }
            }, ClickEvent.getType());
        }
        return deleteIcon;
    }




    public TournamentLevel getLevel() {
        return level;
    }
}
