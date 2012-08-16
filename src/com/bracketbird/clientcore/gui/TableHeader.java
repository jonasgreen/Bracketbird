package com.bracketbird.clientcore.gui;

import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class TableHeader {


    public static TextLayout HEADER_LAYOUT = new TextLayout().sizeSmall().colorBaseDark().italic().borderBottom(1).borderColor(P.BACKGROUND_C1);


    private String name;
    private String width;
    private int paddingLeft;

    public TableHeader(String name, String width, int paddingLeft) {
        this.name = name;
        this.width = width;
        this.paddingLeft = paddingLeft;
    }

    public String getWidth() {
        return width;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void createColumn(HorizontalComponent parent, GuiComponent gc, TextLayout l, TableHeader t) {
        if (t.getPaddingLeft() != 0) {
            LabelComponent la = new LabelComponent("");
            parent.add(la, l);
            la.setWidth(t.getPaddingLeft() + "px");
            la.align(null, Vertical.BOTTOM, 0,0,0,0);
            
        }
        parent.add(gc, l);
        gc.setWidth(t.getWidth());


    }


    public static HorizontalComponent createHeaderPanel(TextLayout l, TableHeader... headers) {
        HorizontalComponent headerPanel = new HorizontalComponent();
        for (TableHeader h : headers) {
            setHeader(headerPanel, l, h);
        }

        return headerPanel;
    }

    private static void setHeader(HorizontalComponent parent, TextLayout l, TableHeader header) {
        if (header.getPaddingLeft() != 0) {
            LabelComponent la = new LabelComponent("");
            parent.add(la, l);
            la.setWidth(header.getPaddingLeft() + "px");
            la.align(null, Vertical.BOTTOM, 0,0,0,0);
        }

        LabelComponent label = new LabelComponent(header.getName());
        parent.add(label, l);
        label.setWidth(header.getWidth());
    }
}
