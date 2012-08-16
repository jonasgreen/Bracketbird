package com.bracketbird.server.services;


import com.bracketbird.server.repository.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class DeleteUserHandler extends AbstractActionHandler implements ActionHandler<DeleteUserAction, VoidResult> {


    public VoidResult execute(DeleteUserAction action) throws ApplicationException {
        new UserRepository().delete(action.getUserId());
        return new VoidResult();
    }

    public Class<DeleteUserAction> getActionType() {
        return DeleteUserAction.class;
    }


}