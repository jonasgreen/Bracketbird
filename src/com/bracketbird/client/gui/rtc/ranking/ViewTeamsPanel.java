package com.bracketbird.client.gui.rtc.ranking;

import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.RTCLayoutFac2;
import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.gui.rtc.event.REventListener;
import com.bracketbird.client.gui.rtc.event.UpdateTeamNameEvent;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.tournament.SeedingChangedEvent;
import com.bracketbird.client.model.tournament.TournamentListener;
import com.bracketbird.client.model.tournament.TournamentTeamEvent;
import com.bracketbird.clientcore.gui.FlowComponent;
import com.bracketbird.clientcore.gui.LabelComponent;
import com.bracketbird.clientcore.style.TextLayout;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class ViewTeamsPanel extends FlowComponent {

    FlowComponent teamsPanel;

    public ViewTeamsPanel() {
        init();
    }

    private void init() {
        System.out.println("ViewTeamsPanel init");
        add(new LabelComponent("The tournament has not started - registered teams until now are:"), RTCLayoutFac2.h1().padding(20));
        RTC.getInstance().getTournament().addSeedingListener(new TournamentListener<SeedingChangedEvent>() {
            public void onChange(SeedingChangedEvent event) {
                updateTeamsPanel();
            }
        });

        RTC.getInstance().getTournament().addTeamsListener(new TournamentListener<TournamentTeamEvent>() {
            public void onChange(TournamentTeamEvent event) {
                if(TournamentTeamEvent.Action.create == event.getAction()){
                    event.getTeam().addListener(new REventListener() {
                        public void onChange(REvent<?, ?> event) {
                            updateTeamsPanel();
                        }
                    }, new UpdateTeamNameEvent());
                }
                updateTeamsPanel();
            }
        });
    }

    private void updateTeamsPanel() {
        if(teamsPanel != null){
            teamsPanel.removeFromParent();

        }
        teamsPanel = new FlowComponent();
        teamsPanel.setStyleName("viewTeamsPanel_teams");
        add(teamsPanel);

        for (Team t  : RTC.getInstance().getTournament().getTeams()) {
            teamsPanel.add(createTeamComp(t));
        }
    }

    private Widget createTeamComp(Team t) {
        FlowComponent fl = new FlowComponent();
        fl.setStyleName("viewTeamsPanel_team");
        fl.add(new LabelComponent(t.getName()), new TextLayout().sizeH1().colorBase());
        return fl;
    }


}
