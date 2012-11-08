package com.bracketbird.client.gui.rtc.health;


import com.bracketbird.client.gui.rtc.RunningTournamentPageController;
import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.model.UserStateConstant;
import com.bracketbird.client.service.BBService;
import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.gui.FlowComponent;
import com.bracketbird.clientcore.gui.LabelComponent;
import com.bracketbird.clientcore.gui.MenuComponent;
import com.bracketbird.clientcore.gui.MenuImageAndTextComponent;
import com.bracketbird.clientcore.service.LogAction;
import com.bracketbird.clientcore.style.TextLayout;

import java.util.Date;

/**
 *
 */
public class LogPageController extends PageController<LogPage> {

    private static LogPageController instance;
    public static String HISTORY_NAME = "logpage";


    private LogPageController() {
        super(RunningTournamentPageController.getInstance(), HISTORY_NAME);
    }

    public static LogPageController getInstance() {
        if (instance == null) {
            instance = new LogPageController();
        }
        return instance;
    }

    public LogPage newInstance() {
        return new LogPage();
    }

    public boolean makeHistory() {
        return false;
    }

    public MenuComponent newMenuInstance() {
        return new MenuImageAndTextComponent("Oscilloscope.png", "Activity and log");
    }

    public UserStateConstant getLegalState() {
        return UserStateConstant.LOGGED_OUT;
    }


    public void log(REvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append(" [").append(event.isFromClient() ? "client] ------ " : "server] ----- ").append(event.getEventName());

        if (!event.isFromClient() && event.getTimeStamp() != null) {
            Date rightNow = new Date(event.getTimeStamp());
            int hour = rightNow.getHours();
            int min = rightNow.getMinutes();
            sb.append(" (").append(event.getEventId()).append(", ").append(hour).append(":").append(min < 10 ? ("0" + min) : min).append(")");
        }
        log(sb.toString());
    }

    public void log(String text) {
        StringBuilder sb = new StringBuilder();
        Date rightNow = new Date();

        int hour = rightNow.getHours();
        int min = rightNow.getMinutes();
        sb.append(hour).append(":").append(min < 10 ? ("0" + min) : min).append(" - ");
        sb.append(text);

        FlowComponent div = new FlowComponent();
        div.add(new LabelComponent(sb.toString()));
        getPage().getContent().add(div);
    }


    public void log(String message, Throwable caught) {
        StringBuilder sb = new StringBuilder("Error: ").append(message).append(" - ");
        sb.append(caught.getMessage());
        if (caught.getCause() != null) {
            sb.append("\n").append(caught.getMessage());
        }
        BBService.log(sb.toString());

        log(sb.toString());
    }
}