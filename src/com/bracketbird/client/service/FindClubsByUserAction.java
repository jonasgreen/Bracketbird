package com.bracketbird.client.service;

import com.bracketbird.clientcore.model.keys.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class FindClubsByUserAction extends AbstractAction implements Action<ListClubsResult>{
    private static final long serialVersionUID = 7689753245530572136L;

    private UserId userId;

    public FindClubsByUserAction() {
    }

    public FindClubsByUserAction(UserId userId) {
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }
}