package com.bracketbird.client.rtc.event;


import com.google.gwt.event.shared.HandlerRegistration;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class OnChangeHandlerList {


    public OnChangeHandlerList() {
    }

    private List<OnChangeHandler> handlers = new ArrayList<OnChangeHandler>();

    public HandlerRegistration addHandler(final OnChangeHandler handler){
        handlers.add(handler);

        return new HandlerRegistration() {
            @Override
            public void removeHandler() {
                handlers.remove(handler);
            }
        };
    }

    public void fireEvent(){
        //createGroupMatch new list to avoid concurrent modification exception
        for (OnChangeHandler l : new ArrayList<OnChangeHandler>(handlers)) {
            l.onChange();
        }
    }



}
