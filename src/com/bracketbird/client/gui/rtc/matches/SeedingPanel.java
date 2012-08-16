package com.bracketbird.client.gui.rtc.matches;

import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.clientcore.gui.FlowComponent;
import com.bracketbird.clientcore.gui.TextBoxComponent;
import com.bracketbird.clientcore.style.P;
import com.bracketbird.clientcore.style.TextLayout;

import java.util.*;

/**
 *
 */
public class SeedingPanel extends FlowComponent {

    private List<SeedingPanelComponent> rows = new ArrayList<SeedingPanelComponent>();

    public SeedingPanel() {
        super();
        init();
    }

    private void init() {
        //setWidth("400px");


    }


    public void setInitialSeedings() {
        reset();
        for (Team team : RTC.getInstance().getTournament().getTeams()) {
            SeedingPanelComponent w = new SeedingPanelComponent(this, team);
            w.getTeamName().setText(team.getName());
            w.getSeeding().setText("" + team.getSeeding());
            rows.add(w);
            addRow(w);
        }
        if (!rows.isEmpty()) {
            rows.get(0).setFocus(true);
        }
    }

    private void addRow(SeedingPanelComponent w) {
        add(w, new TextLayout(null, "100%").backgroundGreyLight().border(2).borderColor(P.BACKGROUND_F0));
    }

    private void reset() {
        for (SeedingPanelComponent row : rows) {
            row.removeFromParent();
        }
        rows = new ArrayList<SeedingPanelComponent>();
    }

    public void clear() {
        for (SeedingPanelComponent row : rows) {
            row.getSeeding().setText("");
        }
    }

    public void focusPrevious(SeedingPanelComponent sp) {
        int index = rows.indexOf(sp);
        if (index > 0) {
            rows.get(index - 1).setFocus(true);
        }

    }

    public void focusNext(SeedingPanelComponent sp) {
        int index = rows.indexOf(sp);
        if (index < rows.size() - 1) {
            rows.get(index + 1).setFocus(true);
        }
    }

    public void moveUp(SeedingPanelComponent sp) {
        int index = rows.indexOf(sp);
        if (index > 0) {
            rows.remove(index);
            rows.add(index - 1, sp);
            sp.getSeeding().setText("" + (index));
            SeedingPanelComponent sp2 = rows.get(index);
            if (!sp2.isEmpty()) {
                sp2.getSeeding().setText("" + (index + 1));
            }
            repaintRows();
            sp.setFocus(true);
        }
    }

    private void repaintRows() {
        for (SeedingPanelComponent row : rows) {
            row.removeFromParent();
        }
        for (SeedingPanelComponent row : rows) {
            addRow(row);
        }
    }


    public void moveDown(SeedingPanelComponent sp) {
        int index = rows.indexOf(sp);
        if (index < rows.size() - 1) {
            rows.remove(index);
            rows.add(index + 1, sp);
            sp.getSeeding().setText("" + (index + 2));
            SeedingPanelComponent sp2 = rows.get(index);
            if (!sp2.isEmpty()) {
                sp2.getSeeding().setText("" + (index + 1));
            }
            repaintRows();
            sp.setFocus(true);
        }
    }

    public int getSeeding(SeedingPanelComponent sp) {
        return rows.indexOf(sp) + 1;
    }

    public void setSeeding(SeedingPanelComponent sp, int newSeed) {
        newSeed = ensureLegalSeed(newSeed);
        sp.getSeeding().setText(""+newSeed);
        int oldSeed = getSeeding(sp);

        if (oldSeed == newSeed) {
            return;
        }
        rows.remove(rows.indexOf(sp));
        rows.add(newSeed - 1, sp);

        if (oldSeed > newSeed) {
            incrementSeeding(newSeed, oldSeed);
        }
        else {
            decrementSeeding(oldSeed, newSeed);
        }
        repaintRows();
    }
    
    private int ensureLegalSeed(int seed){
        if(seed < 1){
            return 1;
        }
        if(seed > rows.size()){
            return rows.size();
        }
        return seed;
    }


    //both inclusive
    private void incrementSeeding(int fromSeeding, int toSeeding) {
        int fromIndex = fromSeeding;
        while (fromIndex < toSeeding) {
            TextBoxComponent tb = rows.get(fromIndex++).getSeeding();
            tb.setText("" + (Integer.valueOf(tb.getText()) + 1));
        }
    }


    //both inclusive
    private void decrementSeeding(int fromSeeding, int toSeeding) {
        int fromIndex = fromSeeding - 1;
        int toIndex = toSeeding - 1;
        while (fromIndex < toIndex) {
            TextBoxComponent tb = rows.get(fromIndex++).getSeeding();
            tb.setText("" + (Integer.valueOf(tb.getText()) -1));
        }
    }

    public int getIndexOf(SeedingPanelComponent seedingPanelComponent) {
        return rows.indexOf(seedingPanelComponent);
    }

    public void setFocus(int index) {
        rows.get(index).setFocus(true);
    }

    public void shuffle() {
    	Random rgen = new Random();
        SeedingPanelComponent[] randomArray = new SeedingPanelComponent[rows.size()];

        for (SeedingPanelComponent sp : rows) {
            int i = rgen.nextInt(rows.size());
            while (randomArray[i] != null){
                i = rgen.nextInt(rows.size());
            }
            randomArray[i] = sp;
            sp.getSeeding().setText(""+(i+1));
        }
        
        rows = new ArrayList<SeedingPanelComponent>();
        Collections.addAll(rows, randomArray);
    	
        repaintRows();
        setFocus(0);
    }

    public List<TeamId> getSeedings() {
        List<TeamId> seedings = new ArrayList<TeamId>();
        for (SeedingPanelComponent row : rows) {
            seedings.add(row.getTeam().getId());
        }

        return seedings;
    }
}