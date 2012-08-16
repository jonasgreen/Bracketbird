package com.bracketbird.client.gui.main.club.home;


import com.bracketbird.client.*;
import com.bracketbird.client.model.*;
import com.google.gwt.user.client.Timer;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.util.*;

import java.util.*;

/**
 *
 */
public class ClubWallPageController extends PageController<ClubWallPage> {

    public static String HISTORY_NAME = "BoardPage";
    private static ClubWallPageController instance;
    private MenuLinkComponent ml = new MenuLinkComponent("Wall of today");
    private Timer t;
    private boolean firstLoad = true;

    private ClubWallPageController() {
        super(ClubHomePageController.getInstance(), HISTORY_NAME);
    }

    public static ClubWallPageController getInstance() {
        if (instance == null) {
            instance = new ClubWallPageController();
        }
        return instance;
    }


    public ClubWallPage newInstance() {
        return new ClubWallPage();

    }


    public void submit() {
        String text = page.getSubmitTextBox().getText();
        if (StringUtil.isEmpty(text)) {
            //ignore
        }
        else {
            if (!UserManager.getInstance().isMember()) {
                InfoManager.showInfo("Only members can write on the wall - please use guest forum instead.");
                return;
            }
            page.getSubmitTextBox().setText("");
            User user = UserManager.getInstance().getUser();
            String name = user == null ? "Guest" : (user.getFirstName() + " " + user.getLastName());
/*
            CreateWallMessage cr = new CreateWallMessage();
            cr.setClubId(UserManager.getInstance().getClub().getId());
            cr.setMessage(text);
            cr.setType(WallMessageConstant.USER_MESSAGE.getValue());
            cr.setUserName(name);
            Service.createWallMessage(cr, new CallBack<SingleResult<WallMessage>>() {
                @Override
                public void success(SingleResult<WallMessage> result) {
                    refreshMessages();
                }

                @Override
                public void fail(Throwable t) {
                }
            });
            **(
            */

        }
        page.getSubmitTextBox().setFocus(true);
    }

    private void refreshMessages() {

    }


    public void afterLoadFromHistory() {
        afterLoad();
    }

    private void loadMessages() {
        if (firstLoad) {
            firstLoad = false;
            loadMessages(DateUtil.yesterDay());
        }
    }

    private void loadMessages(final Date afterDate) {
        /*
        Service.getWallMessages(UserManager.getInstance().getClub().getId(), afterDate,     new CallBack<ListResult<WallMessage>>() {            @Override
            public void success(ListResult<WallMessage> result) {
                //page.getBoard().addMessages(result.getResult());
            }

            @Override
            public void fail(Throwable t) {
                t.printStackTrace();
            }
        });
*/
    }

    public void unload() {
        if (t != null) {
            t.cancel();
            t = null;
        }
    }

    public MenuComponent newMenuInstance() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void afterLoad() {
        getPage().getSubmitTextBox().setFocus(true);
        loadMessages();
        startTimer();
    }

    private void startTimer() {
        if (t == null) {
            t = new Timer() {
                @Override
                public void run() {
                    refreshMessages();
                }
            };
        //    t.scheduleRepeating((int) DateUtil.ONE_MINUTE);
        }
    }

    public boolean makeHistory() {
        return true;
    }

    public MenuComponent getMenu() {
        return ml;
    }


    public void clear() {
        super.clear();
        firstLoad = true;
    }

    public UserStateConstant getLegalState() {
        return UserStateConstant.GUEST;
    }


}