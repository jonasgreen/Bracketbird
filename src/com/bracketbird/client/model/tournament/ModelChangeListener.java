package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.Model;

/**
 *
 */
public interface ModelChangeListener<M extends Model>{
    public void onChange(ModelChangeEvent<M> event);
}