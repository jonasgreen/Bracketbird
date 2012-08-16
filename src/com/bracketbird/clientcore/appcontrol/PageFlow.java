package com.bracketbird.clientcore.appcontrol;

import com.bracketbird.client.HistorySupport;
import com.bracketbird.clientcore.gui.*;

import java.util.*;

/**
 *
 */
public class PageFlow {

    private static HashMap<PageController, PageController> usedControllers = new HashMap<PageController, PageController>();

    public static boolean scrollUp = true;
    public static PageController activeController = null;


    public static void show(PageController pc) {
        boolean succes = showPage(pc);
        if (succes && scrollUp) {
            //ApplicationController.scrollUp();
        }
    }

    public static void showFromHistory(PageController pc) {
        showPage(pc);
    }

    public static void popUp(PageController pc) {
        popUp(pc, true);
    }


    public static void popUp(PageController pc, boolean checkPermission) {
        if (checkPermission) {
            //check permission
        }
        PopupManager.show(pc);
    }


    public static void clearWhenLoggedOut() {
        for (PageController c : usedControllers.keySet()) {
            c.clear();

        }
    }

    public static void clearWhenLoggedOutOfClub() {
        /*for (PageController c : usedControllers.keySet()) {
            if (c.getLegalState().getValue() > UserStateConstant.LOGGED_IN.getValue()) {
                c.clear();
            }
        }
        */
    }


    private static boolean showPage(PageController pc) {
        //tjeck permission - all the way up

        //filling list with controller paths (parents parent etc)
        List<PageController> newPcList = new ArrayList<PageController>();
        List<PageController> activePcList = new ArrayList<PageController>();

        fillControllerTree(pc, newPcList);
        fillControllerTree(activeController, activePcList);

        //find common parent between showing and non-showing
        PageController commonPc = getCommon(newPcList, activePcList);

        //cut the lists at common controller
        newPcList = cutListAtCommon(newPcList, commonPc);
        if (newPcList.isEmpty()) {
            newPcList.add(pc);
        }
        activePcList = cutListAtCommon(activePcList, commonPc);


        //unload until common parent - bottom to top
        unload(activePcList);

        //show until comon parent - top down
        show(newPcList);

        return true;
    }

    private static List<PageController> cutListAtCommon(List<PageController> newPcList, PageController commonPc) {
        List<PageController> list = new ArrayList<PageController>();
        for (PageController pc : newPcList) {
            if (pc == commonPc) {
                return list;
            }
            list.add(pc);
        }
        return list;
    }


    private static void show(List<PageController> toShow) {
        PopupManager.hide();
        InfoManager.hideInfo();

        int i = toShow.size() - 1;
        while (i >= 0) {
            //show
            PageController pc = toShow.get(i--);
            usedControllers.put(pc, pc);

            PageController parent = pc.getParent();
            if (parent != null) {
                activeController = pc;
                parent.setChild(pc);
                parent.setSelectedMenu(pc.getMenu());
                parent.getPage().setSubPageHolder(pc.getPage());

                runGoogleAnalytics(pc.getHistoryName());


                HistorySupport.getInstance().addHistory(pc);

                if (pc.makeHistory()) {
                    //HistorySupport.getInstance().addHistory(pc);
                }
                pc.afterLoad();
            }

        }
    }


    private static void unload(List<PageController> toUnload) {
        for (PageController pc : toUnload) {
            pc.unload();
        }
    }


    private static PageController getCommon(List<PageController> listNew, List<PageController> listOld) {
        for (PageController pcNew : listNew) {
            for (PageController pcOld : listOld) {
                if (pcNew == pcOld) {
                    return pcNew;
                }
            }
        }
        return null;
    }


    private static void fillControllerTree(PageController pc, List<PageController> list) {
        if (pc == null) {
            return;
        }
        list.add(pc);
        fillControllerTree(pc.getParent(), list);
    }


    private static void runGoogleAnalytics(String siteName) {
        //ApplicationController.runGoogleAnalytics(siteName);
    }


    public static PageController getActiveController() {
        return activeController;
    }
}
