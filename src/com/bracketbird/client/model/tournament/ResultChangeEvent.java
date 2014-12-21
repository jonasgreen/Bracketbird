package com.bracketbird.client.model.tournament;


import com.bracketbird.client.model.Model;

/**
 *
 */
public class ResultChangeEvent<M extends Model> extends ModelChangeEvent {
    
    public ResultChangeEvent(M model) {
        super(model);
    }
}