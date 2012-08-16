package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.Timer;
import com.bracketbird.clientcore.util.FocusManager;
import com.bracketbird.clientcore.util.FocusComponentIntfc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SuggestBoxComponent extends GuiComponent implements FocusComponentIntfc, KeyDownHandler {
    private SuggestBox suggestBox;
    private List<KeyDownHandler> keyDownHandlers = new ArrayList<KeyDownHandler>();
    private FocusManager fManager;


    public SuggestBoxComponent(SuggestOracle or) {
        super();
        suggestBox = new SuggestBox(or);
        initWidget(suggestBox);
        addDomHandler(this, KeyDownEvent.getType());
    }


    public SuggestBox getSuggestBox() {
        return suggestBox;
    }


    public void onKeyDown(KeyDownEvent event) {
        int keycode = event.getNativeEvent().getKeyCode();
        if (suggestBox.isSuggestionListShowing()) {

            if (keycode == 27) {//ESC
                getSuggestBox().hideSuggestionList();
            }
            else if (keycode == 38) {//arrow up
                final String s = getSuggestBox().getText();
                if (s == null || s.equals("")) {
                    //ignore
                }
                else {
                    Timer t = new Timer() {
                        @Override
                        public void run() {
                            getSuggestBox().getTextBox().setCursorPos(s.length());
                        }
                    };
                    t.schedule(50);
                }
            }
            return;
        }

        //has to be called first
        for (KeyDownHandler keyDownHandler : keyDownHandlers) {
            keyDownHandler.onKeyDown(event);
        }

        if (fManager != null) {
            fManager.onKeyDown(new FocusKeyDownEvent(this, event.getNativeKeyCode()));
        }



    }

    public void addKeyDownHandler(KeyDownHandler handler) {
        keyDownHandlers.add(handler);
    }

    public void setFocus(boolean focus) {
        if (focus) {
            if (fManager != null) {
                fManager.setLastFocus(this);
            }

        }
        getSuggestBox().setVisible(focus);
        getSuggestBox().setFocus(focus);
    }

    public void doFocus(FocusKeyDownEvent e) {
        setFocus(true);
        if(fManager != null && e != null){
            fManager.setCursorPosition(this, getSuggestBox().getTextBox(), e);
        }
    }

    public void focusLost() {
        setFocus(false);
    }


    public boolean isLeavable(int keyCode) {
        return fManager != null && fManager.isLeavable(getSuggestBox(), keyCode);
    }

    public void addParent(FocusManager fm) {
        this.fManager = fm;

    }


}
