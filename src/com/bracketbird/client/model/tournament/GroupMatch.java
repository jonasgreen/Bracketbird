package com.bracketbird.client.model.tournament;

/**
 *
 */
public class GroupMatch extends Match {
    private static final long serialVersionUID = 6944560675227729797L;

    //When a group match changes state - the stageRound and the groupRound has to be notified.
    private StageRound stageRound;

    public GroupMatch(GroupRound groupRound, int matchNo) {
        super(groupRound, matchNo);
    }

    public void setStageRound(StageRound stageRound) {
        this.stageRound = stageRound;
    }

    @Override
    public void updateState(boolean fromClient) {
        super.updateState(fromClient);
        if (stageRound != null) {
            stageRound.updateState(fromClient);
        }
    }
}
