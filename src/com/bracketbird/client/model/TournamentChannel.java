package com.bracketbird.client.model;


import com.bracketbird.client.model.keys.*;
import com.bracketbird.clientcore.model.*;

import java.io.*;
import java.util.*;

/**
 *
 */
public class TournamentChannel extends Model<TournamentChannelId> implements Serializable {

    private static final long serialVersionUID = 1077580453214630520L;

    private List<String> clients = new ArrayList<String>();

    private Date createdDate;

    private Date lastChangeDate;

    public TournamentChannel() {
        super();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    public List<String> getClients() {
        return clients;
    }

    public void setClients(List<String> clients) {
        this.clients = clients;
    }
}