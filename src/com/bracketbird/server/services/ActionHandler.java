package com.bracketbird.server.services;


import com.bracketbird.client.appcontrol.ApplicationException;
import com.bracketbird.client.service.Action;
import com.bracketbird.client.service.Result;

/**
 *
 */
public interface ActionHandler <A extends Action, R extends Result>{

    public R execute(A action) throws ApplicationException;

    public Class<A> getActionType();


}
