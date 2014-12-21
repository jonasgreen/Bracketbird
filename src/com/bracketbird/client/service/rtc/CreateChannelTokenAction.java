package com.bracketbird.client.service.rtc;

import com.bracketbird.client.model.keys.TournamentChannelId;
import com.bracketbird.client.service.AbstractAction;
import com.bracketbird.client.service.Action;

/**
 *
 */
public class CreateChannelTokenAction extends AbstractAction implements Action<CreateChannelTokenResult> {
    private static final long serialVersionUID = 4959769561124027412L;

    private String uid;
    private TournamentChannelId channelId;



    public CreateChannelTokenAction() {
    }

    public CreateChannelTokenAction(String uid, TournamentChannelId channelId) {
        this.uid = uid;
        this.channelId = channelId;
    }


    public TournamentChannelId getChannelId() {
        return channelId;
    }

    public void setChannelId(TournamentChannelId channelId) {
        this.channelId = channelId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}