package com.bracketbird.client.pages.matches;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.client.model.tournament.Position;
import com.bracketbird.client.model.tournament.Stage;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FinalRankingDecisionPanel extends FlowPanel {

    private Button okButton;
    private SimplePanel buttonPanel = new SimplePanel();
    private SimplePanel rankingPanel = new SimplePanel();
    private SimplePanel errorsPanel = new SimplePanel();
    private GroupRankingPanel finalRow = null;
    private List<Position> positions;
    private Stage level;

    public FinalRankingDecisionPanel(Stage tl, List<Position> positions) {
        super();
        setStyleName("finalRanking_decisionPanel");
        this.level = tl;
        this.positions = positions;
        init();
    }

    private void init() {
        add(rankingPanel);

        /*finalRow = new GroupRankingPanel(null, positions);
        rankingPanel.add(finalRow);
*/
        add(errorsPanel);
        add(buttonPanel);
        buttonPanel.add(getOkButton());
    }

    private void done() {
        buttonPanel.removeFromParent();
        errorsPanel.removeFromParent();
        List<Team> finalRankedTeams = finalRow.done();

        //updateResult model
        List<TeamId> ids = new ArrayList<TeamId>();
        for (Team team : finalRankedTeams) {
            ids.add(team.getId());
        }
        RTC.getInstance().levelFinished(level.getId(), convertFinalRank(ids));


    }

    private List<TeamId[]> convertFinalRank(List<TeamId> finalRank) {
        List<TeamId[]> list = new ArrayList<TeamId[]>();
        for (TeamId teamId : finalRank) {
            list.add(new TeamId[]{teamId});
        }
        return list;
    }

    public Button getOkButton() {
        if (okButton == null) {
            okButton = new Button("Accept ranking");
            okButton.setStyleName("primaryButton");
            okButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    List<String> errors = new ArrayList<String>();
                    errors.addAll(finalRow.validate());
                    if (errors.isEmpty()) {
                        done();
                    }
                    else {
                        FlowPanel vc = new FlowPanel();
                        for (String errorMsg : errors) {
                            vc.add(new Label(errorMsg));
                        }
                        errorsPanel.clear();
                        errorsPanel.add(vc);
                    }
                }
            });
        }
        return okButton;
    }


}
