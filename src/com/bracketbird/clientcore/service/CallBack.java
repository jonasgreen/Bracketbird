package com.bracketbird.clientcore.service;

import com.google.gwt.user.client.rpc.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public abstract class CallBack<T> implements AsyncCallback<T> {

    private Action retry;
    private int retries = 0;

    public void startProgressBar() {
        startProgressBar(null);
    }

    public void startProgressBar(String text) {
        InfoManager.startProgressBar(text);
    }


    public void onFailure(Throwable caught) {
        //retries a couple of times
        if(retry != null && retries++ < 2){
            //Service.resend(retry, this);
            return;
        }

        InfoManager.stopProgressBar();
        if (caught instanceof ApplicationException) {

            InfoManager.showInfo(caught.getMessage());
            fail(caught);
        }
        else {
            InfoManager.showError("An error occurred, try again.");
            fail(caught);
        }
    }
    

    public void setRetry(Action a){
        retry = a;
    }


    public void onSuccess(T result) {
        InfoManager.stopProgressBar();
        success(result);
    }

    public abstract void success(T result);

    public abstract void fail(Throwable t);
}
