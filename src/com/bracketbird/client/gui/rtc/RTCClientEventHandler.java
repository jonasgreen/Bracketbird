package com.bracketbird.client.gui.rtc;

import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public abstract class RTCClientEventHandler {

    protected boolean fromClient;

    protected RTCClientEventHandler(boolean fromClient) {
        this.fromClient = fromClient;
    }

    public void handleEvent() {
        if (fromClient && shouldWarn()) {
            final Warning w = new Warning(getWarning());
            PopupManager.show(w, new OnClose() {
                public void onClose() {
                    if (w.isProceed()) {
                        executeEvent();
                    }
                }
            });
        }
        else {
            executeEvent();
        }
    }

    protected void executeEvent() {
        try {
            if (fromClient) {

                EventManager manager = RTC.getInstance().getSync();
               // queue.add(createEventToServer());
                updateTournament();
            }
            else {
                updateTournament();
            }
        }
        catch (Exception e) {
            RTC.getInstance().handleFailure(e);
        }
    }


    protected abstract boolean shouldWarn();

    protected abstract String getWarning();


    protected abstract void updateTournament();


}
