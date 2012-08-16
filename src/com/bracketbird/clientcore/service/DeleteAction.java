package com.bracketbird.clientcore.service;


import com.bracketbird.clientcore.model.keys.*;

/**
 *
 */
public class DeleteAction extends AbstractAction  implements Action<VoidResult>{
    private static final long serialVersionUID = -6917837239422306676L;

    private FindIn findIn;
    private EntityId indexKey;

    public DeleteAction() {
    }

    public DeleteAction(FindIn findIn, EntityId indexKey) {
        this.findIn = findIn;
        this.indexKey = indexKey;
    }

    public FindIn getFindIn() {
        return findIn;
    }

    public void setFindIn(FindIn findIn) {
        this.findIn = findIn;
    }

    public EntityId getIndexKey() {
        return indexKey;
    }

    public void setIndexKey(EntityId indexKey) {
        this.indexKey = indexKey;
    }
}