package com.bracketbird.server.repository;

import com.bracketbird.client.appcontrol.ApplicationException;
import com.bracketbird.client.model.Model;
import com.bracketbird.client.model.keys.EntityId;
import com.bracketbird.client.service.Finder;
import com.bracketbird.client.service.SingleFinder;
import com.bracketbird.server.dao.Dao;
import com.bracketbird.server.dao.JDO;
import com.google.appengine.api.datastore.Key;

import java.util.Collection;
import java.util.List;

/**
 *
 */
public abstract class Repository<J extends JDO, M extends Model, C extends Creater> {


    protected Converter<J, M, C> conv;
    protected Dao<J> dao;


    public Repository(Dao<J> dao, Converter<J, M, C> con) {
        this.conv = con;
        this.dao = dao;
    }

    public Converter<J, M, C> getConverter() {
        return conv;
    }

    public Dao<J> getDao() {
        return dao;
    }

    public Collection<M> findBy(Finder f) {
        return convertAndRelate(getDao().findBy(f));
    }

    public M create(C c) throws ApplicationException {
        J jdo = getDao().create(getConverter().convert(c));
        return getConverter().convert(jdo);
    }




    public boolean exist(EntityId key) {
        return getDao().exist(KeyFac.convert(key));
    }


    public M get(EntityId id) {
        return convertAndRelate(getDao().read(id));
    }

    public M get(Key key) {
        return convertAndRelate(getDao().read(key));
    }

    public Collection<M> getAll() {
        return convertAndRelate(getDao().readAll());
    }


    public void delete(EntityId id) {
        getDao().delete(id);
    }

    public void delete(Key key) {
        getDao().delete(key);
    }

    public M update(M model){
        J jdo = getDao().read(model.getId());
        getConverter().updateJDO(jdo, model);
        return convertAndRelate(getDao().update(jdo));
    }

    public void deleteAll(SingleFinder param) {
        getDao().deleteAll(param);
    }

    protected void deleteAll(Collection<J> jdos) {
        getDao().deleteAll(jdos);
    }

    public abstract Class<M> getRepositoryType();

    public M get(String encodedKey){
        return get(KeyFac.stringToKey(encodedKey));
    }



    protected List<M> convertAndRelate(Collection<J> jdos){
        return addRelations(convert(jdos), jdos);
    }

    protected List<M> convert(Collection<J> jdos){
        return getConverter().convertJDOs(jdos);
    }

    protected  M convert(J jdo){
        return getConverter().convert(jdo);
    }

    protected  M convertAndRelate(J jdo){
        return addRelations(getConverter().convert(jdo), jdo);
    }

    private List<M> addRelations(List<M> ts, Collection<J> jdos){
        int i = 0;
        for (J jdo : jdos) {
            addRelations(ts.get(i++), jdo);
        }
        return ts;
    }

    //overwrite this one
    protected  M addRelations(M t, J jdo){
        return t;
    }
}
