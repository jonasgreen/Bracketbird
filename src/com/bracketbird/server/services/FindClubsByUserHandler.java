package com.bracketbird.server.services;


import com.bracketbird.client.model.*;
import com.bracketbird.client.service.*;
import com.bracketbird.server.repository.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.service.*;

import java.util.*;

/**
 *
 */
public class FindClubsByUserHandler extends AbstractActionHandler  implements ActionHandler<FindClubsByUserAction, ListClubsResult> {

    private ClubRepository repos = new ClubRepository();
    private MemberRepository memberRepos = new MemberRepository();

    public ListClubsResult execute(FindClubsByUserAction action) throws ApplicationException {
        SingleFinder fp = FinderFac.findByEntityId(action.getUserId());
        Collection<Member> members = memberRepos.findBy(fp);

        List<Club> clubs = findClubByMembers(members);
        return new ListClubsResult(clubs);
    }

    private List<Club> findClubByMembers(Collection<Member> members) {
        List<Club> clubs = new ArrayList<Club>();
        for (Member m : members) {
            clubs.add(repos.get(m.getClubId()));
        }
        return clubs;
    }


    public Class<FindClubsByUserAction> getActionType() {
        return FindClubsByUserAction.class;
    }


}