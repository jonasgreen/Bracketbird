package com.bracketbird.server.services;


import com.bracketbird.server.repository.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class GetAllHandler extends AbstractActionHandler implements ActionHandler<GetAllAction, ListResult> {

    public ListResult execute(GetAllAction action) throws ApplicationException {
        Repository r = ActionHandlerRegistry.getRepos(action.getFindIn());
        return new ListResult(r.getAll());
    }


    public Class<GetAllAction> getActionType() {
        return GetAllAction.class;
    }


}