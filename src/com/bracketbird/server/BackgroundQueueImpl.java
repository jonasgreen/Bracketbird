package com.bracketbird.server;

import com.bracketbird.server.services.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.service.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 * Implementing queue jobs /gwtmodule/emaillist
 */
public class BackgroundQueueImpl extends HttpServlet {


    private static final long serialVersionUID = 8354270027885124245L;

    public static String JOB = "jobType";

    public static String JOB_SUBSCRIPTION = "job_subscription";
    public static String JOB_EVENT = "job_event";


    public static String EMAIL_CONTENT = "emailcontent";

    public static String WALLEVENT_ID = "walleventid";
    public static String CLUB_ID = "clubid";


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
            Logger.log("BackgroundQueueImpl: 1");

            //only requests from google cron job maschine should be handled
            //String xApp = r.getHeader("X-AppEngine-Cron");
            Logger.log("BackgroundQueueImpl: 2");

            String cronJob = r.getParameter(JOB);

            Logger.log("BackgroundQueueImpl: 4 job="+cronJob);

            
            Action action;

            //only supports subscriptions
            if (JOB_SUBSCRIPTION.equals(cronJob)) {
                String mailContent = r.getParameter(EMAIL_CONTENT);
                String subscriptionId = r.getParameter(JOB_SUBSCRIPTION);
                action = new EmailListJobAction(subscriptionId, mailContent);
            }

            else{
                Logger.log("QueueEmailList: job not supported: "+cronJob);
                return;
            }

            ActionHandlerRegistry.executeAction(action);

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