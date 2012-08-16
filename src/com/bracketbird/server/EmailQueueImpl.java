package com.bracketbird.server;


import com.bracketbird.client.service.*;
import com.bracketbird.server.services.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.keys.*;
import com.bracketbird.clientcore.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class EmailQueueImpl extends HttpServlet {


    private static final long serialVersionUID = 8354270027885124245L;

    public static String MSG_BODY = "content";
    public static String FROM_NAME = "from_name";
    public static String USER_ID = "user_id";

    public static String MUST_BE_SEND = "must_be_send";
    public static String SUBJECT = "title";


    public void init() {
        ActionHandlerRegistry.init(getServletContext());
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request);
    }


    private void handleRequest(HttpServletRequest r) {
        try {
            String mustBeSend = r.getParameter(MUST_BE_SEND);
            //supports only two retries
            String retried = r.getHeader("X-AppEngine-TaskRetryCount");
            if (StringUtil.isEmpty(mustBeSend) && (retried != null && retried.equals("2"))) {
                Logger.log("EmailQueue exceeded maximum number of tries for job");
                return;
            }

            UserId userId = new UserId(r.getParameter(USER_ID));
            String fromName = r.getParameter(FROM_NAME);
            String content = r.getParameter(MSG_BODY);
            String title = r.getParameter(SUBJECT);
            ActionHandlerRegistry.executeAction(new EmailAction(userId,  fromName, content, title));

        }
        catch (Exception e) {
            throw new SystemException(e.getMessage());
        }
    }


/*
    *  X-AppEngine-QueueName, the name of the queue (possibly default)
    * X-AppEngine-TaskName, the name of the task, or a system-generated unique ID if no name was specified
    * , the number of times this task has been retried; for the first attempt, this value is 0




*/


}