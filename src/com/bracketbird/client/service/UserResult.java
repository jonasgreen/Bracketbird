package com.bracketbird.client.service;


import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class UserResult implements Result {
    private static final long serialVersionUID = 2536342536769646854L;

    private User user;

    public UserResult() {
    }

    public UserResult(User user) {
        this.user = user;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
