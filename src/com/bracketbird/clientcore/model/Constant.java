package com.bracketbird.clientcore.model;


import com.bracketbird.clientcore.model.keys.ConstantId;

import java.io.Serializable;

/**
 *
 */
public class Constant extends Model<ConstantId> implements Serializable {
    private static final long serialVersionUID = 2922208791628076707L;

    private Integer group;
    private String name;
    private Integer value;


    public Constant() {
    }


    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Constant{" +
                ", group=" + group +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}