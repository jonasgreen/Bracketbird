package com.bracketbird.client.model.tournament;


import com.bracketbird.clientcore.model.*;

/**
 *
 */
public class ResultChangeEvent<M extends Model> extends ModelChangeEvent {
    
    public ResultChangeEvent(M model) {
        super(model);
    }
}