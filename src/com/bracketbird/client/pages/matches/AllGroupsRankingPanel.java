package com.bracketbird.client.pages.matches;

import com.bracketbird.client.model.Team;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.ArrayList;
import java.util.List;

public class AllGroupsRankingPanel extends FlowPanel {

    private List<RankingPanel> rankPanels = new ArrayList<RankingPanel>();
    private FlowPanel errorPanelHolder = new FlowPanel();
    private Button okButton;

    private WhenRanked whenRanked;


    public AllGroupsRankingPanel(List<GroupPositions> groupsPositions, WhenRanked whenRanked) {
        this.whenRanked = whenRanked;

        setStyleName("allGroupsRankingPanel");
        for (GroupPositions gr : groupsPositions) {
            RankingPanel rp = new RankingPanel(gr.getGroup().getName(), gr.getPositionOfTeams());
            rp.setWidth("280px");
            rankPanels.add(rp);
            add(rp);
        }

        FlowPanel buttonPanel = new FlowPanel();
        buttonPanel.add(getOkButton());
        add(buttonPanel);

    }


    public Button getOkButton() {
        if (okButton == null) {
            okButton = new Button("Accept ranking");

            okButton.setStyleName("primaryButton");
            okButton.addStyleName("finalRanking_button");

            okButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    List<String> errors = new ArrayList<String>();
                    for (RankingPanel rp : rankPanels) {
                        errors.addAll(rp.validate());
                    }
                    if (!errors.isEmpty()) {
                        handleErrors(errors);
                    }
                    else {
                        rankingIsDone();

                    }
                }
            });
        }
        return okButton;
    }

    private void handleErrors(List<String> errors) {
        FlowPanel vc = new FlowPanel();
        for (String errorMsg : errors) {
            vc.add(new Label(errorMsg));
        }
        errorPanelHolder.clear();
        errorPanelHolder.add(vc);
    }

    private void rankingIsDone() {
        List<List<Team>> finalGroupRankings = new ArrayList<List<Team>>();
        for (RankingPanel rp : rankPanels) {
            finalGroupRankings.add(rp.done());
        }

        whenRanked.onRanked(finalGroupRankings);
    }


}
