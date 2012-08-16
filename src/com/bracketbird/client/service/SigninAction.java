package com.bracketbird.client.service;


import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class SigninAction extends AbstractAction implements Action<UserResult>{
    private static final long serialVersionUID = -3704912342832981433L;

    private SignInData signInData;

    public SigninAction() {
    }

    public SigninAction(SignInData signInData) {
        this.signInData = signInData;
    }

    public SignInData getSignInData() {
        return signInData;
    }

    public void setSignInData(SignInData signInData) {
        this.signInData = signInData;
    }
}