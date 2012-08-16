package com.bracketbird.clientcore.gui;


import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class VerticalSubMenuPanel extends VerticalMenuPanel{

     private TextLayout layout = new TextLayout(null, "100%", Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().bold().paddingLeft(6).colorBaseDark().sizeEkstraSmall();
     private TextLayout layoutSectionTitle = new TextLayout(null, "100%", Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().bold().paddingLeft(2).paddingTop(20).colorBase();

    
    private VerticalComponent panel = new VerticalComponent();

    public VerticalSubMenuPanel(MenuController controller) {
        super(controller);

    }

    public VerticalSubMenuPanel() {
        this(null);
    }

    public void addSectionTitle(String title) {
        panel.add(new LabelComponent(title), layoutSectionTitle);
    }


    protected void add(MenuComponent mc) {
        panel.add(mc, layout);
        StyleIt.add(((MenuLinkComponent)mc).getLabel(), layout);
    }

    public VerticalComponent getPanel() {
        return panel;
    }


}