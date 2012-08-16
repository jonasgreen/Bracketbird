package com.bracketbird.client.gui.main.club.home;


import com.bracketbird.client.*;
import com.bracketbird.client.event.*;
import com.bracketbird.client.model.*;
import com.bracketbird.client.service.*;
import com.bracketbird.client.util.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class MembersPageController extends PageController<MembersPage> {
    private StringToMember stringToMembers = new StringToMember();

    private boolean updatingMembersInfo = false;

    public static String HISTORY_NAME = "MembersPage";
    private static MembersPageController instance;

    private MembersPageController() {
        super(MainPageController.getInstance(), HISTORY_NAME);
        EventManager.eventBus.addHandler(MemberCreatedEvent.TYPE,
                new MembersCreatedHandler() {
                    public void onChange(MemberCreatedEvent event) {
                        fetchMembers();
                    }
                });
    }

    public static MembersPageController getInstance() {
        if (instance == null) {
            instance = new MembersPageController();
        }
        return instance;
    }


    public MembersPage newInstance() {
        return new MembersPage();

    }

    public void setFocus() {
        getPage().setFocus();
    }

    public void afterLoadFromHistory() {
        setFocus();
    }

    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("Users.png","Members");


    }

    public void afterLoad() {
        setFocus();
        if (stringToMembers.getStringToMemberMap().isEmpty()) {
            fetchMembers();
        }
    }

    private void fetchMembers() {
        BBService.findMembersByClub(UserManager.getInstance().getClub().getId(), new CallBack<ListResult<Member>>() {
            @Override
            public void success(ListResult<Member> result) {
                stringToMembers = new StringToMember();
                MembersPageController.this.stringToMembers.add(result.getResult());
                getPage().updateMembersTable(stringToMembers.getStringToMemberMap().keySet());
            }

            @Override
            public void fail(Throwable t) {
                //ignore
            }
        });
    }


    public boolean makeHistory() {
        return true;
    }

    public UserStateConstant getLegalState() {
        return UserStateConstant.GUEST;
    }


    public void updateMembersInfo(String uniqueMemberName) {
        if (updatingMembersInfo) {
            return;
        }

        final Member m = stringToMembers.getStringToMemberMap().get(uniqueMemberName);
        if (m == null) {
            return;
        }
        updatingMembersInfo = true;


        BBService.getUser(m.getUserId(), new CallBack<SingleResult<User>>() {
            @Override
            public void success(SingleResult<User> result) {
                updatingMembersInfo = false;
                getPage().updateMemberInfo(result.getResult(), m);        
            }

            @Override
            public void fail(Throwable t) {
                updatingMembersInfo = false;
            }
        });

    }

    public void clear() {
        super.clear();
        stringToMembers = new StringToMember();
    }
}