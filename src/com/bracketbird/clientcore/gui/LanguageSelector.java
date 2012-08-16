package com.bracketbird.clientcore.gui;


import com.bracketbird.clientcore.language.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class LanguageSelector extends HorizontalComponent implements PushDownHandler {

    private static LanguageSelector instance;

    private PushDownLink[] links = new PushDownLink[]{
            new PushDownLink(Language.DANISH.getName()),
            //new PushDownLink("Deutsch"),
            new PushDownLink(Language.ENGLISH.getName())};
    //new PushDownLink("Españiol"),
    //new PushDownLink("Français")};


    private LanguageSelector() {
        super();
        init();
    }

    public static LanguageSelector getInstance() {
        if (instance == null) {
            instance = new LanguageSelector();
        }
        return instance;
    }

    private void init() {
        Layout17 lSep = new TextLayout(0, 2, 0, 2, Vertical.BOTTOM).sizeEkstraSmall().paddingBottom(15).add(P.COLOR_GREY);
        Layout17 l = new TextLayout(Vertical.BOTTOM).sizeEkstraSmall().paddingBottom(15);

        int index = 0;
        for (PushDownLink link : links) {
            link.addPushDownHandler(this);
            add(link, l);
            if (index != links.length - 1) {
                add(new LabelComponent("|"), lSep);
            }
            index++;
        }
        links[0].setSelected(true);
    }

    public void pushedDown(PushDownLink pdl) {
        for (PushDownLink link : links) {
            if (pdl == link) {
                link.setSelected(true);
                Language.changeLanguage(link.getLabel().getText());
            }
            else {
                link.setSelected(false);
            }
        }
    }


    public void setSelected(String langText) {
        for (PushDownLink link : links) {
            if(link.getLabel().getText().equals(langText)){
                link.setSelected(true);
            }
            else{
                link.setSelected(false);
            }
        }
    }
}
