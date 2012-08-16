package com.bracketbird.server.services;


import com.bracketbird.client.model.*;
import com.bracketbird.client.service.*;
import com.bracketbird.server.*;
import com.bracketbird.server.dao.*;
import com.bracketbird.server.mail.*;
import com.bracketbird.server.repository.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.language.*;

import java.util.UUID;

/**
 *
 */
public class CreateUserHandler extends AbstractActionHandler implements ActionHandler<CreateUserAction, UserResult> {

    private UserRepository userRepos = new UserRepository();
    private ClubRepository clubRepos = new ClubRepository();

    public UserResult execute(CreateUserAction action) throws ApplicationException {
        return createUserAccount(action);
    }


    private UserResult createUserAccount(CreateUserAction action) throws ApplicationException {

        User user = null;

        try {
            Long userId = getUniqueUserId();
            user = createUser(action, userId);
            return new UserResult(user);
        }
        catch (ApplicationException e) {
            //damage control.
            if(user != null){
                userRepos.delete(user.getId());
            }

            throw e;
        }
        catch (RuntimeException e) {

            //damage control.
            if(user != null){
                userRepos.delete(user.getId());
            }

            throw e;

        }
    }


    public User getOrCreateSystemUser() throws ApplicationException {
        String email = "systemuser@bracketbird.com";
        User u = new UserRepository().findByEmail(email);
        if(u == null){
            CreateUser cu = new CreateUser();
            cu.setEmail(email);
            return execute(new CreateUserAction(cu)).getUser();
        }
        return u;
    }

    private User updateUser(User user) {
        try {
            PMF.startTransaction();
            User updatedUser = userRepos.update(user);

            PMF.commitTransaction();
            return updatedUser;
        }
        finally {
            PMF.endTransaction();
        }
    }

    private User createUser(CreateUserAction action, Long id) throws ApplicationException {
        try {
            PMF.startTransaction();
            CreateUser cr = action.getSignup();
            cr.setId(id);
            User user = createUser(cr);
            createUserMail(user);

            PMF.commitTransaction();
            return user;
        }
        finally {
            PMF.endTransaction();
        }
    }

    private long getUniqueUserId() {
        try {
            PMF.startTransaction();
            long id = new CounterDao().nextUserCounts();
            PMF.commitTransaction();
            return id;
        }
        finally {
            PMF.endTransaction();
        }
    }

    private User createUser(CreateUser createUser) throws ApplicationException {
        User user = userRepos.findByEmail(createUser.getEmail());
        if (user != null) {
            if (user.isDeleted()) {
                return userRepos.reopen(user.getId());
            }
            throw new ApplicationException("Unable to create user - email in use allready.");
        }

        user = userRepos.create(createUser);
        return user;
    }

    private void createUserMail(User user) throws ApplicationException {
        UserCreatedMail mail = new UserCreatedMail(user);
        MailTemplate mt = new MailTemplate(Language.ENGLISH, mail.getTitle());
        new MailService().sendMail(user, mail.getTitle(), mt.createMail(mail.getEmailContent(), true), Language.ENGLISH.getValue());

    }


    public Class<CreateUserAction> getActionType() {
        return CreateUserAction.class;
    }


}
