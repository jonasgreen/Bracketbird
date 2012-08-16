package com.bracketbird.client.gui.rtc;


import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class RTCLayoutFac2 {

    public static TextLayout CONTENT = new TextLayout(null, "100%").padding(20).paddingTop(20).paddingRight(20);
    

    public static TextLayout h1(){
        return new TextLayout(Vertical.MIDDLE).colorBaseDark().sizeH1().margin(0,0,10,0);
    }

    public static TextLayout h2(){
        return new TextLayout(Vertical.MIDDLE).colorBaseDark().sizeH2();
    }


    public static TextLayout h3(){
        return new TextLayout(Vertical.MIDDLE).colorBaseDark().sizeH3();
    }

    public static TextLayout normal(){
        return new TextLayout(Vertical.MIDDLE).colorBaseDark().sizeNormal();
    }
}
