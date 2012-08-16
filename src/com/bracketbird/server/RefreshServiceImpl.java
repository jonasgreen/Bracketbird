package com.bracketbird.server;

import com.bracketbird.client.service.*;
import com.bracketbird.server.services.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 */
public class RefreshServiceImpl extends HttpServlet {
    private static final long serialVersionUID = 274804192869160632L;


    public void init() {
        ActionHandlerRegistry.init(getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request);
    }


    private void handleRequest(HttpServletRequest request) {
        try {
            Logger.log("Executing refresh cron job");
            ActionHandlerRegistry.executeAction(new RefreshAction());
        }
        catch (Exception e) {
            Logger.log("Cron job", e);

        }

    }

}





