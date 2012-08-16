package com.bracketbird.client.gui.rtc;

import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 *
 */
public class LockPanel extends HorizontalComponent{

    private ImageComponent activeImage;
    private ImageComponent lockImage;
    private ImageComponent unlockImage;
    private ClickHandler handler;
    private LabelComponent label;

    public LockPanel(ClickHandler handler) {
        this.handler = handler;
        //setActiveImage(getUnlockImage());
        add(getLabel(), new TextLayout(Vertical.MIDDLE).sizeSmall().colorBaseDark().paddingTop(10).underline());
    }

    private void setActiveImage(ImageComponent ic){
        if(activeImage != null){
            activeImage.removeFromParent();
        }
        activeImage = ic;
        add(activeImage, new TextLayout("20px", "20px",Vertical.MIDDLE).margin(4, 10, 0, 5));

    }

    public ImageComponent getLockImage() {
        if (lockImage == null) {
            lockImage = new ImageComponent("lock32.png");
            lockImage.getImage().addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    lockClicked();
                }
            });
            lockImage.getImage().addMouseOverHandler(MouseOver.POINTER);
            lockImage.setTitle("This tournament is secured with password.");


        }
        return lockImage;
    }

    public ImageComponent getUnlockImage() {
        if (unlockImage == null) {
            unlockImage = new ImageComponent("un_lock32.png");
            unlockImage.getImage().addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    lockClicked();
                }
            });
            unlockImage.getImage().addMouseOverHandler(MouseOver.POINTER);
            unlockImage.setTitle("Secure this tournament with a password.");
        }
        return unlockImage;
    }



    private void lockClicked(){
        OkWarning gc = new OkWarning("Not implemented yet");
        gc.getHeader().setText("Secure tournament with a password");
        PopupManager.show(gc, null);
        gc.getOkButton().getButton().setFocus(true);
    }

    public LabelComponent getLabel() {
        if (label == null) {
            label = new LabelComponent("Share");
            label.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    shareClicked();
                }
            });
            label.addMouseOver(MouseOver.POINTER);
            label.setTitle("Share this tournament.");
        }
        return label;
    }

    private void shareClicked() {
        ShareComponent gc = new ShareComponent(RTC.getInstance().getTournament().getViewUrl(), RTC.getInstance().getTournament().getUrl());
        PopupManager.show(gc, null);
        gc.getCloseButton().getButton().setFocus(true);
    }





}
