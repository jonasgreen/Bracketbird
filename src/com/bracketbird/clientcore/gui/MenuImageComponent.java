package com.bracketbird.clientcore.gui;


import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class MenuImageComponent extends MenuComponent {

    private P backGroundOff = Color.backgroundBase();
    private P backGroundOn = P.BACKGROUND_WHITE;
    private P mouseOver = Color.backgroundBaseDark();
    private ImageComponent imageOn;
    private ImageComponent imageOff;


    public MenuImageComponent(String urlOn, String urlOff) {
        super();
        imageOn = new ImageComponent(urlOn);
        imageOff = new ImageComponent(urlOff);
        add(imageOn, new TextLayout(Horizontal.CENTER, Vertical.MIDDLE).padding(0,25,0,25));
        add(imageOff,new TextLayout(Horizontal.CENTER, Vertical.MIDDLE).padding(0,25,0,25));
        imageOff.setVisible(false);

        setBackgroundColor(backGroundOff);
    }

    public void mouseOver() {
        setBackgroundColor(mouseOver);
    }

    public void mouseOut() {
        update();
    }

    public ImageComponent getImageOn() {
        return imageOn;
    }

    public void setImageOn(ImageComponent imageOn) {
        this.imageOn = imageOn;
    }

    public void update() {
        imageOff.setVisible(!isSelected());
        imageOn.setVisible(isSelected());
        setBackgroundColor(isSelected() ? backGroundOn : backGroundOff);

    }


    public P getBackGroundOff() {
        return backGroundOff;
    }

    public P getBackGroundOn() {
        return backGroundOn;
    }



}