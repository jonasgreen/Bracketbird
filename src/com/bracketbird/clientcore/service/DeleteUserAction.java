package com.bracketbird.clientcore.service;


import com.bracketbird.clientcore.model.keys.*;

/**
 *
 */
public class DeleteUserAction extends AbstractAction  implements Action<VoidResult>{
    private static final long serialVersionUID = 6411689227609728779L;

    private UserId userId;


    public DeleteUserAction() {
    }

    public DeleteUserAction(UserId userId) {
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }
}