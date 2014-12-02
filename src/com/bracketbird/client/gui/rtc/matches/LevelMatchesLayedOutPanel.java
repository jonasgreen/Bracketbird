package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.model.tournament.Stage;
import com.bracketbird.client.model.tournament.StageRound;
import com.bracketbird.clientcore.gui.VerticalComponent;

/**
 *
 */
public class LevelMatchesLayedOutPanel extends VerticalComponent {

    private Stage level;

    public LevelMatchesLayedOutPanel(Stage tl) {
        super();
        this.level = tl;
        init();
    }

    private void init() {
        for (StageRound round : level.getRounds()) {
            for (Match match : round.getMatches()) {

              //  addTableRow(new MatchTableRow(this, match));
            }



        }
    }


}
