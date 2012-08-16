package com.bracketbird.client.service;


import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class CreateUserAction extends AbstractAction implements Action<UserResult>{
    private static final long serialVersionUID = -561380444769847802L;

    private CreateUser signup;


    public CreateUserAction() {
    }

    public CreateUserAction(CreateUser signup) {
        this.signup = signup;
    }

    public CreateUser getSignup() {
        return signup;
    }

    public void setSignup(CreateUser signup) {
        this.signup = signup;
    }

}
