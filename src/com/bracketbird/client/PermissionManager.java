package com.bracketbird.client;


import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.appcontrol.*;

/**
 *
 */
public class PermissionManager {
    private static PermissionManager instance;

    private PermissionManager() {
    }

    public static PermissionManager getInstance() {
        if (instance == null) {
            instance = new PermissionManager();
        }
        return instance;
    }

    public String[] pagePermission(PageController toLoad) {
        int userState = UserManager.getInstance().getUserState().getValue();

        String[] errMsg;
        if (userState == UserStateConstant.MEMBER.getValue()) {
            errMsg = new String[2];
            errMsg[0] = "Sorry, only administrators of the club";
            errMsg[1] = "have permission to do this.";
        }
        else if (userState == UserStateConstant.GUEST.getValue()) {
            errMsg = new String[2];
            errMsg[0] = "Sorry, only members of the club";
            errMsg[1] = "have permission to do this.";
        }
        else if (userState == UserStateConstant.LOGGED_IN.getValue()) {
            errMsg = new String[2];
            errMsg[0] = "Sorry, you have to enter a club";
            errMsg[1] = "before doing this.";
        }
        else if (userState == UserStateConstant.LOGGED_OUT.getValue()) {
            errMsg = new String[2];
            errMsg[0] = "Sorry, you have to log in";
            errMsg[1] = "before doing this.";
        }
        else {
            throw new SystemException("UserState error in Application Controller - validate page. UserState=" + userState);
        }
        return errMsg;
    }

}
