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
public class CreateClubHandler extends AbstractActionHandler  implements ActionHandler<CreateClubAction, ClubResult> {

    private ClubRepository repos = new ClubRepository();
    private MemberRepository memberRepos = new MemberRepository();
    private UserRepository userRepos = new UserRepository();

    public ClubResult execute(CreateClubAction action) throws ApplicationException {
        SingleFinder fp = FinderFac.findByNameLowerCaser(action.getCreateClub().getName().toLowerCase());

        Collection<Club> clubCollection = repos.findBy(fp);
        if (clubCollection == null || !clubCollection.isEmpty()) {
            throw new ApplicationException("A club with that name already exist");
        }


        long clubId = getUniqueClubId();

        try {
            User user = userRepos.get(action.getCreateClub().getUserId());

            PMF.startTransaction();

            //Mail - should be before commit to ensure correct email of user.
            //new MailService().accountCreated(user);

            CreateClub createClub = action.getCreateClub();
            createClub.setId(clubId);
            Club club = repos.create(createClub);
            Member member = createAdmin(club, user);
            PMF.commitTransaction();

            ClubResult cr = new ClubResult(club);
            cr.setMember(member);
            return cr;
        }
        finally {
            PMF.endTransaction();
        }
    }


    private long getUniqueClubId() {
        try {
            PMF.startTransaction();
            long id = new CounterDao().nextClubCounts();
            PMF.commitTransaction();
            return id;
        }
        finally {
            PMF.endTransaction();
        }
    }

    private Member createAdmin(Club club, User user) throws ApplicationException {
        CreateMember cm = new CreateMember();
        cm.setClubId(club.getId());
        cm.setMemberType(UserStateConstant.ADMIN.getValue());
        cm.setUserId(user.getId());
        cm.setFirstName(user.getFirstName());
        cm.setLastName(user.getLastName());
        cm.setNickName(user.getNickName());

        return memberRepos.create(cm);
    }


    public Class<CreateClubAction> getActionType() {
        return CreateClubAction.class;
    }


}