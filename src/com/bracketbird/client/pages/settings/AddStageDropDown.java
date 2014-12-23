package com.bracketbird.client.pages.settings;

import com.bracketbird.client.rtc.RTC;
import com.bracketbird.client.model.StageType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 *
 */
public class AddStageDropDown extends PopupPanel{

    private FlowPanel groupPanel;
    private FlowPanel knockoutPanel;


    public AddStageDropDown() {
        super(true, true);
        setStyleName("settingsPage_addStageDropDown");

        FlowPanel innerPanel = new FlowPanel();
        innerPanel.setStyleName("settingsPage_addStageDropDown_innerPanel");
        add(innerPanel);

        innerPanel.add(getGroupPanel());
        innerPanel.add(getKnockoutPanel());
        getElement().getStyle().setZIndex(10);
    }


    public FlowPanel getGroupPanel() {
        if (groupPanel == null) {
            groupPanel = new FlowPanel();
            groupPanel.setStyleName("settingsPage_addStageDropDown_item");
            groupPanel.addStyleName("addSettingsDropDown_item_group");

            groupPanel.add(new Label("Group"));
            groupPanel.addDomHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    RTC.getInstance().createStage(StageType.group);
                    hide();
                }
            }, ClickEvent.getType());
        }
        return groupPanel;
    }

    public FlowPanel getKnockoutPanel() {
        if (knockoutPanel == null) {
            knockoutPanel = new FlowPanel();
            knockoutPanel.setStyleName("settingsPage_addStageDropDown_item");
            knockoutPanel.addStyleName("addSettingsDropDown_item_knockout");

            knockoutPanel.add(new Label("Knockout"));
            knockoutPanel.addDomHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    RTC.getInstance().createStage(StageType.knockout);
                    hide();
                }
            }, ClickEvent.getType());
        }
        return knockoutPanel;
    }


}
