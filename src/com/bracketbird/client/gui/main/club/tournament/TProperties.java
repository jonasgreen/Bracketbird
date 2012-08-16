package com.bracketbird.client.gui.main.club.tournament;

import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class TProperties extends VerticalComponent {

    private ImageComponent img;
    private String header;
    private HorizontalComponent headerComp;

    public TProperties(ImageComponent img, String name) {
        super();
        this.img = img;
        this.header =name;
        init();
    }

    public void init(){
        add(getHeaderComp());

    }


    public HorizontalComponent getHeaderComp() {
        if (headerComp == null) {
            headerComp = new HorizontalComponent();
            headerComp.add(img, new TextLayout(Vertical.MIDDLE));
            headerComp.add(new LabelComponent(header + " settings"), new TextLayout(Vertical.MIDDLE).sizeH2().colorBaseDark().paddingLeft(10));
        }
        return headerComp;
    }
}
