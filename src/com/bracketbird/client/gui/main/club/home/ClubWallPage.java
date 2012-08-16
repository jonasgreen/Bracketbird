package com.bracketbird.client.gui.main.club.home;


import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.util.*;

/**
 *
 */
public class ClubWallPage extends SubPage<ClubWallPageController> {

    //private WallEventComponent board;
    private HorizontalComponent submitArea;
    private TextBoxComponent textBox;
    private ButtonComponent submitButton;


    public ClubWallPage() {
        super();
    }


    public void doInit() {
        getContent().add(getSubmitArea(), new TextLayout(0,0,20,0));
      //  getContent().add(getBoard(), new TextLayout(0,0,0,0, null, "100%"));
        getContent().add(new SimplePanelComponent(), new TextLayout("100%", null));
    }

    public String getHeader() {
        return "Wall of Today";
    }


    public HorizontalComponent getSubmitArea() {
        if (submitArea == null) {
            submitArea = new HorizontalComponent();
            submitArea.add(getSubmitTextBox(), new TextLayout(0,0,0,0,"20px", "100%", Vertical.MIDDLE).sizeSmall().colorBaseDark().border(1).borderColor(Color.backgroundBaseDark()));
            submitArea.add(getSubmitButton(), new TextLayout(0,0,0,20,Vertical.MIDDLE).sizeSmall().colorBaseDark());

        }
        return submitArea;
    }

    public TextBoxComponent getSubmitTextBox() {
        if (textBox == null) {
            textBox = new TextBoxComponent();
            StyleIt.add(textBox, Name.BORDER, "NONE");
            textBox.getTextBox().addKeyDownHandler(new KeyDownHandler() {
                public void onKeyDown(KeyDownEvent event) {
                    if(KeyUtil.isEnter(event.getNativeKeyCode())){
                        getController().submit();
                    }
                }
            });

        }
        return textBox;
    }

    public ButtonComponent getSubmitButton() {
        if (submitButton == null) {
            submitButton = new ButtonComponent("Submit");
            submitButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    getController().submit();
                }
            });
        }
        return submitButton;
    }


    @Override
    protected void setSubPageHolder(Page subPage) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void clear(){

    }



    protected void setSubPage(Page subPage) {
        //ignore
    }
}