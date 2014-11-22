package com.bracketbird.client.gui.rtc;

import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.gui.rtc.event.REventListener;
import com.bracketbird.client.gui.rtc.event.UpdateTeamNameEvent;
import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.clientcore.gui.FlowComponent;
import com.bracketbird.clientcore.style.P;
import com.google.gwt.dom.client.Style;

/**
 *
 */
public class ViewMatch extends FlowComponent{

    private TeamScoreComp upperTeamComp;
    private TeamScoreComp lowerTeamComp;

    private Match match;
    private FlowComponent line;

    public ViewMatch(Match match){
        this.match = match;

        /*
        match.addMatchChangedListener(new TournamentListener<MatchEvent>() {
            public void onChange(MatchEvent event) {
                updateComponent();
            }
        });
        */
        this.setStyleName("matchView");
        add(getUpperTeamComp());
        add(getLine());
        add(getLowerTeamComp());


        match.getTeamHome().addListener(new REventListener() {
            public void onChange(REvent<?, ?> event) {
                updateComponent();
            }
        }, new UpdateTeamNameEvent());

        match.getTeamOut().addListener(new REventListener() {
            public void onChange(REvent<?, ?> event) {
                updateComponent();
            }
        }, new UpdateTeamNameEvent());


        updateComponent();
    }

    private void updateComponent() {
        getUpperTeamComp().update(match, match.getTeamHome());
        getLowerTeamComp().update(match, match.getTeamOut());
        if(match.isFinish()){
            getLine().setBackgroundColor(P.BACKGROUND_WHITE);
        }
        else{
            FlowComponent fl = new FlowComponent();
            fl.setStyleName("fieldBanner");
            //LabelComponent w = new LabelComponent("Bord 3");
            //fl.add(w, new TextLayout().sizeSmall().padding(0).paddingBottom(-4).verticalAlignMiddel());
            //add(fl);
            getLine().setBackgroundColor(P.BACKGROUND_ORANGE);
        }
    }


    public FlowComponent getLine() {
        if (line == null) {
            line = new FlowComponent();
            line.setHeight("1px");
            line.setWidth("240px");
        }
        return line;
    }


    public TeamScoreComp getLowerTeamComp() {
        if (lowerTeamComp == null) {
            lowerTeamComp = new TeamScoreComp(match.getTeamOut());
            lowerTeamComp.setHeight("23px");
            lowerTeamComp.getElement().getStyle().setWidth(238, Style.Unit.PX);

        }
        return lowerTeamComp;
    }

    public TeamScoreComp getUpperTeamComp() {
        if (upperTeamComp == null) {
            upperTeamComp = new TeamScoreComp(match.getTeamHome());
            upperTeamComp.setHeight("23px");
            upperTeamComp.getElement().getStyle().setWidth(238, Style.Unit.PX);


        }
        return upperTeamComp;
    }





}
