package com.bracketbird.server.mail;

import com.bracketbird.client.model.*;
import com.bracketbird.client.url.*;
import com.bracketbird.server.services.*;

/**
 *
 */
public class UserCreatedMail {

    private HtmlStringBuffer sb = new HtmlStringBuffer(true);
    private User user;

    public UserCreatedMail(User user) {
        this.user = user;
    }


    public String getTitle() {
        return "Account created";
    }

    public String getEmailContent() {


        String baseUrl = ActionHandlerRegistry.getBaseUrl();
        UrlCommand uc = UrlCommand.deleteAccount(baseUrl, new DeleteAccount(), user.getId());

        sb.p("An account have been created for you at "+baseUrl +".");
        appendMailAndPass();
        sb.p("To change your password log into <a href=\""+baseUrl+"\">Bracketbird</a> and go to your account page.");
        sb.p("If you believe this account is a mistake you can delete it <a href=\""+uc.getUrl()+"\">here</a>.");
        return sb.toString();
    }

    private void appendMailAndPass() {
        sb.p("Use your email and password to login:", "visitcard");
        sb.p("<b>Email:</b> " + user.getEmail(), "visitcard");
        sb.p("<b>Password:</b> " + user.getPassword(), "visitcardLast");
    }

}
