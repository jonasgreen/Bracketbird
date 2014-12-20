package com.bracketbird.client;

import com.google.gwt.dom.client.Document;

public class Environment {

    private Boolean local;

    private static Environment instance;

    public static Environment get(){
        if(instance == null){
            instance = new Environment();
        }

        return instance;
    }


    public Boolean isLocal(){
        if(local == null){
            String domain = Document.get().getDomain();
            local = domain.contains("127.0.0.1") || domain.contains("localhost");
        }

        return local;
    }
}
