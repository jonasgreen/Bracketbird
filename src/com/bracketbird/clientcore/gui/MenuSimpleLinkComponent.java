package com.bracketbird.clientcore.gui;


import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class MenuSimpleLinkComponent extends MenuComponent {

    private LabelComponent label;
    private String text;

    private TextLayout notSelectedLayout = new TextLayout().add(P.COLOR_DARK_GREY).add(P.FONT_WEIGHT_NORMAL).underline();
    private TextLayout selectedLayout = new TextLayout().colorBaseDark().bold().add(P.TEXT_DECORATION_NON);

    public MenuSimpleLinkComponent(String text) {
        super();
        this.text = text;
        getLabel().setText(text);
        add(getLabel());
    }


    public LabelComponent getLabel() {
        if (label == null) {
            label = new LabelComponent("");
        }
        return label;
    }



    public void mouseOver() {
        if(isSelected()){
            //ignore
        }
        else{
            this.getElement().getStyle().setProperty("cursor", "pointer");

        }

    }

    public void mouseOut() {
        update();
    }

    public void setLabel(LabelComponent label) {
        this.label = label;
    }

    public void update() {
        if(isSelected()){
            getLabel().setText("| "+text+" |");            
            StyleIt.add(label, selectedLayout);
        }
        else{
            getLabel().setText(text);
            StyleIt.add(label, notSelectedLayout);

        }
    }






}