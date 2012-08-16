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
public class SignInPageController extends PageController<SignInPage> implements PopupPageController {

    private static SignInPageController instance = null;
    public static String HISTORY_NAME = "signin";


    private SignInPageController() {
        super(AppPageController.getInstance(), HISTORY_NAME);
    }

    public static SignInPageController getInstance() {
        if (instance == null) {
            instance = new SignInPageController();
        }
        return instance;
    }

    public SignInPage newInstance() {
        return new SignInPage();
    }

    public void afterLoad() {
        getPage();
        page.setVisible(true);
        page.getSignInPanel().PASSWORD.clear();
        page.removeErrors();
        page.getSignInPanel().EMAIL.setFocus(true);
        page.getSignInPanel().REMEMBER_ME.getCheckBox().setValue(false);
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuLabelComponent("");
    }

    public MenuLabelComponent getMenu() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void signIn() {
        ValidateManager vm = new ValidateManager();
        boolean succes = vm.validate(page.getSignInPanel().getDataContainerChildren());
        if (succes) {
            page.removeErrors();
            BBService.signIn(page.getSignInPanel().getValue(), new CallBack<UserResult>() {
                public void fail(Throwable caught) {
                    caught.printStackTrace();
                }

                public void success(UserResult result) {
                    PopupManager.hide();
                    UserManager.getInstance().loggedOn(result.getUser(), page.getSignInPanel().REMEMBER_ME.getCheckBox().getValue());
                    InfoManager.showSucces("Du er succesfuldt logget på");
                }
            });
        }
        else {
            page.showErrors(vm);
        }
    }

    public void forgotPassword() {
        page.getSignInPanel().PASSWORD.setDataIsIllegal(false);
        ValidateManager vm = new ValidateManager();
        List<DataContainer> containers = new ArrayList<DataContainer>();
        containers.add(page.getSignInPanel().EMAIL);
        boolean succes = vm.validate(containers);

        if (succes) {//succes
            page.removeErrors();
            BBService.emailPasswordToUser(page.getSignInPanel().EMAIL.getValue(), new CallBack<VoidResult>() {
                @Override
                public void success(VoidResult result) {
                    InfoManager.showSucces("Dit password er blevet sendt til den angivne mail,", "og burde være fremme inden for få minutter");
                }

                @Override
                public void fail(Throwable t) {
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
