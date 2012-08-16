package com.bracketbird.client;



import com.google.gwt.user.client.rpc.*;
import com.bracketbird.clientcore.service.*;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
    Result execute( Action<?> action ) throws Exception;

}
