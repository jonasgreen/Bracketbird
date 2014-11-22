package com.bracketbird.client.gui.rtc;

import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.service.BBService;
import com.bracketbird.client.service.rtc.RTCAction;
import com.bracketbird.client.service.rtc.RTCRequest;
import com.bracketbird.client.service.rtc.RTCResponse;
import com.bracketbird.client.service.rtc.RTCResult;
import com.bracketbird.clientcore.service.CallBack;
import com.google.gwt.user.client.Command;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ToServer {
    private EventManager manager;
    private boolean dead = false;
    private boolean dirtyGui = false;

    //Events waiting to be sent to server.
    private List<REvent> pendingEvents = new ArrayList<REvent>();
    private boolean sending = false;


    public ToServer(EventManager eManager) {
        this.manager = eManager;
    }


    public void add(REvent event) {
        pendingEvents.add(event);
        send();
    }


    private void send() {
        if (pendingEvents.isEmpty() || sending) {
            return;
        }
        sending = true;
        final REvent toSend = pendingEvents.remove(0);
        Command command = new Command() {

            public void execute() {
                try {
                    BBService.updateRunningTournament(createAction(toSend), new CallBack<RTCResult>() {
                        @Override
                        public void success(RTCResult result) {
                            try {
                                if (dead) {
                                    return;
                                }
                                sending = false;
                                handleSucces(toSend, result.getResponse());
                                send();
                            }
                            catch (Exception e) {
                                handleFailure(e);
                            }
                        }

                        @Override
                        public void fail(Throwable t) {
                            if (dead) {
                                return;
                            }
                            sending = false;
                            handleFailure(t);
                        }
                    });
                }
                catch (Exception e) {
                    handleFailure(e);
                }
            }
        };

        com.google.gwt.core.client.Scheduler.get().scheduleDeferred(command);
    }

    private void handleSucces(REvent event, RTCResponse response) {
        if (RTCResponse.State.succeed == response.getState() || RTCResponse.State.merge == response.getState()) {
            event.setEventId(response.getEventId());
            event.setStateId(response.getStateId());
            manager.eventSendToServer(event);
        }
        else {
            dirtyGui = true;
            //wait for server to send the updateResult through channel
        }
    }

    private void handleFailure(Throwable t) {
        RTC.getInstance().handleFailure(t);
    }

    private REvent getLastDone() {
        return manager.getDoneEvents().get(manager.getDoneEvents().size() - 1);
    }

    private RTCAction createAction(REvent event) {
        RTCRequest r = new RTCRequest(event, getLastDone().getEventId(), manager.getTournamentId(), manager.getClientId());
        return new RTCAction(r);
    }


    public boolean isBusy() {
        return !pendingEvents.isEmpty() || sending;
    }

    public void kill() {
        dead = true;
    }

    public boolean isDirtyGui() {
        return dirtyGui;
    }
}
