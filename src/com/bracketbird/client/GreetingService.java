package com.bracketbird.client;



import com.bracketbird.client.service.Action;
import com.bracketbird.client.service.Result;
import com.google.gwt.user.client.rpc.*;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
    Result execute( Action<?> action ) throws Exception;

}
