package com.bracketbird.clientcore.util;

import com.bracketbird.clientcore.gui.*;

import java.util.*;

/**
 *
 */
public class Executer {
    private List<Job> jobs = new ArrayList<Job>();

    private SuccesFailureHandler handler = null;
    private ProgressBar progressBar = new ProgressBar();


    public Executer() {

    }

    public void setHandler(SuccesFailureHandler handler) {
        this.handler = handler;
    }

    public void add(Job job) {
        job.setId(jobs.size());
        jobs.add(job);
    }

    public void execute() {
        progressBar.setText("Executing " + jobs.size() + " tasks");
        progressBar.show(new DialogComponent.DialogCallBack() {
            public void onClose(DialogComponent.Response r) {
                if (r == DialogComponent.Response.CANCEL) {
                    //TODO - stop rest of the jobs
                }

            }
        });

        run(jobs.get(0));
    }

    public void finished(Job job, boolean succes) {
        if (!succes) {
            progressBar.stop();

            if (job.getHandler() != null) {
                job.getHandler().failure();
            }
            if (handler != null) {
                handler.failure();
            }
        }
        else {
            if (job.getHandler() != null) {
                job.getHandler().succes();
            }
            if (isLastJob(job)) {
                progressBar.stop();
                if (handler != null) {
                    handler.succes();
                }
            }
            else {
                run(jobs.get(job.getId() + 1));
            }
        }
    }

    private void run(Job j) {
        updateProgressBar(j);
        j.run();
    }

    private void updateProgressBar(Job j) {
        progressBar.setText(String.valueOf(j.getId() + 1) + "/" + jobs.size() + " " + j.getInProgressText());
    }

    private boolean isLastJob(Job job) {
        return job.getId() == jobs.size() - 1;
    }


}
