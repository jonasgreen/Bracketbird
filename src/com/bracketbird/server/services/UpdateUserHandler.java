package com.bracketbird.server.services;

import com.bracketbird.client.model.*;
import com.bracketbird.client.service.*;
import com.bracketbird.server.dao.*;
import com.bracketbird.server.repository.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.service.*;

import java.util.*;

/**
 *
 */
public class UpdateUserHandler extends AbstractActionHandler  implements ActionHandler<UpdateUserAction, VoidResult> {

    private UserRepository repos = new UserRepository();
    private MemberRepository memberRepos = new MemberRepository();

    public VoidResult execute(UpdateUserAction action) throws ApplicationException {
        updateUser(action);


        Collection<Member> ms = memberRepos.findByUserId(action.getUser().getId());
        for (Member m : ms) {
            m.setFirstName(action.getUser().getFirstName());
            m.setLastName(action.getUser().getLastName());
            m.setNickName(action.getUser().getNickName());
            updateMember(m);
        }

        return new VoidResult();
    }


    private void updateMember(Member m) {
        try {
            PMF.startTransaction();
            memberRepos.update(m);
            PMF.commitTransaction();
        }
        finally {
            PMF.endTransaction();
        }
    }

    private void updateUser(UpdateUserAction action) {
        try {
            PMF.startTransaction();
            repos.update(action.getUser());
            PMF.commitTransaction();
        }
        finally {
            PMF.endTransaction();
        }
    }

    public Class<UpdateUserAction> getActionType() {
        return UpdateUserAction.class;
    }


}