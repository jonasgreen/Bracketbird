package com.bracketbird.client.model.tournament;


public class PrintStateTree {

    public void print(Tournament t){
        StringBuilder sb = new StringBuilder();

        sb.append("Tournament: " + name(t.getState()));
        for (Stage stage : t.getStages()) {
            sb.append("    STAGE ").append(stage.getName().toUpperCase()).append(": ").append(name(stage.getState()));
            for (Round round : stage.getRounds()) {
                sb.append("        ").append("round: ").append(name(round.getState()));
                for (Match match : round.getMatches()) {
                    sb.append("            ").append(match).append("\n");
                }
            }
            if(!stage.isKnockoutStage()){
                GroupStage gstage = (GroupStage) stage;
                for (Group group : gstage.getGroups()) {
                    sb.append("        ").append("GROUP ").append(group.getName()).append(": ").append(name(group.getState()));
                    for (Round round : group.getRounds()) {
                        sb.append("            ").append("round: ").append(name(round.getState()));
                        for (Match match : round.getMatches()) {
                            sb.append("                ").append(match).append("\n");
                        }
                    }
                }
            }
        }

        System.out.println(sb.toString());

    }


    private String name(LevelState state){
        return state.getClass().getSimpleName() + "\n";
    }
}
