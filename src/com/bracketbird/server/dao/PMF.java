package com.bracketbird.server.dao;

import com.bracketbird.client.appcontrol.SystemException;

import javax.jdo.FetchPlan;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class PMF {


    private static Map<Thread, PersistenceManager> pmMap = new HashMap<Thread, PersistenceManager>();

    private static final PersistenceManagerFactory pmfInstance = JDOHelper
            .getPersistenceManagerFactory("transactions-optional");


    private PMF() {
    }


    private static PersistenceManager getGlobalPersistenceManager() {
        PersistenceManager pm = pmMap.get(Thread.currentThread());
        if (pm == null) {
            pm = pmfInstance.getPersistenceManager();
            pmMap.put(Thread.currentThread(), pm);
            pm.setDetachAllOnCommit(true);
            FetchPlan fp = pm.getFetchPlan();
            fp.setMaxFetchDepth(-1);

        }
        
        return pm;
    }

    public static void startTransaction() {
        PersistenceManager pm = getGlobalPersistenceManager();
        pm.currentTransaction().begin();
    }

    public static void endTransaction() {
        PersistenceManager pm = pmMap.remove(Thread.currentThread());
        if (pm == null) {
            throw new SystemException("Trying to end transaction on persistence manager, that does not exist");
        }

        if (pm.currentTransaction().isActive()) {
            pm.currentTransaction().rollback();
        }
        pm.close();
    }

    public static void commitTransaction() {
        PersistenceManager pm = pmMap.get(Thread.currentThread());
        if (pm == null) {
            throw new SystemException("Trying to commit transaction on persistence manager, that does not exist");
        }
        pm.currentTransaction().commit();
    }




    public static PersistenceManager getPersistenceManager() {
        PersistenceManager pm = pmMap.get(Thread.currentThread());
        if (pm == null) {
            pm = pmfInstance.getPersistenceManager();
            FetchPlan fp = pm.getFetchPlan();
            fp.setMaxFetchDepth(-1);
        }
        pm.setDetachAllOnCommit(true);
        return pm;
    }


    public static void close(PersistenceManager pm) {
        if (pmMap.get(Thread.currentThread()) == null) {
            pm.close();
        }
    }


}
