package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.clientcore.appcontrol.Page;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.Horizontal;
import com.bracketbird.clientcore.style.TextLayout;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 *
 */
public class SeedingPage extends Page<SeedingPageController> {

    private VerticalComponent content;
    private LabelComponent randomButton;
    private SeedingPanel seedingPanel;
    private ScrollPanelComponent scrollPanel;
    private ButtonPanel buttonPanel;

    public SeedingPage() {
        super();
        content = new VerticalComponent();
        add(content);
    }

    public void init() {
        //HorizontalComponent hc = new HorizontalComponent();
        //hc.add(getSpecificAndRandom(), new TextLayout(10, 10, 10, 10, Horizontal.CENTER));
        //hc.add(getClear(),new TextLayout(10, 10, 10, 10, Horizontal.RIGHT));
        content.add(new LabelComponent("Change team seeding"), new TextLayout().sizeH1().colorBaseDark());
        content.add(getRandomButton(), new TextLayout(10,0,10,0,Horizontal.LEFT));
        content.add(getScrollPanel(), new TextLayout());
        content.add(new LabelComponent("Tip: use shift+arrows to move selected item"), new TextLayout(Horizontal.LEFT).colorBase().sizeEkstraSmall().italic().padding(10, 0, 0, 0));

        content.add(getButtonPanel(), new TextLayout(20, 0,0,0,null, "100%"));


        if(RTC.getInstance().getTournament().getTeams().size() > 14){
            getScrollPanel().setHeight("400px");
        }



        
    }

    protected void setSubPageHolder(Page subPage) {
        //ignore
    }

    public ButtonPanel getButtonPanel() {
        if (buttonPanel == null) {
            ButtonComponent ok = new ButtonComponent("Ok");
            ok.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                	PopupManager.hide();
                	RTC.getInstance().updateSeeding(getSeedingPanel().getSeedings());
                    
                }
            });

            ButtonComponent cancel = new ButtonComponent("Cancel");
            cancel.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    PopupManager.hide();
                }
            });
            buttonPanel = new ButtonPanel(Horizontal.RIGHT, ok, cancel);
            ok.setWidth("70px");
            cancel.setWidth("70px");

        }
        return buttonPanel;
    }

    public ScrollPanelComponent getScrollPanel() {
        if (scrollPanel == null) {
            scrollPanel = new ScrollPanelComponent(getSeedingPanel());
        }
        return scrollPanel;
    }

    public SeedingPanel getSeedingPanel() {
        if (seedingPanel == null) {
            seedingPanel = new SeedingPanel();
        }
        return seedingPanel;
    }

    public LabelComponent getRandomButton() {
        if (randomButton == null) {
            randomButton = new LabelComponent("Shuffle");
            randomButton.setStyleName("colorbutton4");

            randomButton.addMouseOver(MouseOver.POINTER);
            randomButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    getSeedingPanel().shuffle();
                }
            });
        }
        return randomButton;
    }






}