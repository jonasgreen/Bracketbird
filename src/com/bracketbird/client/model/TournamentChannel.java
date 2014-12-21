package com.bracketbird.client.model;


import com.bracketbird.client.model.keys.TournamentChannelId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  //TODO should not be a model
 */
public class TournamentChannel extends Model<TournamentChannelId> implements Serializable {

    private static final long serialVersionUID = 1077580453214630520L;

    private List<String> clients = new ArrayList<String>();

    public TournamentChannel() {
        super();
    }

    public List<String> getClients() {
        return clients;
    }

    public void setClients(List<String> clients) {
        this.clients = clients;
    }

}