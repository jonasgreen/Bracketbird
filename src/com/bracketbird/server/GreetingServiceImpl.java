package com.bracketbird.server;

import com.bracketbird.client.*;
import com.bracketbird.client.service.Action;
import com.bracketbird.client.service.Result;
import com.bracketbird.server.services.*;
import com.google.gwt.user.server.rpc.*;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    public Result execute(Action action) throws Exception {
        ActionHandlerRegistry.setRequestSettings(getThreadLocalRequest());
        return ActionHandlerRegistry.executeAction(action);
    }

    public void init() {
        ActionHandlerRegistry.init(getServletContext());
    }


}
