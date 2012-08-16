package com.bracketbird.clientcore.util;

/**
 *
 */
public abstract class Job {
    private int id = -1;
    private SuccesFailureHandler handler = null;
    private final Executer executer;

    private boolean succes;
    private int statusCode = 0;
    private String errorMsg ="";

    private String inProgressText ="";

    protected Job(Executer executer, String inProgressText) {
        this.executer = executer;
        this.inProgressText = inProgressText;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public abstract void run();


    public SuccesFailureHandler getHandler() {
        return handler;
    }

    public void setHandler(SuccesFailureHandler handler) {
        this.handler = handler;
    }

    public void finished(boolean succes){
        this.succes = succes;
        executer.finished(this, succes);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInProgressText() {
        return inProgressText;
    }
}
