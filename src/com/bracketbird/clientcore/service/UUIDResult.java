package com.bracketbird.clientcore.service;


import java.util.List;


/**
 *
 */
public class UUIDResult implements Result{

    private static final long serialVersionUID = 4717783794377634909L;

    private List<String> uuids;

    public UUIDResult() {
    }

    public UUIDResult(List<String> uuids) {
        this.uuids = uuids;
    }

    public List<String> getUuids() {
        return uuids;
    }

    public void setUuids(List<String> uuids) {
        this.uuids = uuids;
    }
}