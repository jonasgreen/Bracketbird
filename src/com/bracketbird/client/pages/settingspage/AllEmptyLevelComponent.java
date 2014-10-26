package com.bracketbird.client.pages.settingspage;

import com.bracketbird.client.model.LevelType;

/**
 *
 */
public class AllEmptyLevelComponent extends LevelComponent{


    public AllEmptyLevelComponent() {
        super(LevelType.missing);
        getInnerPanel().addStyleName("levelComponent_allEmpty");
        getLeftEar().addStyleName("levelComponent_allEmpty");
        getRightEar().addStyleName("levelComponent_allEmpty");
    }

}
