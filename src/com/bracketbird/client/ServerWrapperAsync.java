package com.bracketbird.client;


import com.google.gwt.user.client.rpc.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public interface ServerWrapperAsync {

    <A extends Action<R>, R extends Result> void execute( A action, AsyncCallback<R> callback );

}
