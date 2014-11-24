package com.bracketbird.client.model;


import com.bracketbird.client.gui.rtc.event.*;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.clientcore.model.*;

/**
 *
 */
public class Team extends Model<TeamId> {
    private static final long serialVersionUID = -8413373363468003258L;

    public ModelHandlerList<String> nameHandlers;
    public ModelHandlerList<Integer> seedingHandlers;

    protected String name;
    protected Integer seeding;


    public Team(String name, int seeding) {
        super();
        this.name = name;
        this.seeding = seeding;

        nameHandlers = new ModelHandlerList<String>("Team "+name + " (nameHandler)");
        seedingHandlers = new ModelHandlerList<Integer>("Team "+name + " (seedingHandler)");

    }

    public Integer getSeeding() {
        return seeding;
    }

    public void setSeeding(Integer seeding) {
        this.seeding = seeding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateSeeding(Integer seeding, boolean fromClient) {
        Integer oldSeeding = this.seeding;
        this.seeding = seeding;

        seedingHandlers.fireEvent(new UpdateModelEvent<Integer>(fromClient, oldSeeding, seeding));
    }

    public void updateName(String name, boolean fromClient) {
        String oldName = this.name;
        this.name = name;

        nameHandlers.fireEvent(new UpdateModelEvent<String>(fromClient, oldName, name));
    }

    public boolean isEmpty() {
        return name == null || name.equals("");
    }

    public boolean isWalkover() {
        return this instanceof WalkOverTeam;
    }

    public boolean hasSeeding() {
        return seeding != null;
    }

    public void setSeedingSilent(int i) {
        this.seeding = i;
    }

    public boolean isSeedingTeam() {
        return this instanceof SeedingTeam;
    }

    public boolean isARealTeam(){
        return true;
    }
}
