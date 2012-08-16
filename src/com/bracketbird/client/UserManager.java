package com.bracketbird.client;


import com.bracketbird.client.event.*;
import com.bracketbird.client.gui.main.club.home.*;
import com.bracketbird.client.gui.main.FrontPageController;
import com.bracketbird.client.gui.main.personal.personal.PersonalPageController;
import com.bracketbird.client.model.*;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.client.service.*;
import com.google.gwt.user.client.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.model.keys.*;
import com.bracketbird.clientcore.service.*;

import java.util.*;

/**
 *
 */
public class UserManager {
    public final static String COOKIE_USER_ID = "userid";

    private static UserManager instance;

    private UserStateConstant userState = UserStateConstant.LOGGED_OUT;
    private User user;
    private Member member;
    private Club club;
    private Collection<Member> members = new ArrayList<Member>();
    private HashMap<MemberId, Member> memberLookup = new HashMap<MemberId, Member>();


    private UserManager() {
    }


    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }


    public void loggedOn(User user, boolean rememberUser) {
        this.user = user;
        if (rememberUser) {
            setCookie(user.getId().getEncodedKey());
        }
        updateUserState(UserStateConstant.LOGGED_IN);
    }

    public void loggedOut() {
        try {
            updateUserState(UserStateConstant.LOGGED_OUT);
            Cookies.removeCookie(COOKIE_USER_ID);
            InfoManager.showSucces("You have now logged out.");
        }
        finally {
            this.member = null;
            this.user = null;
            this.club = null;
            PageFlow.clearWhenLoggedOut();
        }

    }

    private void setCookie(String userId) {
        try {
            Date expire = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 14));//14 days
            Cookies.setCookie(COOKIE_USER_ID, userId, expire);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUserIdFromCookie() {
        String userId = null;
        try {
            userId = Cookies.getCookie(COOKIE_USER_ID);
        }
        catch (Exception e) {
            //ignore
        }
        return userId;
    }


    public void silentLogIn() {
        String userId = getUserIdFromCookie();
        if (userId != null) {
            BBService.automaticLogin(new UserId(userId), new CallBack<SingleResult<User>>() {
                public void fail(Throwable caught) {
                    PageFlow.show(FrontPageController.getInstance());
                }

                public void success(SingleResult<User> result) {
                    if (result != null && result.getResult() != null) {
                        loggedOn(result.getResult(), true);
                    }
                    else {
                        PageFlow.show(FrontPageController.getInstance());
                    }
                }
            });
        }
        else {
            loadTransitionPage();
        }
    }


    public void loggedIntoClub(Club theClub) {
        this.club = theClub;
        BBService.findMembersByUser(user.getId(), new CallBack<ListResult<Member>>() {
            @Override
            public void success(ListResult<Member> result) {
                Color.setScheme(new ColorSchemeInClub());
                for (Member m : result.getResult()) {
                    if (m.getClubId().equals(club.getId())) {
                        member = m;
                        updateUserState(UserStateConstant.get(m.getMemberType()));
                        return;
                    }
                }
            }

            @Override
            public void fail(Throwable t) {
            }
        });
        updateUserState(UserStateConstant.GUEST);
        //updateUserState(UserStateConstant.ADMIN);
    }


    public void loggedOutOfClub() {
        try {
            updateUserState(UserStateConstant.LOGGED_IN);
        }
        finally {
            this.member = null;
            this.club = null;
            PageFlow.clearWhenLoggedOutOfClub();
        }
    }

    public UserStateConstant getUserState() {
        return userState;
    }

    private void updateUserState(UserStateConstant newUserState) {
        if (this.userState.getValue() == newUserState.getValue()) {
            return;
        }
        UserStateConstant oldValue = userState;
        this.userState = newUserState;
        loadTransitionPage();
        EventManager.userStateChanged(user, newUserState, oldValue);
    }


    public User getUser() {
        return user;
    }

    public boolean isInTransistionLoggedOut() {
        return userState.getValue() == UserStateConstant.LOGGED_OUT.getValue();
    }

    public boolean isInTransitionLoggedIn() {
        return userState.getValue() == UserStateConstant.LOGGED_IN.getValue();
    }

    public boolean isInTransistionInClub() {
        int state = userState.getValue();
        return state == UserStateConstant.GUEST.getValue() ||
                state == UserStateConstant.MEMBER.getValue() ||
                state == UserStateConstant.ADMIN.getValue();
    }

    public void loadTransitionPage() {
        if (isInTransistionLoggedOut()) {
            Color.setScheme(new DefaultColorScheme());
            PageFlow.show(FrontPageController.getInstance());
        }
        else if (isInTransitionLoggedIn()) {
            Color.setScheme(new DefaultColorScheme());            
            PageFlow.show(PersonalPageController.getInstance());
        }
        else if (isInTransistionInClub()) {
            Color.setScheme(new ColorSchemeInClub());
            PageFlow.show(ClubHomePageController.getInstance());
        }
        else {
            throw new SystemException("UserState: " + userState.getValue() + " not supported in loadHomePage in UserManager");
        }
    }

    public Club getClub() {
        return club;
    }

    public void setClubSilent(Club club) {
        this.club = club;
    }

    public void setUserSilent(User user) {
        this.user = user;
    }

    public void setMemberSilent(Member m) {
        this.member = m;
    }

    public Member getMember() {
        return member;
    }

    public boolean isMember() {
        return member != null;
    }

    public static void validateIsMember() {
        if (!UserManager.getInstance().isMember()) {
            InfoManager.showInfo("Sorry - guests are not allowed to do this.");
            throw new SystemException();
        }
    }

    public Collection<Member> getMembers() {
        return members;
    }

    public void setMembers(Collection<Member> members) {
        this.members = members;
        for (Member m : members) {
            memberLookup.put(m.getId(), m);
        }
    }

    public Member getMember(MemberId id){
        return memberLookup.get(id);
    }
}
