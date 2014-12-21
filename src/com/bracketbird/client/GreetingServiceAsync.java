package com.bracketbird.client;

import com.bracketbird.client.service.Action;
import com.bracketbird.client.service.Result;
import com.google.gwt.user.client.rpc.*;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

    

    void execute( Action<?> action, AsyncCallback<Result> callback );


}
