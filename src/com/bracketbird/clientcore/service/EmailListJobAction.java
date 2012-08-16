package com.bracketbird.clientcore.service;

/**
 *
 */
public class EmailListJobAction extends AbstractAction  implements Action<VoidResult> {
    private static final long serialVersionUID = -4495806514001436131L;

    private String subscriptionId;
    private String emailContent;


    public EmailListJobAction() {
    }

    public EmailListJobAction(String subscriptionId, String emailContent) {
        this.subscriptionId = subscriptionId;
        this.emailContent = emailContent;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }
}