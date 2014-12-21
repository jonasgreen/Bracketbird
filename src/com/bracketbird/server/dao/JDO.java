package com.bracketbird.server.dao;

import com.google.appengine.api.datastore.Key;

import java.util.Date;

/**
 *
 */
public abstract class JDO {

    public abstract void setCreatedDate(Date createdDate);

    public abstract Date getCreatedDate();

    public abstract void setLastChangeDate(Date lastChangeDate);

    public abstract Date getLastChangeDate();

    public abstract Key getKey();

    public abstract void setKey(Key key);




}
