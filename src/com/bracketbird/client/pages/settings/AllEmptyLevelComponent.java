package com.bracketbird.client.pages.settings;

/**
 *
 */
public class AllEmptyLevelComponent extends LevelComponent{


    public AllEmptyLevelComponent() {
        super(null);
        getInnerPanel().addStyleName("settingsPage_stageComp_allEmpty_innerPanel");
        getLeftEar().addStyleName("settingsPage_stageComp_allEmpty");
        getRightEar().addStyleName("settingsPage_stageComp_allEmpty");
    }


    protected void onMouseOut() {
    }

    protected void onMouseOver() {
    }


}
