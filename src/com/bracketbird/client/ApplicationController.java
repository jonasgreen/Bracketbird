package com.bracketbird.client;

import com.bracketbird.client.gui.main.FrontPageController;
import com.bracketbird.client.model.*;
import com.bracketbird.client.service.*;
import com.bracketbird.client.url.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.service.*;
import com.bracketbird.clientcore.util.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;

import java.util.*;

/**
 *
 */
public class ApplicationController {


    private static ApplicationController instance;


    public static ApplicationController getInstance() {
        if (instance == null) {
            instance = new ApplicationController();
        }
        return instance;
    }


    public final static String APP_WIDTH = "900px";
    public final static int APP_WIDTH_INT = 900;


    private GlobalKeyboardHandler gh = new GlobalKeyboardHandler();
    public static String nationality = null;


    private TextArea loggerPanel = null;
    //private HistorySupport history = HistorySupport.getInstance();
    public User user;
    public Timer pingTimer;


    private ApplicationController() {
    }


/*
            if(pMap.get(SinglePageApp.SINGLE_PAGE_APP) != null){
                SinglePageApp.load(pMap);
            }

            else if(pMap.get(UrlCommand.URL_COMMAND) != null){
                runGoogleAnalytics();
                startPinging();

                PageFlow.show(FrontPageController.getInstance());
                gh.setupKeyboardShortcuts();
                UrlCommand.execute(pMap);
            }
            */


    public void loadApplication() {
        try {
            Map<String, String> pMap = UrlUtil.getParameters();
            String token = pMap.get(UrlUtil.TOKEN);
            if (pMap.containsKey(UrlCommand.URL_COMMAND)) {
                UrlCommand.execute(pMap);
                
            }

            else if(token != null && !token.equals("")){

            }

            else {
                //runGoogleAnalytics();

                Window.enableScrolling(true);
                //PageFlow.activeController = MainPageController2.getInstance();
                PageFlow.show(FrontPageController.getInstance());
                //startPinging();

                //TODO animated gif while fetching resourcebundle and silent login.
                //history.loadFirstPage();
                UserManager.getInstance().silentLogIn();
                gh.setupSingleAppKeyboardShortcuts();;
            }

            //printBackEnd();
        }

        catch (Throwable t) {
            ApplicationController.getInstance().error(t, null);
        }

    }

    private void printBackEnd() {
/*        Service.printBackEnd(new CallBack<VoidResult>() {
            @Override
            public void success(VoidResult result) {

            }

            @Override
            public void fail(Throwable t) {
                t.printStackTrace();
            }
        });
        */
    }

    private void startPinging() {
        pingServer();//varm server up

        pingTimer = new Timer() {
            @Override
            public void run() {
                pingServer();
            }
        };
        pingTimer.scheduleRepeating(1000 * 60);//ping every minute
    }

    private void pingServer() {
        String domain = Document.get().getDomain();
        if (domain.equals("localhost")) {
            return;
        }
        BBService.getUserByMail("dummy", new SilentCallBack<ListResult<User>>() {
            @Override
            public void success(ListResult<User> result) {
                //ignore
            }

            @Override
            public void fail(Throwable t) {
                //ignore
            }
        });
    }



    public void debug(String msg) {
        if (loggerPanel != null) {
            loggerPanel.setText(loggerPanel.getText() + msg + "\n");
        }
    }


    public void error(Throwable e, String msg) {
        //Service.log(LogAction.Type.system_error, msg, e);
        e.printStackTrace();
    }

    public void error(String msg) {
        //Service.log(LogAction.Type.system_error, msg, null);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User u) {
        this.user = u;
    }


    @SuppressWarnings({"AppEngineForbiddenCode"})
    public static native void resizeTo(final int width, final int height) /*-{
        $wnd.resizeTo(width, height);
    }-*/;

    @SuppressWarnings({"AppEngineForbiddenCode"})
    public native String getBrowserLanguage() /*-{
        return navigator.language;
    }-*/;

    @SuppressWarnings({"AppEngineForbiddenCode"})
    public static native void runGoogleAnalytics() /*-{
        try {
                $wnd.gaTrack = $wnd._gat._getTracker("UA-12456452-5");
                $wnd.gaTrack._trackPageview();
        } catch(err) {}
    }-*/;

    @SuppressWarnings({"AppEngineForbiddenCode"})
    public static native void runGoogleAnalytics(String pageName) /*-{
        try {
                $wnd.gaTrack._trackPageview(pageName);
        } catch(err) {}
    }-*/;


    @SuppressWarnings({"AppEngineForbiddenCode"})
    public static native void scrollUp() /*-{
        $wnd.scroll(0, 0);
    }-*/;


    @SuppressWarnings({"AppEngineForbiddenCode"})
    public static native void scrollDown() /*-{
        $wnd.scroll(0, 1000);
    }-*/;


    @SuppressWarnings({"AppEngineForbiddenCode"})
    public static native String getUserAgent() /*-{
        return navigator.userAgent.toLowerCase();
    }-*/;


    @SuppressWarnings({"AppEngineForbiddenCode"})

    public static native boolean showFeedbackDialog() /*-{

    var uservoiceOptions = {
    key: 'onlineclubmaster',
    host: 'onlineclubmaster.uservoice.com',
    forum: '75987',
    lang: 'en',
    showTab: false
  };

       $wnd.UserVoice.Popin.show(uservoiceOptions); return false;

    }-*/;
}
