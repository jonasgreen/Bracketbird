package com.bracketbird.server.dao;

import com.bracketbird.server.*;
import com.google.appengine.api.datastore.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.keys.*;
import com.bracketbird.clientcore.service.*;

import javax.jdo.*;
import javax.jdo.Query;
import java.util.*;

/**
 *
 */
public class Dao<J extends JDO> {

    private Class jdoClass;

    public Dao(Class jdoClass) {
        this.jdoClass = jdoClass;
    }


    public J create(J jdo) {
        try {
            setCreatedDate(jdo);
            PersistenceManager pm = PMF.getPersistenceManager();
            try {
                J newJdo = pm.makePersistent(jdo);
                return pm.detachCopy(newJdo);
            }
            finally {
                PMF.close(pm);
            }
        }
        catch (Throwable t) {
            throw log("create", jdo, t);
        }
    }

    public Collection<J> createAll(Collection<J> jdos) {
        try {
            Date d = new Date();
            for (J jdo : jdos) {
                jdo.setCreatedDate(d);
            }
            PersistenceManager pm = PMF.getPersistenceManager();
            try {
                Collection<J> newJdo = pm.makePersistentAll(jdos);
                return pm.detachCopyAll(newJdo);
            }
            finally {
                PMF.close(pm);
            }
        }
        catch (Throwable t) {
            throw log("create", jdos, t);
        }
    }

    public J read(EntityId id) {
        return read(KeyFactory.stringToKey(id.getEncodedKey()));
    }

    public J read(Key id) {
        try {
            PersistenceManager pm = PMF.getPersistenceManager();
            try {
                J jdo = read(id, pm);
                return pm.detachCopy(jdo);
            }
            finally {
                PMF.close(pm);
            }
        }
        catch (Throwable t) {
            throw log("read", id, t);
        }
    }

    public Collection<J> updateAll(Collection<J> jdos) {
        try {
            setLastChangeDate(jdos);
            PersistenceManager pm = PMF.getPersistenceManager();
            try {
                Collection<J> newJdos = pm.makePersistentAll(jdos);
                return pm.detachCopyAll(newJdos);
            }
            finally {
                PMF.close(pm);
            }
        }
        catch (Throwable t) {
            throw log("updateAll", jdos, t);
        }
    }

    public J update(J jdo) {
        try {
            setLastChangeDate(jdo);
            PersistenceManager pm = PMF.getPersistenceManager();
            try {
                J newJdo = pm.makePersistent(jdo);
                return pm.detachCopy(newJdo);
            }
            finally {
                PMF.close(pm);
            }
        }
        catch (Throwable t) {
            throw log("update", jdo, t);
        }
    }


    public Collection<J> readAll() {
        try {
            PersistenceManager pm = PMF.getPersistenceManager();
            try {
                String query = "select from " + jdoClass.getName();
                //noinspection unchecked
                List<J> list = (List<J>) pm.newQuery(query).execute();
                return pm.detachCopyAll(list);
            }
            finally {
                PMF.close(pm);
            }
        }
        catch (Throwable t) {
            throw log("readAll", null, t);
        }
    }


    public void delete(EntityId id) {
        delete(KeyFactory.stringToKey(id.getEncodedKey()));
    }


    public void delete(Key key) {
        try {
            PersistenceManager pm = PMF.getPersistenceManager();
            try {
                J jdo = read(key, pm);
                if (jdo != null) {
                    pm.deletePersistent(jdo);
                }
            }
            finally {
                PMF.close(pm);
            }
        }
        catch (Throwable t) {
            throw log("delete", key, t);
        }
    }

    public boolean exist(Key key) {
        try {
            PersistenceManager pm = PMF.getPersistenceManager();
            try {
                return pm.getObjectById((Class<J>) jdoClass, key) != null;
            }
            catch (javax.jdo.JDOObjectNotFoundException e) {
                return false;
            }
            finally {
                PMF.close(pm);
            }
        }
        catch (Throwable t) {
            throw log("exist", key, t);
        }
    }

    public void delete(J jdo) {
        try {
            PersistenceManager pm = PMF.getPersistenceManager();
            try {
                pm.deletePersistent(jdo);
            }
            finally {
                PMF.close(pm);
            }
        }
        catch (Throwable t) {
            throw log("delete", jdo, t);
        }
    }

    public void deleteAll(Collection<J> list) {
        try {
            PersistenceManager pm = PMF.getPersistenceManager();
            try {
                pm.deletePersistentAll(list);
            }
            finally {
                PMF.close(pm);
            }
        }
        catch (Throwable t) {
            throw log("deleteAll", list, t);
        }
    }


    public Collection<J> deleteAll(SingleFinder param) {
        Collection<J> all = findBy(param);
        deleteAll(all);
        return all;
    }

    public Collection<J> findBy(Finder f) {
        try {

            PersistenceManager pm = PMF.getPersistenceManager();
            try {
                Query query = pm.newQuery(jdoClass);
                query.setFilter(f.getFilter());
                if (f.getOrdering() != null) {
                    query.setOrdering(f.getOrdering());
                }

                StringBuffer params = new StringBuffer();
                f.buildParameterList(params);

                query.declareParameters(params.toString());


                Map<String, Object> values = new HashMap<String, Object>();
                f.buildValueMap(values);

                //entity keys replaced with app engine keys
                replaceEntityKeys(values);


                List<J> jdos = (List<J>) query.executeWithMap(values);

                return pm.detachCopyAll(jdos);
            }
            finally {
                PMF.close(pm);
            }
        }
        catch (Throwable t) {
            throw log("findBy", f, t);
        }
    }

    private void replaceEntityKeys(Map<String, Object> values) {
        for (String key : values.keySet()) {
            Object value = values.get(key);
            if(value instanceof EntityId){
                EntityId id = (EntityId) value;
                values.put(key, KeyFactory.stringToKey(id.getEncodedKey()));
            }
        }
    }


    private J read(Key key, PersistenceManager pm) {
        J j = pm.getObjectById((Class<J>) jdoClass, key);
        return pm.detachCopy(j);
    }


    private void setCreatedDate(J jdo) {
        jdo.setCreatedDate(new Date());
    }

    private void setLastChangeDate(Collection<J> jdos) {
        Date today = new Date();
        for (J j : jdos) {
            j.setLastChangeDate(today);
        }
    }

    private void setLastChangeDate(J j) {
        j.setLastChangeDate(new Date());
    }


    private SystemException log(String type, Object obj, Throwable t) {
        String s = "DAO: " + type + ". " + t.getClass().getName() + (obj != null ? " Object=" + obj.toString() : "");
        Logger.log(s, t);
        return new SystemException(s);
    }

}
