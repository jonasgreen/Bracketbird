package com.bracketbird.client.pages.settingspage;

/**
 *
 */
public class AllEmptyLevelComponent extends LevelComponent{


    public AllEmptyLevelComponent() {
        super("?");
        getInnerPanel().addStyleName("levelComponent_allEmpty");
        getLeftEar().addStyleName("levelComponent_allEmpty");
        getRightEar().addStyleName("levelComponent_allEmpty");
    }

}
