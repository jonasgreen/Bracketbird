package com.bracketbird.clientcore.gui;

import com.google.gwt.user.client.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class ProgressBar extends DialogComponent{
    private SimplePanelComponent[] comps = new SimplePanelComponent[15];
    private int index = 0;
    private  Timer t;

    public ProgressBar() {
        super(false, true, Response.CANCEL);
        init();
    }


    public void init() {
        
        HorizontalComponent hc = new HorizontalComponent();
        int i = 0;
        Layout17 l = new Layout17(0,0,0,0, "30px", "15px");
        for (SimplePanelComponent comp : comps) {
            SimplePanelComponent spc = new SimplePanelComponent();
            spc.setBackgroundColor(P.BACKGROUND_GREY_YELLOW);
            hc.add(spc, l);
            comps[i++] = spc;
        }
        super.add(hc, new Layout17(0,0,0,0, "20px", "100px").add(P.BORDER_STYLE_SOLID).add(P.BORDER_WIDTH_1px));
    }

    protected void buttonClicked(Response r) {
        hide();
        if (callBack != null) {
            callBack.onClose(r);
        }
    }

    public void show(DialogCallBack callBack) {
        index = 0;
        super.show(callBack);
        t = new Timer() {
            @Override
            public void run() {


                if(index == comps.length){
                    comps[index-1].setBackgroundColor(P.BACKGROUND_GREY_YELLOW);
                    index = 0;
                }

                if(index != 0){
                    comps[index-1].setBackgroundColor(P.BACKGROUND_GREY_YELLOW);
                }
                comps[index].setBackgroundColor(P.BACKGROUND_PURPLE);
                
                index++;

            }
        };
        t.scheduleRepeating(300);
    }

    public void stop(){
        if(t != null){
            t.cancel();
        }
        hide();
    }


}
