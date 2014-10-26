package com.bracketbird.client.gui.rtc.settings;

import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.*;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.util.*;

import java.util.*;

/**
 *
 */
public class StagePanelHolder extends HorizontalComponent {

    private TextLayout lQuestion = new TextLayout(Horizontal.CENTER, Vertical.MIDDLE).sizeH2().bold().colorBlack().padding(20);

    private List<StagePanel> panelList = new ArrayList<StagePanel>();
    public ListContainer<Integer> LEVELS = new ListContainer<Integer>("list", TournamentLevelConstant.LIST_WITH_CHOOSE, 0, false, false);

    private SimplePanelComponent levelHolder = new SimplePanelComponent();
    private LabelComponent questionMark;
    private HorizontalComponent stages;
    private TournamentSettingsPanel parent;

    public StagePanelHolder(TournamentSettingsPanel parent) {
        super();
        this.parent = parent;
        init();
    }

    private void init() {


        add(new ImageComponent("img/start.png"));
        add(levelHolder);
        levelHolder.add(getQuestionMark(), lQuestion);
        add(new ImageComponent("img/end.png"));
        add(LEVELS.getGui(), new TextLayout(0, 0, 0, 10, Vertical.MIDDLE).sizeSmall().padding(2).colorBaseDark());

        LEVELS.getGui().getListBox().addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
                int levelType = LEVELS.getValue();
                if (levelType == -1) {
                    //ignore
                }
                else if (levelType == -2 && !panelList.isEmpty()) {
                    if (!panelList.isEmpty()) {
                        RTC.getInstance().deleteLevel(CU.lastItem(panelList).getLevel().getId());
                    }
                }
                else {
                    //RTC.getInstance().createLevel(levelType);
                }
                LEVELS.setValue(-1);
            }
        });
    }

    void createStage(TournamentLevel level) {
        if (panelList.isEmpty()) {
            levelHolder.add(getStages());
            parent.getClickText().setText("Click on a stage to edit its settings.");
        }

    }

    private String getImageUrl(int levelType) {
        if (levelType == TournamentLevelConstant.SEEDING.getValue()) {
            return "img/seeding.png";
        }
        if (levelType == TournamentLevelConstant.CUP.getValue()) {
            return "img/cup.png";
        }
        if (levelType == TournamentLevelConstant.GROUP.getValue()) {
            return "img/group.png";
        }
        throw new SystemException("tournament leveltype is not supported for image. Leveltype:" + levelType);
    }


    public HorizontalComponent getStages() {
        if (stages == null) {
            stages = new HorizontalComponent();
        }
        return stages;
    }

    public LabelComponent getQuestionMark() {
        if (questionMark == null) {
            questionMark = new LabelComponent("?");
        }
        return questionMark;
    }

    void removeStage() {
        if (!panelList.isEmpty()) {

            StagePanel panel = panelList.remove(panelList.size() - 1);
            panel.removeFromParent();
            if (panelList.isEmpty()) {
                levelHolder.add(getQuestionMark(), lQuestion);
                parent.getClickText().setText("");
            }
        }
    }

    public SimplePanelComponent getStageHolder() {
        return levelHolder;
    }

    public boolean isEmpty() {
        return panelList.isEmpty();
    }
}
