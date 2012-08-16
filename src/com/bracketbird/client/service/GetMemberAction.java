package com.bracketbird.client.service;

import com.bracketbird.client.model.keys.*;
import com.bracketbird.clientcore.model.keys.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class GetMemberAction extends AbstractAction implements Action<ListMembersResult>{

    private static final long serialVersionUID = -4867427891745195856L;
    private ClubId clubId;
    private UserId userId;


    public GetMemberAction() {
    }

    public GetMemberAction(ClubId clubId, UserId userId) {
        this.clubId = clubId;
        this.userId = userId;
    }

    public ClubId getClubId() {
        return clubId;
    }

    public void setClubId(ClubId clubId) {
        this.clubId = clubId;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }
}