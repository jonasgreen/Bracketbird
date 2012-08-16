package com.bracketbird.client.gui.rtc;

import com.bracketbird.client.model.Team;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;

import java.util.List;

/**
 *
 */
public class StressTeam {
    private int todo = 1;

    public StressTeam() {
        run();
    }

    private void run() {
        try {
            Timer t = new Timer() {
                @Override
                public void run() {
                    int choise = getTodo();
                    if (choise == 1) {
                        createTeam();
                    }
                    else if (choise == 2) {
                        updateSeeding();
                    }
                    else if (choise == 3) {
                        updateName();
                    }
                    else if (choise == 4) {
                        createTeam();
                    }
                    else {
                        deleteTeam();
                    }
                }
            };
            t.scheduleRepeating(5000);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void deleteTeam() {
        Team t = getRandomTeam();
        if (t == null) {
            return;
        }
        RTC.getInstance().deleteTeam(t.getId());
    }


    private void updateName() {
        Team t = getRandomTeam();
        if (t == null) {
            return;
        }
        RTC.getInstance().updateTeamName(t.getId(), "NAME_" + Random.nextInt(1000));
    }

    private void updateSeeding() {
        Team t = getRandomTeam();
        if (t == null) {
            return;
        }
    }

    private Team getRandomTeam() {
        List<Team> teams = RTC.getInstance().getTournament().getTeams();
        if (teams.isEmpty()) {
            return null;
        }
        return teams.get(Random.nextInt(teams.size()));
    }

    private void createTeam() {
        RTC.getInstance().createTeam();
    }

    private int getTodo() {
        if (todo > 5) {
            todo = 1;
        }
        return todo++;
    }
}
