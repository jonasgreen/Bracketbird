package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.Model;

/**
 *
 */
public abstract class ModelChangeEvent<M extends Model> {

    private M model;

    public ModelChangeEvent(M model) {
        this.model = model;
    }

    public M getModel() {
        return model;
    }
}