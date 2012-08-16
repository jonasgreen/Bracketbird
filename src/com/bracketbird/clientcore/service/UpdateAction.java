package com.bracketbird.clientcore.service;

import com.bracketbird.clientcore.model.*;


/**
 *
 */
public class UpdateAction <M extends Model> extends AbstractAction  implements Action<SingleResult>{
    private static final long serialVersionUID = -8239767251624196685L;

    private FindIn findIn;
    private M model;

    public UpdateAction() {
    }

    public UpdateAction(FindIn findIn, M model) {
        this.findIn = findIn;
        this.model = model;
    }

    public FindIn getFindIn() {
        return findIn;
    }

    public void setFindIn(FindIn findIn) {
        this.findIn = findIn;
    }

    public M getModel() {
        return model;
    }

    public void setModel(M model) {
        this.model = model;
    }
}