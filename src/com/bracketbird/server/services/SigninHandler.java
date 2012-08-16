package com.bracketbird.server.services;


import com.bracketbird.client.model.*;
import com.bracketbird.client.service.*;
import com.bracketbird.server.repository.*;
import com.bracketbird.clientcore.appcontrol.*;

import java.util.UUID;

/**
 *
 */
public class SigninHandler extends AbstractActionHandler  implements ActionHandler<SigninAction, UserResult> {

    private UserRepository repos = new UserRepository();

    public UserResult execute(SigninAction action) throws ApplicationException {
        User user = repos.findByEmail(action.getSignInData().getEmail());
        if (user == null) {
            throw new ApplicationException("Unable to log on - email could not be identified.");
        }

        if (!user.getPassword().equals(action.getSignInData().getPassword())) {
            throw new ApplicationException("Unable to log on - wrong password.");
        }

        return new UserResult(user);
    }

    public Class<SigninAction> getActionType() {
        return SigninAction.class;
    }


}