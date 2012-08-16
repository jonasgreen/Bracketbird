package com.bracketbird.server.services;


import com.bracketbird.client.model.*;
import com.bracketbird.server.*;
import com.bracketbird.server.repository.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.language.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class EmailPasswordToUserHandler extends AbstractActionHandler implements ActionHandler<EmailPasswordToUserAction, VoidResult> {

    private UserRepository repos = new UserRepository();

    public VoidResult execute(EmailPasswordToUserAction action) throws ApplicationException {
        User user = repos.findByEmail(action.getEmail());
        if(user == null){
            throw new ApplicationException("The email could not be found in the system.");
        }
        MailService m = new MailService();
        m.sendMail(user, LEmailPassword.YOUR_PASSWORD.get(getClientLanguage()), LEmailPassword.YOUR_PASSWORD_IS.get(getClientLanguage()) + user.getPassword(), getClientLanguage());

        return new VoidResult();
    }


    public Class<EmailPasswordToUserAction> getActionType() {
        return EmailPasswordToUserAction.class;
    }


}