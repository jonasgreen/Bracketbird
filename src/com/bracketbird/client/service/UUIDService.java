package com.bracketbird.client.service;

import com.google.gwt.user.client.Timer;

import java.util.*;

/**
 *
 */
public class UUIDService {

    private int SLEEP = 30000;
    private List<String> uuids = new ArrayList<String>();

    Timer timer;
    private static UUIDService instance;

    private UUIDService() {
        start();
    }

    public static UUIDService getInstance() {
        if (instance == null) {
            instance = new UUIDService();
        }
        return instance;
    }

    

    private void start() {
        loadUUIDs();
        timer = new Timer() {
            @Override
            public void run() {
                if (uuids.size() < 30) {
                    loadUUIDs();
                }
            }
        };
        timer.scheduleRepeating(SLEEP);
    }

    private void loadUUIDs() {
        /*DeferredCommand.addCommand(new Command() {
            public void handle() {
                Service.getUUID(50, new SilentCallBack<UUIDResult>() {
                    @Override
                    public void success(UUIDResult result) {
                        uuids.addAll(result.getUuids());
                    }

                    @Override
                    public void fail(Throwable t) {
                    }
                });
            }
        });
        */
    }

    public String next() {
        if (uuids.isEmpty()) {
            loadUUIDs();
            return null;
        }
        else {
            return uuids.remove(0);
        }
    }
}
