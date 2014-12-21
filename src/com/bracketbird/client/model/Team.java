package com.bracketbird.client.model;


import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.client.rtc.event.UpdateDispatcher;

/**
 *
 */
public class Team extends Model<TeamId> {

    public UpdateDispatcher<String> nameDispatcher = new UpdateDispatcher<>();
    public UpdateDispatcher<Integer> seedingDispatcher = new UpdateDispatcher<>();

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

        seedingDispatcher.fireEvent(oldSeeding, seeding, fromClient);
    }

    public void updateName(String name, boolean fromClient) {
        String oldName = this.name;
        this.name = name;

        nameDispatcher.fireEvent(oldName, name, fromClient);
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
