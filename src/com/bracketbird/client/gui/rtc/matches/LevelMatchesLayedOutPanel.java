package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.model.tournament.Round;
import com.bracketbird.client.model.tournament.TournamentLevel;
import com.bracketbird.client.table.TableManager;
import com.bracketbird.clientcore.gui.HorizontalComponent;
import com.bracketbird.clientcore.gui.LabelComponent;
import com.bracketbird.clientcore.gui.VerticalComponent;
import com.bracketbird.clientcore.style.TextLayout;

/**
 *
 */
public class LevelMatchesLayedOutPanel extends VerticalComponent {

    private TournamentLevel level;
    public LevelMatchesLayedOutPanel(TournamentLevel tl) {
        super();
        this.level = tl;
        init();
    }

    private void init() {
        TableManager tm = new TableManager() {
            @Override
            public void scrollUp() {
              //  ScrollPanel scrollPanel = TournamentPageController.getInstance().getPage().getScrollPanel().getScrollPanel();
              //  scrollPanel.setVerticalScrollPosition(scrollPanel.getVerticalScrollPosition() - 40);
            }

            @Override
            public void scrollDown() {
               // ScrollPanel scrollPanel = TournamentPageController.getInstance().getPage().getScrollPanel().getScrollPanel();
               //      scrollPanel.setVerticalScrollPosition(scrollPanel.getVerticalScrollPosition() + 40);
            }
        };

        //create headers

        boolean withHeader = true;
        for (Round round : level.getRounds()) {
            if(!withHeader){
                HorizontalComponent hc = new HorizontalComponent();
                hc.add(new LabelComponent(round.getName()), new TextLayout().sizeSmall().bold().colorBaseDark().italic().noWrap());
                //hc.add(new FlowComponent(), new TextLayout(14,0,0,10,null,  "100%").borderBottom(1).borderColor(P.BACKGROUND_C1));
                add(hc, new TextLayout(0, 0, 0, 0, null, "100%"));
            }
            RoundTable table = new RoundTable(tm, round, withHeader);
            add(table, new TextLayout(10, 0, 10, 0, null, "100%"));
            withHeader = false;
        }
    }


}
