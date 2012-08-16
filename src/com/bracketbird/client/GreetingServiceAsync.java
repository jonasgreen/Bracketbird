package com.bracketbird.client;

import com.google.gwt.user.client.rpc.*;
import com.bracketbird.clientcore.service.*;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

    

    void execute( Action<?> action, AsyncCallback<Result> callback );


}
