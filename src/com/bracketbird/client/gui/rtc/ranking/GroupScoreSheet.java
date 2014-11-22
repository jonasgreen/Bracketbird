package com.bracketbird.client.gui.rtc.ranking;

import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class GroupScoreSheet extends VerticalComponent {

    private static P color = P.COLOR_WHITE_ALMOST;

    private List<Position> positions;
    private Group group;

    public GroupScoreSheet(Group gr, List<Position> positions) {
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
        TextLayout tl = new TextLayout().padding(4, 6, 2, 6).sizeNormal().italic().colorGrey().noWrap().borderBottom(1).borderColor(P.COLOR_4D);
        sb.append("<tr class=\"groupHeaderRow\">");
        appendTD(sb, tl, "");
        appendTD(sb, tl.alignRight(), "M", "Matches played");

        appendTD(sb, tl, "S", "Score");
        appendTD(sb, tl.paddingLeft(12), "P", "Points");

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

        ColorWheel cw = new ColorWheel(P.BACKGROUND_BLACK, P.BACKGROUND_BLACK);
        List<TeamResultSum> list = new ArrayList<TeamResultSum>();

        for (Position p : positions) {
            list.addAll(p.getPointsCounters());
        }

        int pCount = 1;
        for (TeamResultSum position : list) {
            appendRow(list.size() == pCount, sb, position, pCount++, cw.getNext());
        }

    }

    private void appendRow(boolean last, StringBuffer sb, TeamResultSum trs, int pCount, P bgColor) {
        TextLayout tlM = new TextLayout().sizeH3().padding(6, 7, 2, 7).add(color).add(bgColor).alignRight().borderLeft(1).borderColor(P.COLOR_4D).noWrap().colorGrey();

        TextLayout tl = new TextLayout().sizeH3().padding(6, 7, 2, 7).add(color).add(bgColor).alignRight().noWrap();
        TextLayout tlTeam = new TextLayout().sizeH3().padding(6, 7, 2, 7).add(color).add(bgColor).alignLeft().noWrap();
        if (!last) {
            tlM = tlM.borderBottom(1).borderColor(P.COLOR_4D);
            tl = tl.borderBottom(1).borderColor(P.COLOR_4D);
            tlTeam = tlTeam.borderBottom(1).borderColor(P.COLOR_4D);
        }

        sb.append("<tr>");

        appendTD(sb, tlTeam, trs.getTeam().getName());
        appendTD(sb, tlM, trs.getPlayedMatches());
        //appendTD(sb, tl, trs.getWonMatches());
        //appendTD(sb, tl, trs.getDrawMatchs());
        //appendTD(sb, tl, trs.getLostMatchs());



        appendTD(sb, tl.clone().colorGrey(), trs.getScoredGoals() - trs.getReceivedGoals());
        appendTD(sb, tl, trs.getPoints());
        sb.append("</tr>");

    }


}
