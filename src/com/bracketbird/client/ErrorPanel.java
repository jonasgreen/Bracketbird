package com.bracketbird.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class ErrorPanel extends FlowPanel{


    public ErrorPanel() {
    }


    public void addError(String errorMsg){
        add(new Label(errorMsg));
        addStyleName("errorPanel");
    }

    @Override
    public void clear(){
        super.clear();
        removeStyleName("errorPanel");
    }
}
