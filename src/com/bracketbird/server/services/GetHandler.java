package com.bracketbird.server.services;


import com.bracketbird.server.repository.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class GetHandler extends AbstractActionHandler  implements ActionHandler<GetAction, SingleResult> {

    public SingleResult execute(GetAction action) throws ApplicationException {
        Repository r = ActionHandlerRegistry.getRepos(action.getFindIn());
        return new SingleResult(r.get(action.getIndexKey()));
    }


    public Class<GetAction> getActionType() {
        return GetAction.class;
    }


}