package com.bracketbird.server.services;


import com.bracketbird.client.model.*;
import com.bracketbird.client.service.*;
import com.bracketbird.server.*;
import com.bracketbird.server.mail.*;
import com.bracketbird.server.repository.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.language.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class EmailHandler extends AbstractActionHandler  implements ActionHandler<EmailAction, VoidResult> {

    public VoidResult execute(EmailAction a) throws ApplicationException {
        MailService ms = new MailService();
        User u = new UserRepository().get(a.getUserId());
        MailTemplate mt = new MailTemplate(Language.ENGLISH, a.getSubject(), a.getFromName());
        ms.sendMail(u, a.getSubject(), mt.createMail(a.getMsgBody(), true), a.getFromName(), a.getLanguage());

        Logger.log("Mail Send to "+u.getEmail());
        return new VoidResult();
    }


    public Class<EmailAction> getActionType() {
        return EmailAction.class;
    }


}