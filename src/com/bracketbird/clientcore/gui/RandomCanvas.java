package com.bracketbird.clientcore.gui;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.Style;

/**
 *
 */
public class RandomCanvas extends FlowComponent {

    private static int count = 0;
    private static String id = "canvasId";

    private Canvas canvas;

    public RandomCanvas(int height, int width) {
        canvas = Canvas.createIfSupported();
        add(canvas);
        canvas.setWidth(width + "px");
        canvas.setHeight(height + "px");
    }


    public void load(){
        String id1 = id + (count++);
        this.canvas.getElement().setId(id1);
        load(id1, "red");
    }


    //ensures that gwt module is loaded before exported api is called from java-script.
    private native void load(String id, String color)
    /*-{
          $wnd.canvasApp(id, color);
      }-*/;



}
