package com.bracketbird.server.services;


import com.bracketbird.client.model.*;
import com.bracketbird.client.service.*;
import com.bracketbird.server.*;
import com.bracketbird.server.dao.*;
import com.bracketbird.server.mail.*;
import com.bracketbird.server.repository.*;
import com.bracketbird.server.util.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.language.*;

import java.util.*;

/**
 *
 */
public class CreateMemberHandler extends AbstractActionHandler implements ActionHandler<CreateMemberAction, MemberResult> {

    private UserRepository userRepos = new UserRepository();
    private MemberRepository memberRepos = new MemberRepository();

    public MemberResult execute(CreateMemberAction action) throws ApplicationException {
        return createMemberAccount(action);
    }


    private MemberResult createMemberAccount(CreateMemberAction action) throws ApplicationException {

        User user = userRepos.findByEmail(action.getCreateMember().getEmail());
        boolean userCreated = false;
        if (user == null) {
            CreateMember cm = action.getCreateMember();
            CreateUser cu = CreateUser.newInstance(cm.getFirstName(), cm.getLastName(), cm.getNickName(), RandomPass.getPass(), cm.getPhone(), cm.getEmail());
            CreateUserAction a = new CreateUserAction(cu);
            user = new CreateUserHandler().execute(a).getUser();
            userCreated = true;
        }
        //setting user id
        action.getCreateMember().setUserId(user.getId());

        //member must not exist
        if (!userCreated) {
            Collection<Member> ms = memberRepos.findBy(FinderFac.findByEntityId(user.getId()));
            for (Member m : ms) {
                if (m.getClubId().equals(action.getCreateMember().getClubId())) {
                    throw new ApplicationException("User is already member of the club.");
                }
            }
        }

        Member m = createMember(action.getCreateMember());

        Club club = new ClubRepository().get(action.getCreateMember().getClubId());
        createMemberMail(club, user, m);
        return new MemberResult(m);
    }

    private Member createMember(CreateMember cr) throws ApplicationException {
        try {
            PMF.startTransaction();
            Member m = new MemberRepository().create(cr);

            PMF.commitTransaction();
            return m;
        }
        finally {
            PMF.endTransaction();
        }
    }



    private void createMemberMail(Club club, User user, Member member) throws ApplicationException {
        MemberCreatedMail mail = new MemberCreatedMail(club, member);
        MailTemplate mt = new MailTemplate(Language.ENGLISH, mail.getTitle());

        new MailService().sendMail(user, mail.getTitle(), mt.createMail(mail.getEmailContent(), true), Language.ENGLISH.getValue());

    }


    public Class<CreateMemberAction> getActionType() {
        return CreateMemberAction.class;
    }


}