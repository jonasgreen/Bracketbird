package com.bracketbird.server.services;


import com.bracketbird.server.repository.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class UpdateHandler extends AbstractActionHandler implements ActionHandler<UpdateAction, SingleResult> {

	
    public SingleResult execute(UpdateAction action) throws ApplicationException {
        Repository r = ActionHandlerRegistry.getRepos(action.getFindIn());
        return new SingleResult<Model>(r.update(action.getModel()));
    }


    public Class<UpdateAction> getActionType() {
        return UpdateAction.class;
    }


}