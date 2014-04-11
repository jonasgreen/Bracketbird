package com.bracketbird.client.gui.rtc.ranking;

import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class GroupScoreSheet extends VerticalComponent {

    private static P color = Color.textBaseDark();

    private List<Position> positions;
    private AGroup group;

    public GroupScoreSheet(AGroup gr, List<Position> positions) {
        super();
        this.group = gr;
        this.positions = positions;
    }


    public void generateHtml(StringBuffer sb) {
        if (positions == null || positions.isEmpty()) {
            sb.append("<div style=\"float:left; padding:0px 45px 20px 0;\">No matches</div>");
            return;
        }
        sb.append("<div style=\"float:left;\">");

        //  appendGroupName(sb, group);

        sb.append("<div class=\"groupTable\"><table cellpadding=\"0\" cellspacing=\"0\"");
        new TextLayout().sizeNormal().colorBase().asHtmlStyle(sb);
        sb.append(">");
        sb.append("<tbody>");

        appendGroupName(sb);
        appendHeaders(sb);
        appendRows(sb);

        sb.append("</tbody>");
        sb.append("</table></div></div>");
    }

    public void appendGroupName(StringBuffer sb) {
        TextLayout tl = new TextLayout().padding(4, 6, 4, 6).sizeH3().bold().noWrap();
        sb.append("<tr class=\"groupTitleRow\">");
        sb.append("<td colspan=\"8\"");
        tl.asHtmlStyle(sb);
        sb.append(">").append("Group ").append(group.getName()).append("</td>");

        sb.append("</tr>");
    }


    public void appendHeaders(StringBuffer sb) {
        TextLayout tl = new TextLayout().padding(4, 6, 2, 6).sizeNormal().italic().noWrap();
        sb.append("<tr class=\"groupHeaderRow\">");
        appendTD(sb, tl, "#");
        appendTD(sb, tl, "Team");
        appendTD(sb, tl, "m", "Matches played");
        appendTD(sb, tl, "w", "Won matches");
        appendTD(sb, tl, "d", "Draw mathces");
        appendTD(sb, tl, "l", "Lost mathces");

        appendTD(sb, tl, "Score");
        appendTD(sb, tl, "Points");

        sb.append("</tr>");
    }


    public void appendTD(StringBuffer sb, TextLayout tl, String value) {
        sb.append("<td ");
        tl.asHtmlStyle(sb);
        sb.append(">").append(value).append("</td>");

    }

    public void appendTD(StringBuffer sb, TextLayout tl, int value) {
        sb.append("<td ");
        tl.asHtmlStyle(sb);
        sb.append(">").append(value).append("</td>");

    }

    public void appendTD(StringBuffer sb, TextLayout tl, String value, String title) {
        sb.append("<td title=\"").append(title).append("\" ");
        tl.asHtmlStyle(sb);
        sb.append(">").append(value).append("</td>");

    }


    private void appendRows(StringBuffer sb) {

        ColorWheel cw = new ColorWheel(P.BACKGROUND_F5, P.BACKGROUND_F0);
        List<TeamResultSum> list = new ArrayList<TeamResultSum>();

        for (Position p : positions) {
            list.addAll(p.getPointsCounters());
        }

        int pCount = 1;
        for (TeamResultSum position : list) {
            appendRow(sb, position, pCount++, cw.getNext());
        }

    }

    private void appendRow(StringBuffer sb, TeamResultSum trs, int pCount, P bgColor) {
        TextLayout tlScore = new TextLayout().sizeH3().padding(6, 7, 2, 7).add(color).add(bgColor).alignRight().borderLeft(1).borderColor(P.COLOR_BLACK).noWrap();

        TextLayout tl = new TextLayout().sizeH3().padding(6, 7, 2, 7).add(color).add(bgColor).alignRight().noWrap();
        TextLayout tlTeam = new TextLayout().sizeH3().padding(6, 7, 2, 7).add(color).add(bgColor).alignLeft().noWrap();

        sb.append("<tr>");

        appendTD(sb, tl, pCount);
        appendTD(sb, tlTeam, trs.getTeam().getName());
        appendTD(sb, tl, trs.getPlayedMatches());
        appendTD(sb, tl, trs.getWonMatches());
        appendTD(sb, tl, trs.getDrawMatchs());
        appendTD(sb, tl, trs.getLostMatchs());

        appendTD(sb, tlScore, trs.getScoredGoals() - trs.getReceivedGoals());
        appendTD(sb, tl, trs.getPoints());
        sb.append("</tr>");

    }


}
