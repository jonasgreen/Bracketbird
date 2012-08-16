package com.bracketbird.client.url;

import com.bracketbird.client.gui.main.SinglePageNotice;
import com.bracketbird.client.service.*;
import com.bracketbird.clientcore.model.keys.*;
import com.bracketbird.clientcore.service.*;

import java.util.*;



/**
 *
 */
public class DeleteAccount implements Command {
    public static String name = "DELETE_ACCOUNT";
    private final static String errTitle = "Failed to delete your account.";
    private final static String errSubTitle = "Try again later og contact Bracketbird for manual deletion.";

    SinglePageNotice spn;

    public void execute(Map<String, String> pMap) {
        spn = new SinglePageNotice("Deleting your account.", "");
        spn.show();


        try {
            UserId userId = new UserId(pMap.get(UrlParam.USER_ID));

            BBService.deleteUser(userId, new CallBack<VoidResult>() {
                @Override
                public void success(VoidResult result) {
                    spn.getTitleLabel().setText("Your account has been deleted.");
                    spn.getSubtitleLabel().setText("If you had memberships of any clubs, they have been deleted as well.");
                    spn.show();
                }

                @Override
                public void fail(Throwable t) {
                    spn.getTitleLabel().setText(errTitle);
                    spn.getSubtitleLabel().setText(errSubTitle);
                }
            });
        }
        catch (Exception e) {
            spn.getTitleLabel().setText(errTitle);
            spn.getSubtitleLabel().setText(errSubTitle);
        }


    }

    public String getName() {
        return name;
    }

}
