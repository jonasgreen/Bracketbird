package com.bracketbird.client.gui.rtc;


import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class RunningTournamentPage extends Page<RunningTournamentPageController> {

    private DockLayoutPanel p = new DockLayoutPanel(Style.Unit.PX);
    private DockLayoutPanel pContent;
    private SimplePanelComponent content = new SimplePanelComponent();
    private CustomScrollPanelComponent scrollPanel;
    private Page activePage;
    private Map<String, Integer> scrollPositions = new HashMap<String, Integer>();
            

    public RunningTournamentPage() {
        super();
    }

    public void init() {
        initWidget(p);
        DOM.getElementById("logodiv").setClassName("bottomImage");
        addContent(RunningTournamentTop.getInstance(), 75);
    }

    private void addContent(Widget top, int size){
        if(pContent != null){
            pContent.removeFromParent();
        }
        pContent = new DockLayoutPanel(Style.Unit.PX);
        pContent.addNorth(top, size);




        StyleIt.add(top, new TextLayout(null, "100%"));

        if(getScrollPanel().getParent() != null){
            getScrollPanel().removeFromParent();
        }
        pContent.add(getScrollPanel());
        StyleIt.add(getScrollPanel(), new TextLayout("100%", "100%"));

        p.add(pContent);


    }

    public CustomScrollPanelComponent getScrollPanel() {
        if (scrollPanel == null) {
            scrollPanel = new CustomScrollPanelComponent(content);
        }
        return scrollPanel;
    }

    protected void setSubPageHolder(Page subPage) {
        if(activePage != null){
            int pos = getScrollPanel().getScrollPanel().getVerticalScrollPosition();
            scrollPositions.put(activePage.getController().getHistoryName(), pos);
        }

        content.add(subPage, new TextLayout(null, "100%"));

        Integer pos = scrollPositions.get(subPage.getController().getHistoryName());
        if(pos != null){
            getScrollPanel().getScrollPanel().setVerticalScrollPosition(pos);
        }

        activePage = subPage;
        
    }


    public void ejectOut() {
        addContent(RunningTournamentTop.getInstance(), 75);
    }
}