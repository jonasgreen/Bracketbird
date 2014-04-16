package com.bracketbird.client.gui.rtc.ranking;

import com.bracketbird.client.model.tournament.*;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

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

    public MatchesViewPanel(final Group level) {
        add(matchesPlayedPanel);
        final List<MatchView> finishedList = new ArrayList<MatchView>();
        final List<MatchView> notFinishedList = new ArrayList<MatchView>();
        for (Match match : level.getMatches()) {
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

        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                System.out.println("WIDTHER:   **************");
                for (MatchView matchView : notFinishedList) {
                    System.out.println(matchView.getTeamPanel().getOffsetWidth());
                }
                for (MatchView matchView : finishedList) {
                    System.out.println(matchView.getTeamPanel().getOffsetWidth());
                }
            }
        });

        matchesPlayedPanel.setStyleName("matchesPlayedPanel");
        for (Match m : level.getMatches()) {
            m.addMatchChangedListener(new TournamentListener<MatchEvent>() {
                @Override
                public void onChange(MatchEvent event) {
                    updateMatchesPlayedPanel(level);
                }
            });
        }

        updateMatchesPlayedPanel(level);
    }

    private void updateMatchesPlayedPanel(TournamentLevel level) {
        List<Match> matches = level.getMatches();

        int mathcesPlayed = 0;
        for (Match m : matches) {
            if(m.isFinish()){
                mathcesPlayed++;
            }
        }
        System.out.println("MATCHES PLAYED: "+mathcesPlayed);
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
        System.out.println("CLIENT HEIGHT: "+ Window.getClientHeight());
        return Window.getClientHeight()/3;
    }


    public void moveToFinished(MatchView matchView) {
        finishedMatches.add(matchView);
    }

    public void moveToNotFinished(MatchView matchView){
        notFinishedMatches.add(matchView);
    }
}
