package com.bracketbird.server.services;


import com.bracketbird.server.dao.*;
import com.bracketbird.server.repository.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class CreateConstantHandler extends AbstractActionHandler  implements ActionHandler<CreateConstantAction, SingleResult> {


    public SingleResult execute(CreateConstantAction action) throws ApplicationException {
        return CreateConstantAccount(action);
    }




    private SingleResult CreateConstantAccount(CreateConstantAction action) throws ApplicationException {
        long id = getUniqueConstanceId();
        try {
            PMF.startTransaction();

            CreateConstant createConstant = action.getCreateConstant();
            createConstant.setId(id);
            Constant c = new ConstantRepository().create(createConstant);

            PMF.commitTransaction();
            return new SingleResult(c);
        }
        finally {
            PMF.endTransaction();
        }
    }


    private long getUniqueConstanceId() {
        try {
            PMF.startTransaction();
            long id = new CounterDao().nextConstantCounts();
            PMF.commitTransaction();
            return id;
        }
        finally {
            PMF.endTransaction();
        }
    }

    public Class<CreateConstantAction> getActionType() {
        return CreateConstantAction.class;
    }


}