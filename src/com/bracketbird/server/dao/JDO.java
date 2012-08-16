package com.bracketbird.server.dao;

import com.google.appengine.api.datastore.*;
import com.bracketbird.clientcore.util.*;

import java.util.*;

/**
 *
 */
public abstract class JDO implements ToString{

    public abstract void setCreatedDate(Date createdDate);

    public abstract Date getCreatedDate();

    public abstract void setLastChangeDate(Date lastChangeDate);

    public abstract Date getLastChangeDate();

    public abstract Key getKey();

    public abstract void setKey(Key key);


    public void toString(StringBuffer sb, int tab){
        WriterIt.appendTab(sb, tab);
        sb.append(this.toString());
    }


}
