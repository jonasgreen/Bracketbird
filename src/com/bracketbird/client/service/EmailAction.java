package com.bracketbird.client.service;


import com.bracketbird.clientcore.model.keys.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class EmailAction extends AbstractAction implements Action<VoidResult> {
    private static final long serialVersionUID = -7374113903787657949L;

    private String fromName;
    private String msgBody;
    private String subject;
    private UserId userId;

    public EmailAction(UserId userId, String fromName, String msgBody, String subject) {
        this.userId = userId;
        this.fromName = fromName;
        this.msgBody = msgBody;
        this.subject = subject;
    }

    public EmailAction() {
    }


    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }
}