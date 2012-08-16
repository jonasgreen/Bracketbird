package com.bracketbird.clientcore.service;

/**
 *
 */
public class EmailPasswordToUserAction extends AbstractAction  implements Action<VoidResult> {
    private static final long serialVersionUID = -4637071140795959057L;

    private String email;


    public EmailPasswordToUserAction() {

    }

    public EmailPasswordToUserAction(String userId) {

        this.email = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}