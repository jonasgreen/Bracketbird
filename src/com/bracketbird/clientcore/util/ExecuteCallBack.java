package com.bracketbird.clientcore.util;

/**
 *
 */
public abstract class ExecuteCallBack {

    private Executer executer;

    public abstract void onSucces();

    public abstract void onFail();


    public Executer getExecuter() {
        return executer;
    }

    public void setExecuter(Executer executer) {
        this.executer = executer;
    }
}
