package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.gui.rtc.EventManager;
import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.Warning;
import com.bracketbird.clientcore.gui.OnClose;
import com.bracketbird.clientcore.gui.PopupManager;

/**
 *
 */
public abstract class REventHandler<E extends REvent<?,?>> {


    protected REventHandler() {
    }

    public void handleEvent(final E event) {
        if (event.isFromClient() && shouldWarn(event)) {
            final Warning w = new Warning(getWarning());
            PopupManager.show(w, new OnClose() {
                public void onClose() {
                    if (w.isProceed()) {
                        executeEvent(event);
                    }
                }
            });
        }
        else {
            executeEvent(event);
        }
    }

    public void postServerAction(E event){

    }

    protected void executeEvent(E event) {
        try {
            if (event.isFromClient()) {
                EventManager manager = RTC.getInstance().getSync();
                manager.add(event);
                updateTournament(event);
            }
            else {
                updateTournament(event);
            }
        }
        catch (Exception e) {
            handleFailure(e, event);
        }
    }




    private void handleFailure(Throwable t, E event) {
        if (t != null) {
            t.printStackTrace();
        }

    }


    protected abstract boolean shouldWarn(E event);

    protected abstract String getWarning();

    protected abstract void updateTournament(E event);



}
