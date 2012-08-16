package com.bracketbird.client.gui.rtc;

import com.bracketbird.client.gui.clientchannel.ClientChannel;
import com.bracketbird.client.gui.clientchannel.JSChannelBridgeHandler;
import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.gui.rtc.health.LogPageController;
import com.bracketbird.client.model.keys.TournamentChannelId;
import com.bracketbird.client.service.BBService;
import com.bracketbird.client.service.rtc.CreateChannelTokenResult;
import com.bracketbird.client.service.rtc.GetREventResult;
import com.bracketbird.clientcore.service.CallBack;

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
        BBService.createChannelToken(channelId, new CallBack<CreateChannelTokenResult>() {
            @Override
            public void success(CreateChannelTokenResult result) {
                try {
                    LogPageController.getInstance().log("Server channel opened.");
                    clientId = result.getClientId();
                    channel = new ClientChannel(createChannelHandler());
                    //native javascript call
                    channel.openChannel(result.getToken());
                    sync.start(eventLog, justCreated);
                }
                catch (Exception e) {
                    LogPageController.getInstance().log("", e);
                }
            }

            @Override
            public void fail(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void reOpenChannel() {
        TournamentChannelId channelId = RTC.getInstance().getTournament().getTournamentChannelId();
        BBService.createChannelToken(channelId, new CallBack<CreateChannelTokenResult>() {
            @Override
            public void success(CreateChannelTokenResult result) {
                LogPageController.getInstance().log("Reopening server channel.");
                clientId = result.getClientId();
                channel = new ClientChannel(createChannelHandler());
                //native javascript call
                channel.openChannel(result.getToken());
            }

            @Override
            public void fail(Throwable t) {
                t.printStackTrace();
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

        BBService.getRunningTournamentEvent(eventManager.getTournamentId(), Long.valueOf(msg.trim()), new CallBack<GetREventResult>() {
            @Override
            public void success(GetREventResult result) {
                eventManager.pushedFromServer(result.getEvent());
            }

            @Override
            public void fail(Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public String getClientId() {
        return clientId;
    }
}
