package com.bracketbird.client.rtc;

import com.bracketbird.client.Printer;
import com.bracketbird.client.rtc.event.REvent;
import com.bracketbird.client.model.keys.TournamentChannelId;
import com.bracketbird.client.service.BBService;
import com.bracketbird.client.service.rtc.CreateChannelTokenResult;
import com.bracketbird.client.service.rtc.GetREventResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

/**
 *
 */
public class FromServer {

    private EventManager eventManager;
    private ClientChannel channel;
    private String clientId;

    public FromServer() {
    }

    public void setReceiver(EventManager seq) {
        this.eventManager = seq;
    }


    public void openChannel(final EventManager sync, final List<REvent> eventLog, final boolean justCreated) {
        TournamentChannelId channelId = RTC.getInstance().getTournament().getTournamentChannelId();
        BBService.createChannelToken(channelId, new AsyncCallback<CreateChannelTokenResult>() {
            @Override
            public void onSuccess(CreateChannelTokenResult result) {
                try {
                    clientId = result.getClientId();
                    channel = new ClientChannel(createChannelHandler());
                    //native javascript call
                    channel.openChannel(result.getToken());
                    sync.start(eventLog, justCreated);
                }
                catch (Exception e) {
                    Printer.printException(e);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Printer.printException(t, "Unable to open channel");
            }
        });
    }

    public void reOpenChannel() {
        TournamentChannelId channelId = RTC.getInstance().getTournament().getTournamentChannelId();
        BBService.createChannelToken(channelId, new AsyncCallback<CreateChannelTokenResult>() {
            @Override
            public void onSuccess(CreateChannelTokenResult result) {
                clientId = result.getClientId();
                channel = new ClientChannel(createChannelHandler());
                //native javascript call
                channel.openChannel(result.getToken());
            }

            @Override
            public void onFailure(Throwable t) {
                Printer.printException(t, "Unable to create channelToken");
            }
        });
    }


    private JSChannelBridgeHandler createChannelHandler() {
        return new JSChannelBridgeHandler() {
            public void messageRecieved(String msg) {
                handleNotificationFromServer(msg);
            }

            public void channelOpened() {
            }

            public void channelClosed() {
            }

            public void errorRecieved() {
                reOpenChannel();
            }
        };
    }

    private void handleNotificationFromServer(String msg) {

        BBService.getRunningTournamentEvent(eventManager.getTournamentId(), Long.valueOf(msg.trim()), new AsyncCallback<GetREventResult>() {
            @Override
            public void onSuccess(GetREventResult result) {
                eventManager.pushedFromServer(result.getEvent());
            }

            @Override
            public void onFailure(Throwable t) {
                Printer.printException(t, "HandleNotificationFromServer:");
            }
        });
    }


    public String getClientId() {
        return clientId;
    }
}
