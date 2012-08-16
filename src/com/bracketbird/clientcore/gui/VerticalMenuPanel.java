package com.bracketbird.clientcore.gui;


import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class VerticalMenuPanel extends AbstractMenuPanel{

    protected TextLayout layoutItem = new TextLayout(null, "100%", Horizontal.LEFT, Vertical.MIDDLE);
    protected TextLayout layout = new TextLayout(null, "100%", Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().paddingLeft(2).paddingRight(2);
    protected TextLayout layoutSectionTitle = new TextLayout(null, "100%", Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().bold().paddingLeft(2).paddingTop(20).colorBase();

    private VerticalComponent panel = new VerticalComponent();

    public VerticalMenuPanel(MenuController controller) {
        super(controller);
    }

    public VerticalMenuPanel() {
        this(null);
    }

    public void addSectionTitle(String title) {
        panel.add(new LabelComponent(title), layoutSectionTitle);
    }


    protected void add(MenuComponent mc) {
        panel.add(mc, layoutItem);
        StyleIt.add(((MenuLinkComponent)mc).getLabel(), layout);
    }

    public VerticalComponent getPanel() {
        return panel;
    }


}