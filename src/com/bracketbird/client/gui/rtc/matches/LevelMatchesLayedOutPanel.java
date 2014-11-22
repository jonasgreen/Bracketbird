package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.model.tournament.Round;
import com.bracketbird.client.model.tournament.TournamentStage;
import com.bracketbird.clientcore.gui.VerticalComponent;

/**
 *
 */
public class LevelMatchesLayedOutPanel extends VerticalComponent {

    private TournamentStage level;

    public LevelMatchesLayedOutPanel(TournamentStage tl) {
        super();
        this.level = tl;
        init();
    }

    private void init() {
        for (Round round : level.getRounds()) {
            for (Match match : round.getMatches()) {

              //  addTableRow(new MatchTableRow(this, match));
            }



        }
    }


}
