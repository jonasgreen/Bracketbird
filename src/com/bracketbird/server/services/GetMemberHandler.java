package com.bracketbird.server.services;


import com.bracketbird.client.model.*;
import com.bracketbird.client.service.*;
import com.bracketbird.server.*;
import com.bracketbird.server.repository.*;
import com.bracketbird.clientcore.appcontrol.*;

import java.util.*;

/**
 *
 */
public class GetMemberHandler extends AbstractActionHandler  implements ActionHandler<GetMemberAction, MemberResult> {

    private MemberRepository repos = new MemberRepository();

    public MemberResult execute(GetMemberAction action) throws ApplicationException {
        Collection<Member> members = repos.findByClubId(action.getUserId());
        for (Member m : members) {
            if(m.getClubId().equals(action.getClubId())){
                return new MemberResult(m);
            }
        }
        String msg ="Unable to find member - maybee member has been deleted";
        Logger.logPossibleDataError(msg + " userid:" + action.getUserId() + " and clubid:" + action.getClubId());
        throw new ApplicationException(msg);
    }



    public Class<GetMemberAction> getActionType() {
        return GetMemberAction.class;
    }


}