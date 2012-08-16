package com.bracketbird.client;

import com.bracketbird.client.gui.rtc.health.LogPageController;
import com.google.gwt.core.client.*;
import com.google.gwt.user.client.rpc.*;
import com.bracketbird.clientcore.service.*;

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
                    LogPageController.getInstance().log(action.getClass().getName(), caught);
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
