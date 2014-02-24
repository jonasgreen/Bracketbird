package com.bracketbird.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

/**
 *
 */
public class Audio {

    private FlowPanel audioDiv;

    private static Audio instance;

    private Audio() {
    }

    public static Audio get(){
        if(instance == null){
            instance = new Audio();
        }
        return instance;
    }


    public void speak(String text) {
        getAudioDiv().clear();
        HTML html = new HTML("<iframe src=\"http://translate.google.com/translate_tts?tl=en&q="+text+"\"></iframe>");
        getAudioDiv().add(html);
    }

    public FlowPanel getAudioDiv() {
        if (audioDiv == null) {
            audioDiv = new FlowPanel();
            audioDiv.getElement().getStyle().setPosition(Style.Position.FIXED);
            audioDiv.getElement().getStyle().setLeft(-400, Style.Unit.PX);
            Document.get().getElementById("audio").insertFirst(audioDiv.getElement());
        }
        return audioDiv;
    }
}
