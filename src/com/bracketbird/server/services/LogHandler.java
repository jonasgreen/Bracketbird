package com.bracketbird.server.services;

import com.bracketbird.server.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class LogHandler extends AbstractActionHandler implements ActionHandler<LogAction, VoidResult> {


    public VoidResult execute(LogAction action) throws ApplicationException {
        log(action);
        return new VoidResult();
    }

    private void log(LogAction a) {
        StringBuffer sb = new StringBuffer("FROM CLIENT: ");
        sb.append(" ").append(a.getLogMessage());
        Logger.log(sb.toString());


    }


    public Class<LogAction> getActionType() {
        return LogAction.class;
    }


}