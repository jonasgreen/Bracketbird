package com.bracketbird.client.url;

import com.bracketbird.client.gui.main.SinglePageNotice;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.client.service.*;
import com.bracketbird.clientcore.service.*;
import com.bracketbird.clientcore.util.*;

import java.util.*;

/**
 *
 */
public class DeleteMembership implements Command {

    public static String name = "DELETE_MEMBERSHIP";

    private SinglePageNotice spn;

    public void execute(Map<String, String> pMap) {
        spn = new SinglePageNotice("Cancelling your memebership.", null);
        spn.show();

        try {
            String memberId = pMap.get(UrlParam.MEMBER_ID);
            final String clubName = pMap.get(UrlParam.CLUB_NAME);
            long createdDate = Long.valueOf(pMap.get(UrlParam.CREATED_DATE));

            //users are only able to delete created accounts up to one month after.
            if((new Date().getTime() - createdDate) > DateConstants.THIRTY_DAYS){
                spn.getTitleLabel().setText("Your membership is to old to be cancelled this way.");
                spn.getSubtitleLabel().setText("Contact the club for manual cancellation");
                return;
            }

            spn.getTitleLabel().setText("Cancelling your memebership of "+clubName+".");

            BBService.deleteMember(new MemberId(memberId), new CallBack<VoidResult>() {
                @Override
                public void success(VoidResult result) {
                    spn.getTitleLabel().setText("Your membership has been cancelled.");
                    spn.getSubtitleLabel().setText("You are no longer a member of " + clubName + ".");
                }

                @Override
                public void fail(Throwable t) {
                    spn.getTitleLabel().setText("Failed to cancel your membership.");
                    spn.getSubtitleLabel().setText("Try again later or contact " + clubName + " for manual cancellation.");
                }
            });
        }
        catch (Exception e) {
            spn.getTitleLabel().setText("Failed to cancel your membership.");
            spn.getSubtitleLabel().setText("Try again later or contact Bracketbird for manual cancellation.");
        }
    }

    public String getName() {
        return name;
    }

}