package com.bracketbird.client.model.tournament;

import com.bracketbird.clientcore.model.*;

/**
 *
 */
public interface ModelChangeListener<M extends Model>{
    public void onChange(ModelChangeEvent<M> event);
}