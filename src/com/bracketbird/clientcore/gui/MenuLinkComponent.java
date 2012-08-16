package com.bracketbird.clientcore.gui;


import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class MenuLinkComponent extends MenuComponent {

    private LabelComponent label;


    public MenuLinkComponent(String text) {
        super();
        label = new LabelComponent(text);
        add(label);
        //setBackgroundColor(backGroundOff);
    }



    public void mouseOver() {
        StyleIt.add(this, Color.backgroundCompl());
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
        if(isSelected()){
            StyleIt.add(this, Color.backgroundBase());
            StyleIt.add(label, P.COLOR_WHITE);
        }
        else{
            StyleIt.add(this, P.BACKGROUND_WHITE);
            StyleIt.add(label, Color.textBase());

        }
    }






}