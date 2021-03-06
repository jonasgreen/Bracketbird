package com.bracketbird.client.pages.scores.ranking;

import com.bracketbird.client.model.tournament.GroupStage;
import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.model.tournament.Stage;
import com.bracketbird.client.model.event.UpdateEvent;
import com.bracketbird.client.model.event.UpdateHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MatchesViewPanel extends FlowPanel{

    private int bottomOfFinishedMatches = calculateBottomOfFinishedMatches();

    private FinishedMatches finishedMatches;
    private NotFinishedMatches notFinishedMatches;
    private FlowPanel matchesPlayedPanel = new FlowPanel();

    public MatchesViewPanel(final GroupStage stage) {
        add(matchesPlayedPanel);
        final List<MatchView> finishedList = new ArrayList<MatchView>();
        final List<MatchView> notFinishedList = new ArrayList<MatchView>();
        for (Match match : stage.getMatches()) {
            final MatchView v = new MatchView(match);
            add(v);
            if(match.isFinish()){
                finishedList.add(v);
            }
            else{
                notFinishedList.add(v);
            }
        }

        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                windowResized();
            }
        });

        finishedMatches = new FinishedMatches(this, bottomOfFinishedMatches, finishedList);
        notFinishedMatches = new NotFinishedMatches(this, bottomOfFinishedMatches, notFinishedList);


        matchesPlayedPanel.setStyleName("matchesPlayedPanel");
        for (Match m : stage.getMatches()) {
            m.matchHandlers.addHandler(new UpdateHandler<Match>() {
                @Override
                public void onUpdate(UpdateEvent<Match> event) {
                    updateMatchesPlayedPanel(stage);
                }
            });
        }

        updateMatchesPlayedPanel(stage);
    }

    private void updateMatchesPlayedPanel(Stage level) {
        List<Match> matches = level.getMatches();

        int mathcesPlayed = 0;
        for (Match m : matches) {
            if(m.isFinish()){
                mathcesPlayed++;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(mathcesPlayed);
        sb.append(" of ").append(matches.size()).append(" matches played");
        matchesPlayedPanel.clear();
        matchesPlayedPanel.add(new Label(sb.toString()));
    }

    private void windowResized() {
        bottomOfFinishedMatches = calculateBottomOfFinishedMatches();
        finishedMatches.setBottom(bottomOfFinishedMatches);
        notFinishedMatches.setTop(bottomOfFinishedMatches);
    }

    private static int calculateBottomOfFinishedMatches(){
        return Window.getClientHeight()/3;
    }


    public void moveToFinished(MatchView matchView) {
        finishedMatches.add(matchView);
    }

    public void moveToNotFinished(MatchView matchView){
        notFinishedMatches.add(matchView);
    }
}
