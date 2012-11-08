package com.bracketbird.client.model.tournament;

/**
 *
 */
public class NotReady extends TournamentState {
    private static final long serialVersionUID = -6889594103724691724L;

    private boolean hasTeams = false;
    private boolean hasLevels = false;

    public NotReady() {
        this(false, false);
    }

    public NotReady(boolean hasTeams, boolean hasLevels) {
        super("notready");
        this.hasTeams = hasTeams;
        this.hasLevels = hasLevels;
    }

    public boolean isHasTeams() {
        return hasTeams;
    }

    public void setHasTeams(boolean hasTeams) {
        this.hasTeams = hasTeams;
    }

    public boolean isHasLevels() {
        return hasLevels;
    }

    public void setHasLevels(boolean hasLevels) {
        this.hasLevels = hasLevels;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && doEquals(o);
    }

    @Override
    public boolean isStartet() {
        return false;
    }

    public boolean doEquals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        NotReady notReady = (NotReady) o;

        if (hasLevels != notReady.hasLevels) {
            return false;
        }
        if (hasTeams != notReady.hasTeams) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (hasTeams ? 1 : 0);
        result = 31 * result + (hasLevels ? 1 : 0);
        return super.hashCode() + result;
    }

    @Override
    public String toString() {
        return "NotReady{" +
                "name=" + name +

                "hasTeams=" + hasTeams +
                ", hasLevels=" + hasLevels +
                '}';
    }
}