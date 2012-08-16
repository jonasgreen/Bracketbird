package com.bracketbird.clientcore.gui;

/**
 *
 */
public class HelpText {

    private final String[] helpers = new String[]{
            "Dette er en hjælpetekst",
            "Du ser nu en anden hjælpetekst",
            "lysegrønne balloner er fra, mørkegrønne er til",
            "ta da!!!"};

    int index = 0;

    private static HelpText instance = null;

    private HelpText(){

    }

    public static HelpText getInstance() {
        if (instance == null) {
            instance = new HelpText();
        }
        return instance;
    }


    public String getNextHelp(){
        if(index < helpers.length){
            return helpers[index++];
        }
        else{
            index = 0;
            return helpers[index++];
        }
    }
}
