package com.bracketbird.clientcore.service;

import com.bracketbird.clientcore.appcontrol.*;

/**
 *
 */
public abstract class SilentCallBack<T> extends CallBack<T> {


    private Action retry;
    private int retries = 0;

    public void startProgressBar() {
        //startProgressBar(null);
    }

    public void startProgressBar(String text) {
        //InfoManager.startProgressBar(text);
    }


    public void onFailure(Throwable caught) {
        //retries a couple of times
        if (retry != null && retries++ < 2) {
            //Service.resend(retry, this);
            return;
        }

        if (caught instanceof ApplicationException) {
            fail(caught);
        }
        else {
            fail(caught);
        }
    }


    public void setRetry(Action a) {
        retry = a;
    }


    public void onSuccess(T result) {
        success(result);
    }


    public abstract void success(T result);

    public abstract void fail(Throwable t);
}
