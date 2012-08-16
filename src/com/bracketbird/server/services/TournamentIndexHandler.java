package com.bracketbird.server.services;


import com.bracketbird.client.model.tournament.*;
import com.bracketbird.client.service.*;
import com.bracketbird.server.repository.tournament.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.service.*;

import java.util.*;

/**
 *
 */
public class TournamentIndexHandler extends AbstractActionHandler  implements ActionHandler<TournamentIndexAction, ListResult<TournamentIndex>> {

    public ListResult<TournamentIndex> execute(TournamentIndexAction action) throws ApplicationException {
        TournamentRepository repos = new TournamentRepository();

        List<TournamentIndex> tis = new ArrayList<TournamentIndex>();
        Collection<Tournament> ts = repos.findBy(FinderFac.findByEntityId(action.getClubId()));
        for (Tournament t : ts) {
            TournamentIndex ti = new TournamentIndex();
            ti.setId(t.getId());
            ti.setName(t.getName());
            tis.add(ti);
        }
        return new ListResult<TournamentIndex>(tis);
    }

    public Class<TournamentIndexAction> getActionType() {
        return TournamentIndexAction.class;
    }


}