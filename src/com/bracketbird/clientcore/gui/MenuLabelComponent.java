package com.bracketbird.clientcore.gui;


import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class MenuLabelComponent extends MenuComponent {

    private P backGroundOff = Color.backgroundBase();
    private P backGroundOn = P.BACKGROUND_WHITE;
    private P mouseOver = Color.backgroundBaseDark();
    private P textColor = P.COLOR_WHITE;
    private LabelComponent label;


    public MenuLabelComponent(String text) {
        super();
        label = new LabelComponent(text);
        add(label, new TextLayout(Horizontal.CENTER, Vertical.MIDDLE).sizeSmall().noWrap().padding(0,25,0,25));
        setBackgroundColor(backGroundOff);
        update();
    }

    public void underline() {
        StyleIt.add(this, P.TEXT_DECORATION_UNDERLINE);
    }

    public void mouseOver() {
        setBackgroundColor(mouseOver);
    }

    public void mouseOut() {
        update();
    }

    public LabelComponent getLabel() {
        return label;
    }

    public void setLabel(LabelComponent label) {
        this.label = label;
    }

    public void update() {
        setBackgroundColor(isSelected() ? backGroundOn : backGroundOff);
        StyleIt.add(getLabel(), isSelected() ? Color.textBase() : P.COLOR_WHITE);
    }

    public P getBackGroundOff() {
        return backGroundOff;
    }

    public P getBackGroundOn() {
        return backGroundOn;
    }

    public P getTextColor() {
        return textColor;
    }


   
    
}