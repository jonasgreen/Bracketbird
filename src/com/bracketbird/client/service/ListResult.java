package com.bracketbird.client.service;

import com.bracketbird.client.model.Model;

import java.util.*;


/**
 *
 */
public class ListResult<M extends Model> implements Result{
    private static final long serialVersionUID = -2615108262997306218L;

    private Collection<M> result;

    public ListResult(Collection<M> result) {
        this.result = result;
    }

    public ListResult() {
    }

    public Collection<M> getResult() {
        return result;
    }

    public void setResult(Collection<M> result) {
        this.result = result;
    }
}