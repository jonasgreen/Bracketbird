package com.bracketbird.server.mail;


import com.bracketbird.client.model.*;
import com.bracketbird.client.url.*;
import com.bracketbird.server.services.*;


/**
 *
 */
public class MemberCreatedMail {

    private HtmlStringBuffer sb = new HtmlStringBuffer(true);
    private Club club;
    private Member member;


    public MemberCreatedMail(Club club, Member member) {
        this.club = club;
        this.member = member;
    }


    public String getTitle() {
        return "Member of " + club.getName();
    }

    public String getEmailContent() {

        sb.p("You have been added as a member of the club " + club.getName()+".");
        sb.p("You can enter the club, in the menu 'My clubs' after you have logged in at <a href=\"http://www.bracketbird.appspot.com\">www.onlineclubmaster.com</a>");

        UrlCommand urlC = UrlCommand.deleteMembership(ActionHandlerRegistry.getBaseUrl(), new DeleteMembership(), member.getId(), club.getName());
        sb.p("If you believe this membership is a mistake you can cancel it <a href=\""+urlC.getUrl()+"\">here</a>.");

        return sb.toString();
    }


}