package com.bracketbird.server.services;


import com.bracketbird.server.dao.*;
import com.bracketbird.server.repository.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class DeleteHandler extends AbstractActionHandler implements ActionHandler<DeleteAction, VoidResult> {

    public VoidResult execute(DeleteAction action) throws ApplicationException {
        Repository r = ActionHandlerRegistry.getRepos(action.getFindIn());

        try {

            PMF.startTransaction();
            r.delete(action.getIndexKey());
            PMF.commitTransaction();
            return new VoidResult();

        }
        finally {
            PMF.endTransaction();
        }


    }


    public Class<DeleteAction> getActionType() {
        return DeleteAction.class;
    }


}