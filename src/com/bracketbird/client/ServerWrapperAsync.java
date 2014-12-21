package com.bracketbird.client;


import com.bracketbird.client.service.Action;
import com.bracketbird.client.service.Result;
import com.google.gwt.user.client.rpc.*;

/**
 *
 */
public interface ServerWrapperAsync {

    <A extends Action<R>, R extends Result> void execute( A action, AsyncCallback<R> callback );

}
