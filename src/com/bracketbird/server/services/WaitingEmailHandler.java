package com.bracketbird.server.services;


import com.bracketbird.server.dao.*;
import com.bracketbird.server.jdo.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.service.*;

import java.util.*;

/**
 *
 */
public class WaitingEmailHandler extends AbstractActionHandler  implements ActionHandler<CreateWaitingMail, VoidResult> {

    private Dao dao = new Dao(WaitingEmailJDO.class);

    public VoidResult execute(CreateWaitingMail action) throws ApplicationException {
        WaitingEmailJDO jdo = new WaitingEmailJDO();
        jdo.setCreatedDate(new Date());
        jdo.setEmail(action.getEmail().trim().toLowerCase());
        dao.create(jdo);
        return new VoidResult();
    }


    public Class<CreateWaitingMail> getActionType() {
        return CreateWaitingMail.class;
    }


}