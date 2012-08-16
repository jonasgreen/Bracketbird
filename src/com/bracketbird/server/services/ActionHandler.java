package com.bracketbird.server.services;


import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public interface ActionHandler <A extends Action, R extends Result>{

    public R execute(A action) throws ApplicationException;

    public Class<A> getActionType();

    public Integer getClientLanguage();

    public void setClientLanguage(Integer clientLanguage);
}
