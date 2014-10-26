package com.bracketbird.client.gui.rtc.settings;


import com.bracketbird.client.gui.main.PropertiesFac;
import com.bracketbird.client.model.tournament.Cup;
import com.bracketbird.client.model.tournament.LevelSettings;
import com.bracketbird.clientcore.gui.IntegerContainer;
import com.bracketbird.clientcore.gui.ListContainer;
import com.bracketbird.clientcore.model.TournamentLevelConstant;

/**
 *
 */
public class CupSettingsPanel extends SettingsPanel {


    public IntegerContainer ENTERING_TEAMS = PropertiesFac.maximumTeams();
    public ListContainer<Integer> ELIMINATION_TYPE = PropertiesFac.eliminationType();


    protected CupSettingsPanel(Cup level) {
        super("Cup stage settings", level);
        init();
    }

    private void init() {
        addFlexTable(ELIMINATION_TYPE, ENTERING_TEAMS);
    }


    public LevelSettings getValue() {
        LevelSettings ss = new LevelSettings();
        ss.setEliminationType(ELIMINATION_TYPE.getValue());
        ss.setMaxNumberOfTeams(ENTERING_TEAMS.getValue());
        return ss;
    }


    @Override
    public Integer getType() {
        return TournamentLevelConstant.CUP.getValue();
    }

    public void setValue(LevelSettings ss) {
        ENTERING_TEAMS.setValue(ss.getMaxNumberOfTeams());
        ELIMINATION_TYPE.setValue(ss.getEliminationType());
    }


}