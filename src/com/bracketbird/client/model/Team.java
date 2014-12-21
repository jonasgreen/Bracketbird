package com.bracketbird.client.model;


import com.bracketbird.client.model.keys.*;
import com.bracketbird.client.rtc.event.ModelHandlerList;
import com.bracketbird.client.rtc.event.UpdateModelEvent;

/**
 *
 */
public class Team extends Model<TeamId> {

    public ModelHandlerList<String> nameHandlers = new ModelHandlerList<String>();
    public ModelHandlerList<Integer> seedingHandlers = new ModelHandlerList<Integer>();

    protected String name;
    protected Integer seeding;


    public Team(String name, int seeding) {
        super();
        this.name = name;
        this.seeding = seeding;
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

    @Override
    public String toString() {
        return getName();
    }
}
