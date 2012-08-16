package com.bracketbird.client.service;

import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class CreateClubAction extends AbstractAction implements Action<ClubResult>{
    private static final long serialVersionUID = -7220084907652453742L;

    private CreateClub createClub;


    public CreateClubAction() {
    }

    public CreateClubAction(CreateClub createClub) {
        this.createClub = createClub;
    }

    public CreateClub getCreateClub() {
        return createClub;
    }

    public void setCreateClub(CreateClub createClub) {
        this.createClub = createClub;
    }
}