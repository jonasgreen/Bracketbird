package com.bracketbird.server.jdo.club;

import com.google.appengine.api.datastore.Key;
import com.bracketbird.server.dao.JDO;

import javax.jdo.annotations.*;
import java.util.Date;

/**
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class WallEventJDO extends JDO {


	
	
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    //foreign key
    @Persistent
    private Key clubKey;

    @Persistent
    private Date eventDate;

    @Persistent
    private Integer repeating;

    @Persistent
    private String message;

    @Persistent
    private String title;

    @Persistent
    private Integer notificationLevel;

    @Persistent
    private Boolean notificationSent;

    @Persistent
    private Date notificationDate;

    @Persistent
    private Integer timeZoneOffset;


    @Persistent
    private Date createdDate;

    @Persistent
    private Date lastChangeDate;

    @Persistent
    private String createdBy;

    public WallEventJDO() {

    }


    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Key getClubKey() {
        return clubKey;
    }

    public void setClubKey(Key clubKey) {
        this.clubKey = clubKey;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getRepeating() {
        return repeating;
    }

    public void setRepeating(Integer repeating) {
        this.repeating = repeating;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getNotificationLevel() {
        return notificationLevel;
    }

    public void setNotificationLevel(Integer notificationLevel) {
        this.notificationLevel = notificationLevel;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    public Boolean getNotificationSent() {
        return notificationSent;
    }

    public void setNotificationSent(Boolean notificationSent) {
        this.notificationSent = notificationSent;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    public Integer getTimeZoneOffset() {
        return timeZoneOffset;
    }

    public void setTimeZoneOffset(Integer timeZoneOffset) {
        this.timeZoneOffset = timeZoneOffset;
    }
}