package com.bracketbird.clientcore.language;

import java.io.Serializable;

/**
 *
 */
public abstract class LanguagePage implements Serializable {
    private String[] texts;


    protected LanguagePage() {
    }

    public LanguagePage(String danish, String english, String german, String french){
        texts = new String[]{danish, english, german, french};
    }

    public String text(){
        return texts[Language.language.getValue()];
    }

    public String get(int i){
        return texts[i];
    }

    public String[] getTexts() {
        return texts;
    }
}
