package com.bracketbird.client;

import com.bracketbird.client.service.Action;
import com.bracketbird.client.service.Result;
import com.google.gwt.core.client.*;
import com.google.gwt.user.client.rpc.*;

/**
 *
 */
public class ServerWrapper implements ServerWrapperAsync {

    private static final GreetingServiceAsync server = GWT.create(GreetingService.class);

    private static boolean inFailure = false;

    public ServerWrapper() {
    }

    public <A extends Action<R>, R extends Result> void execute(final A action, final AsyncCallback<R> callback ) {

        server.execute( action, new AsyncCallback<Result>() {
            public void onFailure( Throwable caught ) {
                if(!inFailure){
                    inFailure = true;
                }
                callback.onFailure( caught );
            }

            public void onSuccess( Result result ) {
                inFailure = false;
                callback.onSuccess( ( R ) result );
            }
        } );
    }

}
