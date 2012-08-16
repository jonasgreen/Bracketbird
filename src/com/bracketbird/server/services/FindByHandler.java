package com.bracketbird.server.services;


import com.bracketbird.server.repository.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class FindByHandler extends AbstractActionHandler  implements ActionHandler<FindByAction, ListResult> {

    public ListResult execute(FindByAction action) throws ApplicationException {
        Repository r = ActionHandlerRegistry.getRepos(action.getFindIn());
        return new ListResult(r.findBy(action.getFinder()));
    }


    public Class<FindByAction> getActionType() {
        return FindByAction.class;
    }


}