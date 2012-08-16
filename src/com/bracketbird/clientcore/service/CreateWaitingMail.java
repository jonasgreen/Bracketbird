package com.bracketbird.clientcore.service;

/**
 *
 */
public class CreateWaitingMail extends AbstractAction  implements Action<VoidResult>{
    private static final long serialVersionUID = 4380653088687718134L;

    private String email;


    public CreateWaitingMail() {
    }

    public CreateWaitingMail(String createClub) {
        this.email = createClub;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}