package com.bracketbird.client.gui.rtc.ranking;


import com.bracketbird.client.gui.rtc.RTCLayoutFac2;
import com.bracketbird.client.gui.rtc.ViewMatch;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.google.gwt.dom.client.Style;

import java.util.*;

/**
 *
 */
public class GroupRankingPanel extends FlowComponent implements RankingPanel {

    private TournamentLevel level;
    private SimplePanelComponent rankingHolder = new SimplePanelComponent();



    private TournamentListener<LevelStateEvent> levelStateListner = new TournamentListener<LevelStateEvent>() {
        public void onChange(LevelStateEvent event) {
            bindMathces();
            layoutRanking();
        }
    };

    private TournamentListener<MatchEvent> matchListener = new TournamentListener<MatchEvent>() {
        public void onChange(MatchEvent event) {
            layoutRanking();
        }
    };

    public TournamentLevel getLevel() {
        return level;
    }

    public FlowComponent getContent() {
        return this;
    }

    public GroupRankingPanel(TournamentLevel l) {
        super();
        this.level = l;
        init();
    }

    private void init() {
        level.addStateListener(levelStateListner);
        add(rankingHolder, new TextLayout(null, "100%"));
        initialSetup();
    }

    private void bindMathces() {
        for (Match m : level.getMatches()) {
            m.addMatchChangedListener(matchListener);
        }
    }

    private void initialSetup() {
        bindMathces();
        if (level.isEmpty()) {
            layoutNoRanking();
        }
        else {
            layoutRanking();
        }
    }


    public void layoutNoRanking() {
        VerticalComponent vc = new VerticalComponent();
        vc.add(new HtmlComponent("Matches not layed out yet."), RTCLayoutFac2.h3());
        rankingHolder.add(vc, new TextLayout(null, "100%"));
    }


    public void layoutRanking() {
        layoutGroupRanking();
    }


    private void layoutGroupRanking() {
        HorizontalComponent content = new HorizontalComponent();
        //VerticalComponent vc = new VerticalComponent();
        StringBuffer sb = new StringBuffer();
        List<AGroup> grs = ((Group) level).getGroups();
        for (AGroup gr : grs) {
            RankingSheet sheet = new RankingSheet(gr.getMatches(), level.getStageSettings());
            GroupScoreSheet gs = new GroupScoreSheet(gr, sheet.getPositions());
            gs.generateHtml(sb);
        }

        content.add(new HtmlComponent(sb.toString()), new TextLayout(20, 0, 0, 0));

       /* FlowComponent right = new FlowComponent();

        for (Round round : level.getRounds()) {
            for (Match match : round.getMatches()) {
                right.add(new ViewMatch(match));
            }
        }

        content.add(right, new TextLayout(20,0,0,0));
        right.getElement().getStyle().setFloat(Style.Float.RIGHT);
        */
        rankingHolder.add(content, new TextLayout(null, "100%"));
    }



    public void relayout() {

    }
}