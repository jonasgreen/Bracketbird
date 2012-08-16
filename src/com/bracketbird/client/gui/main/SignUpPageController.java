package com.bracketbird.client.gui.main;


import com.bracketbird.client.*;
import com.bracketbird.client.model.*;
import com.bracketbird.client.service.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.service.*;
import com.bracketbird.clientcore.validation.*;

import java.util.*;

/**
 *
 */
public class SignUpPageController extends PageController<SignUpPage> implements PopupPageController {

    private static SignUpPageController instance = null;
    public static String HISTORY_NAME = "signup";


    private SignUpPageController() {
        super(AppPageController.getInstance(), HISTORY_NAME);
    }

    public static SignUpPageController getInstance() {
        if (instance == null) {
            instance = new SignUpPageController();
        }
        return instance;
    }

    public SignUpPage newInstance() {
        return new SignUpPage();
    }

    public void afterLoad() {
        getPage();
        page.setVisible(true);
        page.getSignupPanel().PASSWORD.clear();
        page.removeErrors();
        page.getSignupPanel().FIRST_NAME.setFocus(true);
    }

    public void unload() {
        page.getSignupPanel().REMEMBER_ME.getCheckBox().setValue(false);

    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuLinkComponent("");
    }

    public MenuLabelComponent getMenu() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void save() {
        ValidateManager vm = new ValidateManager();
        List<DataContainer> dcs = new ArrayList<DataContainer>();
        SignUpPanel sp = page.getSignupPanel();

        dcs.add(sp.FIRST_NAME);
        dcs.add(sp.LAST_NAME);
        dcs.add(sp.EMAIL);
        dcs.add(sp.PASSWORD);
        dcs.add(sp.REPEAT_PASSWORD);
        dcs.add(page.getAcceptTermsBox());

        boolean succes = vm.validate(dcs);


        if (succes) {
            page.removeErrors();
            BBService.createUser(page.getSignupPanel().getValue(), new CallBack<UserResult>() {
                public void fail(Throwable caught) {
                }

                public void success(UserResult result) {
                    PopupManager.hide();
                    UserManager.getInstance().loggedOn(result.getUser(), page.getSignupPanel().REMEMBER_ME.getCheckBox().getValue());
                    InfoManager.showSucces("Account created with succes.");
                    getPage().getAcceptTermsBox().setValue(false);
                }
            });
        }
        else {
            page.showErrors(vm);
        }
    }


    public UserStateConstant getLegalState() {
        return UserStateConstant.LOGGED_OUT;
    }

}
