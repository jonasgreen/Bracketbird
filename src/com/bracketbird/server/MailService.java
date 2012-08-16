package com.bracketbird.server;


import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.language.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.*;

/**
 *
 */
public class MailService {
    private static String FROM_ADDRESS = "jonasgreen12345@gmail.com";
    private static String FROM_NAME = "Bracketbird";


    public void sendMail(User to, String subject, String msgBody, Integer language) throws ApplicationException {
        sendMail(to.getEmail(), to.getName(), subject, msgBody, language);
    }

    public void sendMail(User to, String subject, String msgBody, String fromName, Integer language) throws ApplicationException {
        sendMail(to.getEmail(), to.getName(), fromName, subject, msgBody, language);
    }

    public void sendMail(String toEmail, String toName, String subject, String msgBody, Integer language) throws ApplicationException {
        sendMail(toEmail, toName, FROM_NAME, subject, msgBody, language);
    }

    public void sendMail(String toEmail, String toName, String fromName, String subject, String msgBody, Integer language) throws ApplicationException {
        Properties props = new Properties();
        props.setProperty("mail.mime.charset", "UTF-8");
        Session session = Session.getDefaultInstance(props, null);
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM_ADDRESS, fromName));
            InternetAddress fromAddress = new InternetAddress(toEmail, toName);
            msg.addRecipient(Message.RecipientType.TO, fromAddress);
            msg.setSubject(MimeUtility.encodeText(subject, "UTF-8", null));
            msg.setContent(msgBody, "text/html");

            Transport.send(msg);
        }
        catch (AddressException e) {
            throw new ApplicationException(LEmailPassword.EMAIL_NOT_VALID.get(language == null ? 1 : language));
        }
        catch (MessagingException e) {
            throw new SystemException(e.getMessage());
        }
        catch (UnsupportedEncodingException e) {
            throw new SystemException(e.getMessage());
        }
    }

}
