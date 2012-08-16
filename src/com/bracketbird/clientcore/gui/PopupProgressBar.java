package com.bracketbird.clientcore.gui;

import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class PopupProgressBar extends PopupPanel {

    private SmallProgressBar bar = new SmallProgressBar(P.BACKGROUND_DARK_BLUE);

    Timer t;

    public PopupProgressBar() {
        super();
        this.add(bar);
        StyleIt.add(this, P.BACKGROUND_DARK_BLUE);
        StyleIt.add(this, Name.BORDER, "NONE");
    }


    public void start(String text) {
        StyleIt.add(this, P.BACKGROUND_DARK_BLUE);
        bar.start(text == null ? "Arbejder" : text);
        t = new Timer() {
            @Override
            public void run() {
                setPopupPositionAndShow(new PopupPanel.PositionCallback() {
                    public void setPosition(int offsetWidth, int offsetHeight) {
                        if (PopupManager.isShowing()) {
                            setPopupPosition((Window.getClientWidth() + Window.getScrollLeft() - offsetWidth) / 2, (PopupManager.getClientOffsetHeight()+1));

                        }
                        else {
                            setPopupPosition(250, Window.getScrollTop());
                        }
                    }
                });
            }
        };
        t.scheduleRepeating(50);

    }

    public void stop() {
        StyleIt.add(this, P.BACKGROUND_WHITE);
        bar.stop();
        if (t != null) {
            t.cancel();
        }
        this.setVisible(false);
        hide();
    }

    

}
