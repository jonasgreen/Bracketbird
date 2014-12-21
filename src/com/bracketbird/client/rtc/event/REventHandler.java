package com.bracketbird.client.rtc.event;

import com.bracketbird.client.rtc.EventManager;
import com.bracketbird.client.rtc.Handler;
import com.bracketbird.client.rtc.ProceedOrCancelWarning;
import com.bracketbird.client.rtc.RTC;

/**
 *
 */
public abstract class REventHandler<E extends REvent<?,?>> {


    protected REventHandler() {
    }

    public void handleEvent(final E event) {
        if (event.isFromClient() && shouldWarn(event)) {
            final ProceedOrCancelWarning pop = new ProceedOrCancelWarning(getWarning());
            pop.addOkHandler(new Handler() {
                @Override
                public void handle() {
                    executeEvent(event);
                }
            });
            pop.center();
        }
        else {
            executeEvent(event);
        }
    }

    public void postServerAction(E event){

    }

    protected void executeEvent(E event) {
        try {
            //System.out.println("Before ----- : "+event.getClass().getSimpleName());

            if (event.isFromClient()) {
                EventManager manager = RTC.getInstance().getSync();
                manager.add(event);
                updateTournament(event);
            }
            else {
                updateTournament(event);
            }

           //System.out.println("After ------: "+event.getClass().getSimpleName());
           //PrintStateTree printStateTree = new PrintStateTree();
           //printStateTree.print(RTC.getInstance().getTournament());

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
