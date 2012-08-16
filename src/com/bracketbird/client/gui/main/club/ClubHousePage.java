package com.bracketbird.client.gui.main.club;


import com.bracketbird.client.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class ClubHousePage extends Page<ClubHousePageController> {

    private VerticalComponent content;
    private Page activePage = null;
    private SimplePanelComponent pageHolder;

    public ClubHousePage() {
        super();
        content = new VerticalComponent();
        initWidget(content);
    }

    public void init() {
        //content.add(getHeader(), new TextLayout(MainAppFrame.MENU_HEIGHT + "px", "100%"));
        content.add(getPageHolder(), new TextLayout("100%", ApplicationController.APP_WIDTH, Horizontal.CENTER, null));
        
    }

    public SimplePanelComponent getPageHolder() {
        if (pageHolder == null) {
            pageHolder = new SimplePanelComponent();
        }
        return pageHolder;
    }

    protected void setSubPageHolder(Page subPage) {
        if (activePage != null) {
            activePage.removeFromParent();
        }
        activePage = subPage;
        getPageHolder().add(activePage, new TextLayout("100%", "100%"));
    }

    public GuiComponent getHeader() {
        HorizontalComponent header = new HorizontalComponent();
        //header.add(new MenuPanel().getPanel(), new TextLayout(MainAppFrame.MENU_HEIGHT + "px", MainAppFrame.PAGE_WIDTH + "px", Horizontal.CENTER));
        header.setStyleName("menu");
        return header;
    }


    public VerticalComponent getContent() {
        return content;
    }
}