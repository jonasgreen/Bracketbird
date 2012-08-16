package com.bracketbird.clientcore.gui;


import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public abstract class SubPageHolder<C extends PageController> extends Page<C> {

    private VerticalComponent content;
    private HorizontalComponent page;
    private Page activeSubPage = null;
    private VerticalComponent leftSide;

    public SubPageHolder() {
        super();
        content = new VerticalComponent();
        initWidget(content);
    }

    public void init() {
        /*GuiComponent header = getHeader();
        if (header != null) {
            content.add(header, new TextLayout(MainAppFrame.MENU_HEIGHT + "px", "100%"));
        }
        */
        content.add(getPage(), new TextLayout(15, 0, 0, 0, "100%", null, Horizontal.CENTER, null).paddingBottom(20));
    }

    public abstract GuiComponent getHeader();



    public void addSection(String title, GuiComponent vmp) {

        VerticalComponent section = new VerticalComponent();
        if (title != null) {
            TextLayout tl = new TextLayout(Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().colorBaseDark().bold().padding(2);
            section.add(new LabelComponent(title), tl);
        }

        if (vmp != null) {
            TextLayout tl2 = new TextLayout(null, "100%", Horizontal.LEFT, Vertical.MIDDLE);
            section.add(vmp, tl2);
        }

        int top = title == null ? 5 : 20;

        getLeftSide().add(section, new TextLayout(top, 0, 5, 0, null, "100%"));
    }

    public VerticalComponent getLeftSide() {
        if (leftSide == null) {
            leftSide = new VerticalComponent();
        }
        return leftSide;
    }


    public HorizontalComponent getPage() {
        if (page == null) {
            page = new HorizontalComponent();
            SimplePanelComponent sp = new SimplePanelComponent();
            sp.add(getLeftSide(), new TextLayout(null, "180px"));
            page.add(sp, new TextLayout("100%", null, Horizontal.LEFT).borderColor(Color.textBase()));
        }
        return page;
    }


    protected void setSubPageHolder(Page subPage) {
        if (activeSubPage != null) {
            activeSubPage.removeFromParent();
        }
        activeSubPage = subPage;
        getPage().add(activeSubPage, activeSubPage.getLayout());
    }

    public Page getActiveSubPage() {
        return activeSubPage;
    }
}