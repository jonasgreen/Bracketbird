package com.bracketbird.client.gui.main.personal.personal;


import com.bracketbird.client.*;
import com.bracketbird.client.model.*;
import com.bracketbird.client.service.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.service.*;
import com.bracketbird.clientcore.validation.*;

import java.util.*;

/**
 *
 */
public class CreateClubPageController extends PageController<CreateClubPage> {

    public static String HISTORY_NAME = "CreateClubPage";
    private static CreateClubPageController instance;
    private boolean firstLoad = true;

    private CreateClubPageController() {
        super(PersonalPageController.getInstance(), HISTORY_NAME);
    }

    public static CreateClubPageController getInstance() {
        if (instance == null) {
            instance = new CreateClubPageController();
        }
        return instance;
    }

    public void setFocus() {
        getPage().NAME.setFocus(true);
    }

    public CreateClubPage newInstance() {
        return new CreateClubPage();

    }

    public void afterLoadFromHistory() {

    }

    public MenuComponent newMenuInstance() {
        return new MenuLinkComponent("Create a new club");

    }

    public void afterLoad() {
        if (firstLoad) {
            firstLoad = false;
            
        }
        getPage().removeSuccesText();
        getPage().removeErrors();
        getPage().NAME.setFocus(true);
    }


    public boolean makeHistory() {
        return true;
    }

    public UserStateConstant getLegalState() {
        return UserStateConstant.LOGGED_IN;
    }


    public void createClub() {
        page.removeErrors();
        getPage().removeSuccesText();
        ValidateManager vm = new ValidateManager();
        List<DataContainer> dcs = new ArrayList<DataContainer>();
        dcs.addAll(page.getDataContainerChildren());

        boolean succes = vm.validate(dcs);
        if (succes) {
            docreateClub();
        }
        else {
            page.showErrors(vm);
        }
    }


    private void docreateClub() {
        CreateClub cc = new CreateClub();
        cc.setName(page.NAME.getValue());
        cc.setUserId(UserManager.getInstance().getUser().getId());
        //noinspection deprecation
        cc.setTimeZoneOffset(new Date().getTimezoneOffset()/60);
        cc.setVisibility(ClubVisabilityConstant.CLOSED.getValue());

        BBService.createClub(cc, new CallBack<ClubResult>() {
            public void fail(Throwable caught) {
            }

            public void success(ClubResult result) {
                //TODO UserManager.getInstance().getUser().getMembers().add(result.getMember());
                PersonalPageController.getInstance().updateClubs();
                PopupManager.hide();
                //page.showSucces(result.getClub());
                InfoManager.showSucces("Your club was succesfully created.");
            }
        });
    }

    public void clear() {
        super.clear();
        firstLoad = true;
    }


}