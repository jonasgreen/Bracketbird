package com.bracketbird.client.service;


import com.bracketbird.client.model.keys.EntityId;

import java.io.*;

/**
 *
 */
public class EntityIdSingleFinder extends SingleFinder implements Serializable {
    private static final long serialVersionUID = -9201660323650579466L;

    private EntityId value;

    public EntityIdSingleFinder() {
        super();
    }

    public EntityId getValue() {
        return value;
    }

    public void setValue(EntityId value) {
        this.value = value;
    }

    public EntityIdSingleFinder(String paramClass, String colNam, EntityId value) {
        this(paramClass, colNam, Operator.EQUAL_TO, value);
    }

    public EntityIdSingleFinder(String paramClass, String colNam, Operator operator, EntityId value) {
        super(paramClass, colNam, operator);
        this.value = value;
    }



}