package com.bracketbird.client.model.tournament;


/**
 *
 */
public class GroupName {

    private String[] names = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "Z"};

    int index = 0;
    int round = 0;
    

    public String next(){
        if(index > names.length-1){
            index = 0;
            round = 1;
        }
        String retValue = names[index++];
        return round == 0 ? retValue : retValue + round;
    }
    
}

