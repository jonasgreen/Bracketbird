package com.bracketbird.client.rtc.event;


import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class REventManager {

    private class Listener {
        private REvent<?, ?> type;
        private REventListener eventListener;

        private Listener(REventListener eventListener, REvent<?, ?> type) {
            this.type = type;
            this.eventListener = eventListener;
        }
    }

    private List<Listener> listeners = new ArrayList<Listener>();


    public void addListener(REventListener rl, REvent<?, ?> ... type) {
        for (REvent<?, ?> t : type) {
            listeners.add(new Listener(rl, t));
        }
    }

    public void fireEvents(REvent<?, ?> event) {
        List<Listener> ls = new ArrayList<Listener>(listeners);
        for (Listener l : ls) {
            if (l.type.getHandler() == event.getHandler()) {
                l.eventListener.onChange(event);
            }
        }
    }


}
