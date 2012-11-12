package com.bracketbird.client.gui.rtc.ranking;


import com.bracketbird.client.gui.rtc.RTCLayoutFac2;
import com.bracketbird.client.gui.rtc.RunningTournamentPageController;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.TextLayout;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CustomScrollPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalScrollbar;

import java.util.List;

/**
 *
 */
public class GroupRankingPanel extends FlowComponent implements RankingPanel {

    private TournamentLevel level;
    private SimplePanelComponent rankingHolder = new SimplePanelComponent();
    private FlowComponent contentLeft = new FlowComponent();
    private FlowComponent contentRight = new FlowComponent();

    private static int widthContentRight = 200;


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
        contentLeft.add(rankingHolder, new TextLayout(null, "100%"));
        add(contentLeft);
        add(contentRight);

        contentLeft.getElement().getStyle().setFloat(Style.Float.LEFT);
        contentRight.getElement().getStyle().setFloat(Style.Float.LEFT);
        contentRight.setWidth("200px");
        contentRight.setHeight("200px");

        contentLeft.getElement().getStyle().setBackgroundColor("blue");
        contentRight.getElement().getStyle().setBackgroundColor("red");

        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                updateSizes();
            }
        });
        initialSetup();
    }

    public void updateSizes() {
        int totalWidth = Window.getClientWidth();
        CustomScrollPanel scrollPanel = RunningTournamentPageController.getInstance().getPage().getScrollPanel().getScrollPanel();
        VerticalScrollbar verticalScrollbar = scrollPanel.getVerticalScrollbar();
        int scrollH = verticalScrollbar.getScrollHeight();
        int sch = scrollPanel.getElement().getClientHeight();
        int schOff = scrollPanel.getElement().getOffsetHeight();
        int clientHeight = Window.getClientHeight();
        final int totalHeight = (scrollH > clientHeight ? scrollH : clientHeight) - 75;
        if (totalWidth < 600) {
            contentLeft.setWidth(400 + "px");
        }
        else {
            contentLeft.setWidth((totalWidth - widthContentRight) + "px");
        }
        contentRight.setHeight(totalHeight + "px");

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
        updateSizes();
    }
}
