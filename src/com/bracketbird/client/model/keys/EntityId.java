package com.bracketbird.client.model.keys;

import java.io.*;


/**
 *
 */
public abstract class EntityId implements Serializable {
    private static final long serialVersionUID = -7452197115337073558L;

    private String dataStoreName;
    private String encodedKey;

    public EntityId() {
    }

    public EntityId(String encodedKey, String dataStoreName) {
        this.encodedKey = encodedKey;
        this.dataStoreName = dataStoreName;
    }


    public String getEncodedKey() {
        return encodedKey;
    }

    public String getDataStoreName() {
        return dataStoreName;
    }


    @Override
    public String toString() {
        return "EntityId{" +
                this.getClass().getName() +
                ", dataStoreName='" + dataStoreName + '\'' +
                ", encodedKey='" + getEncodedKey() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntityId entityId = (EntityId) o;

        return encodedKey.equals(entityId.encodedKey);

    }

    @Override
    public int hashCode() {
        return encodedKey.hashCode();
    }

    public boolean isLocal() {
        try {
            Long.valueOf(encodedKey);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
}
