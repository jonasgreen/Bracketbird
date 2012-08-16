package com.bracketbird.server.services;

import com.bracketbird.client.service.*;
import com.bracketbird.server.repository.*;
import com.bracketbird.clientcore.appcontrol.*;

/**
 *
 */
public class FindClubsHandler extends AbstractActionHandler  implements ActionHandler<FindClubsAction, ListClubsResult> {

    private ClubRepository repos = new ClubRepository();

    public ListClubsResult execute(FindClubsAction action) throws ApplicationException {
        return new ListClubsResult(repos.getAll());
    }



    public Class<FindClubsAction> getActionType() {
        return FindClubsAction.class;
    }


}