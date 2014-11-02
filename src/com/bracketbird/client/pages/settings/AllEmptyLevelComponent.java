package com.bracketbird.client.pages.settings;

/**
 *
 */
public class AllEmptyLevelComponent extends LevelComponent{


    public AllEmptyLevelComponent() {
        super(null);
        getInnerPanel().addStyleName("allEmpty_Component_innerPanel");
        getLeftEar().addStyleName("levelComponent_allEmpty");
        getRightEar().addStyleName("levelComponent_allEmpty");
    }


    protected void onMouseOut() {
    }

    protected void onMouseOver() {
    }


}
