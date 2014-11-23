package com.bracketbird.clientcore.model;

import com.bracketbird.clientcore.model.keys.EntityId;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public abstract class Model<E extends EntityId> implements Serializable{
    private static final long serialVersionUID = 8465595286369175756L;

    //lognumber from rtcevent log. Used to see which objects are newest.
    private Long eventLogId;
    private E id;
    private Date createdDate;
    private Date lastChangeDate;

    public Model(){
    }

    public E getId() {
        return id;
    }


    public void setId(E id) {
        this.id = id;
    }

    public Long getEventLogId() {
        return eventLogId;
    }

    public void setEventLogId(Long eventLogId) {
        this.eventLogId = eventLogId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if(!(o instanceof Model)){
            return false;
        }

        Model model = (Model) o;

        if (id != null ? !id.equals(model.id) : model.id != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    public int compareByEventLogNr(Model m) {
        if (this.getEventLogId() == null && m.getEventLogId() == null) {
            return 0;
        }
        if (this.getEventLogId() == null) {
            return 1;
        }
        if (m.getEventLogId() == null) {
            return -1;
        }
        return new Long(this.eventLogId).compareTo(m.getEventLogId());
    }



}
