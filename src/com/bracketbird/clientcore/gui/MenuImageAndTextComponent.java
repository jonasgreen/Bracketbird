package com.bracketbird.clientcore.gui;


import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class MenuImageAndTextComponent extends MenuComponent {


    private P backGroundOff = P.BACKGROUND_NONE;
    private P backGroundOn = P.BACKGROUND_WHITE;
    private P mouseOver = new P(Name.BACKGROUND,"rgb(190,190,190)");//Color.backgroundCompl();
    private ImageComponent image;
    private LabelComponent label;
    private VerticalComponent content;



    public MenuImageAndTextComponent(String urlOn, String label) {
        this(null, urlOn, label);
    }

    public MenuImageAndTextComponent(P backGroundOffColor, String urlOn, String label) {
        super();
        if(backGroundOffColor != null){
            this.backGroundOff = backGroundOffColor;
        }
        image = new ImageComponent(urlOn);
        getLabel().setText(label);
        add(getContent());
        setTitle(label);
        setBackgroundColor(backGroundOff);

    }


    public VerticalComponent getContent() {
        if (content == null) {
            content = new VerticalComponent();
            //content.add(getLabel(), new TextLayout(0, 0,0,0,Horizontal.CENTER).sizeEkstraEkstraSmall().paddingTop(2));
            content.add(image, new TextLayout("60px","60px", Horizontal.CENTER, Vertical.MIDDLE).margin(5,0,0,0));
        }
        return content;
    }


    public LabelComponent getLabel() {
        if (label == null) {
            label = new LabelComponent("");
        }
        return label;
    }


    public void mouseOver() {
        setBackgroundColor(mouseOver);
    }

    public void mouseOut() {
        update();
    }


    public void update() {
        setBackgroundColor(isSelected() ? backGroundOn : backGroundOff);
        if(isSelected()){

        }
    }

    public ImageComponent getImage() {
        return image;
    }


    public void setBackGroundOn(P backGroundOn) {
        this.backGroundOn = backGroundOn;
    }
}