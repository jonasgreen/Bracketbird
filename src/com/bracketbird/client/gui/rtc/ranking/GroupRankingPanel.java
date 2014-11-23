package com.bracketbird.client.gui.rtc.ranking;


import com.bracketbird.client.gui.rtc.RTCLayoutFac2;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;

import java.util.*;

/**
 *
 */
public class GroupRankingPanel extends FlowComponent implements RankingPanel {

    private Stage level;



    private SimpleFlowComponent groupPanel = new SimpleFlowComponent();
    private MatchesViewPanel matchesViewPanel;


    private TournamentListener<MatchEvent> matchListener = new TournamentListener<MatchEvent>() {
        public void onChange(MatchEvent event) {
            layoutRanking();
        }
    };

    public Stage getLevel() {
        return level;
    }

    public FlowComponent getContent() {
        return this;
    }

    public GroupRankingPanel(Stage l) {
        super();
        this.level = l;

        init();
        bindMathces();
        layoutRanking();
    }

    private void init() {
        matchesViewPanel = new MatchesViewPanel((GroupStage) level);
        groupPanel.getElement().getStyle().setFloat(Style.Float.LEFT);
        matchesViewPanel.getElement().getStyle().setFloat(Style.Float.RIGHT);

        initialSetup();
    }


    private void bindMathces() {
        for (Match m : level.getMatches()) {
            //m.addMatchChangedListener(matchListener);
        }
    }

    private void initialSetup() {
        bindMathces();
        if (level.isNotReady()) {
            layoutNoRanking();
        }
        else {
            layoutRanking();
           // if (RTC.getInstance().getTournament().isViewOnly()) {
                layoutMatchesPanel();
           // }
        }

    }


    public void layoutNoRanking() {
        groupPanel.add(new HtmlComponent("Matches not layed out yet."), RTCLayoutFac2.h3());
    }


    public void layoutRanking() {
        layoutGroupRanking();

    }

    private void layoutMatchesPanel() {
        add(matchesViewPanel);
        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {

                resize();
            }
        });
        resize();
    }

    private void resize() {
        int width = Window.getClientWidth();
        if(width > 800){
            matchesViewPanel.setVisible(true);
            groupPanel.setWidth((width - 400) + "px");
        }
        else{
            matchesViewPanel.setVisible(false);
            groupPanel.setWidth(width+"px");
        }
    }


    private void layoutGroupRanking() {
        StringBuffer sb = new StringBuffer();
        List<Group> grs = ((GroupStage) level).getGroups();
        for (Group gr : grs) {
            RankingSheet sheet = new RankingSheet(gr.getMatches(), level.getSettings());
            GroupScoreSheet gs = new GroupScoreSheet(gr, sheet.getPositions());
            gs.generateHtml(sb);
        }

        groupPanel.add(new HtmlComponent(sb.toString()), new TextLayout(20, 0, 0, 0));
        add(groupPanel);

    }


    public void relayout() {

    }
}