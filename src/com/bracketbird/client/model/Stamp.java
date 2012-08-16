package com.bracketbird.client.model;


import com.bracketbird.clientcore.model.keys.*;

import java.io.*;

/**
 *
 */
public class Stamp implements Serializable {
    private static final long serialVersionUID = -6283847183759368097L;

    private UserId userId;

    public Stamp(){

    }

    public Stamp(User user){
        this.userId = user.getId();
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }
}
