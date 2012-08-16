package com.bracketbird.clientcore.gui;

import com.google.gwt.user.client.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class SmallProgressBar extends HorizontalComponent {
    private SimplePanelComponent[] comps = new SimplePanelComponent[14];
    private int index = 0;
    private Timer t;
    private LabelComponent info;
    private P backgroundColor = P.BACKGROUND_WHITE;

    public SmallProgressBar() {
        super();
        init();
    }

    public SmallProgressBar(P backColor) {
        super();
        this.backgroundColor = backColor;
        init();
    }


    public void init() {
        add(getInfo(), new TextLayout(Vertical.MIDDLE).sizeSmall().colorWhite());
        HorizontalComponent hc = new HorizontalComponent();
        int i = 0;
        Layout17 l = new Layout17("15px", "5px", Vertical.MIDDLE);
        for (SimplePanelComponent comp : comps) {
            SimplePanelComponent spc = new SimplePanelComponent();
            hc.add(spc, l);
            comps[i++] = spc;
        }
        add(hc, new Layout17(0, 0, 0, 2, "20px", "210px", Vertical.MIDDLE));
    }

    public LabelComponent getInfo() {
        if (info == null) {
            info = new LabelComponent("");
        }
        return info;
    }

    public void setText(String text) {
        getInfo().setText(text);
    }

    public void start(String text) {
        //restting from last run
        for (SimplePanelComponent comp : comps) {
            comp.setBackgroundColor(backgroundColor);
        }
        index = 0;

        getInfo().setText(text == null ? "" : text);
        this.setVisible(true);
        t = new Timer() {
            @Override
            public void run() {

                if (index == comps.length) {
                    comps[index - 1].setBackgroundColor(backgroundColor);
                    index = 0;
                }

                if (index != 0) {
                    comps[index - 1].setBackgroundColor(backgroundColor);
                }
                comps[index].setBackgroundColor(P.BACKGROUND_WHITE);

                index++;

            }
        };
        t.scheduleRepeating(150);
    }

    public void stop() {        
        if (t != null) {
            t.cancel();
        }
        getInfo().setText("");
        this.setVisible(false);

    }


}