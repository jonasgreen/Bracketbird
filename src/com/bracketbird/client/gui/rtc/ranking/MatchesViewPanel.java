package com.bracketbird.client.gui.rtc.ranking;

import com.bracketbird.client.model.tournament.Group;
import com.bracketbird.client.model.tournament.Match;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MatchesViewPanel extends FlowPanel{

    private int bottomOfFinishedMatches = calculateBottomOfFinishedMatches();

    private FinishedMatches finishedMatches;
    private NotFinishedMatches notFinishedMatches;

    public MatchesViewPanel(Group level) {
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
