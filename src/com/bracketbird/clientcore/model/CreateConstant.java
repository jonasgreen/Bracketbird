package com.bracketbird.clientcore.model;


import java.io.Serializable;


/**
 *
 */
public class CreateConstant extends Creater implements Serializable {

    private static final long serialVersionUID = -4720838676150945287L;

    private GroupConstants group;
    private String name;
    private Long id;

    public CreateConstant() {
    }

    public CreateConstant(GroupConstants group, String name) {
        this.group = group;
        this.name = name;
    }


    public GroupConstants getGroup() {
        return group;
    }

    public void setGroup(GroupConstants group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}