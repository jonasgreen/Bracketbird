package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.*;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.client.model.tournament.*;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class FinalRankingDecisionPanel extends VerticalComponent {

    private ButtonComponent okButton;
    private SimplePanelComponent buttonPanel = new SimplePanelComponent();
    private SimplePanelComponent rankingPanel = new SimplePanelComponent();
    private SimplePanelComponent errorsPanel = new SimplePanelComponent();
    private FinalRankListPanel finalRow = null;
    private List<Position> positions;
    private TournamentLevel level;
    private boolean isFromClient;

    public FinalRankingDecisionPanel(TournamentLevel tl, List<Position> positions, boolean fromClient) {
        super();
        this.isFromClient = fromClient;
        this.level = tl;
        this.positions = positions;
        init();
    }

    private void init() {
        add(rankingPanel, new TextLayout(null, "100%"));

        finalRow = new FinalRankListPanel(null, positions);
        rankingPanel.add(finalRow, new TextLayout(20, 0, 0, 0, null, "100%"));

        add(errorsPanel, new TextLayout(20, 0, 0, 10, null, "100%"));
        add(buttonPanel, new TextLayout(null, "100%"));
        buttonPanel.add(new ButtonPanel(Horizontal.LEFT, getOkButton()), new TextLayout(10, 0, 10, 10, null, "100%"));
    }

    private void done() {
        buttonPanel.removeFromParent();
        errorsPanel.removeFromParent();
        List<Team> finalRankedTeams = finalRow.done();

        //update model
        List<TeamId> ids = new ArrayList<TeamId>();
        for (Team team : finalRankedTeams) {
            ids.add(team.getId());
        }
        if (isFromClient) {
            RTC.getInstance().levelFinished(level.getId(), convertFinalRank(ids));
        }

    }

    private List<TeamId[]> convertFinalRank(List<TeamId> finalRank) {
        List<TeamId[]> list = new ArrayList<TeamId[]>();
        for (TeamId teamId : finalRank) {
            list.add(new TeamId[]{teamId});
        }
        return list;
    }

    public ButtonComponent getOkButton() {
        if (okButton == null) {
            okButton = new ButtonComponent("Accept ranking");
            okButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    List<String> errors = new ArrayList<String>();
                    errors.addAll(finalRow.validate());
                    if (errors.isEmpty()) {
                        done();

                    }
                    else {
                        VerticalComponent vc = new VerticalComponent();
                        TextLayout tl = new TextLayout().sizeSmall().bold().colorRed().noWrap().padding(4);
                        for (String errorMsg : errors) {
                            vc.add(new LabelComponent(errorMsg), tl);
                        }
                        errorsPanel.add(vc, new TextLayout().border(1).borderColor(P.COLOR_DARK_RED));
                    }
                }
            });
        }
        return okButton;
    }


}
