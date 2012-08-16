package com.bracketbird.clientcore.service;


/**
 *
 */
public class LogAction extends AbstractAction  implements Action<VoidResult>{

    private static final long serialVersionUID = 2210150240885850407L;

    private String logMessage;


    public LogAction() {
    }

    public LogAction(String logMessage) {
        this.logMessage = logMessage;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

}
