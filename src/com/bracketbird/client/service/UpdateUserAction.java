package com.bracketbird.client.service;

import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.service.*;


/**
 *
 */
public class UpdateUserAction extends AbstractAction implements Action<VoidResult>{
    private static final long serialVersionUID = -694231899388639329L;

    private User user;

    public UpdateUserAction(User user) {
        this.user = user;
    }

    public UpdateUserAction() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}