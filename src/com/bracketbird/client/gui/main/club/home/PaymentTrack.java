package com.bracketbird.client.gui.main.club.home;


import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.util.*;

/**
 *
 */
public class PaymentTrack extends VerticalComponent {


    private TextLayout l = new TextLayout("50px", "20px", Vertical.MIDDLE).backgroundBase().borderLeft(1).borderTop(1).borderBottom(1).borderColor(Color.backgroundBaseDark());

    private TextLayout lSep = new TextLayout("50px", "2px").backgroundWhite().borderLeft(1).borderTop(1).borderBottom(1).borderColor(P.BACKGROUND_WHITE);

    public PaymentTrack() {
        super();
        init();
    }

    private void init() {
        HorizontalComponent hc = new HorizontalComponent();
        int i = 0;
        SimplePanelComponent track = null;
        while (i < 8) {
            track = getTrack();
            hc.add(track, l);
            i++;
            if (i % 4 == 0) {
                hc.add(new SimplePanelComponent(), lSep);
                StyleIt.add(track, new TextLayout().borderRight(1).borderColor(Color.backgroundBaseDark()));
            }
        }
        StyleIt.add(track, new TextLayout().borderRight(1).borderColor(Color.backgroundBaseDark()).backgroundWhite());
        add(hc, new TextLayout("54px", null));
    }

    private VerticalComponent createYear(int year, int parts){
        VerticalComponent vc = new VerticalComponent();
        vc.add(new LabelComponent(""+ year), new TextLayout(Horizontal.CENTER, Vertical.MIDDLE));

        HorizontalComponent tracks = new HorizontalComponent();
        vc.add(tracks);
        return null;
    }

    private SimplePanelComponent getTrack() {

        final SimplePanelComponent track = new SimplePanelComponent();
        track.addMouseOverHandler(MouseOver.POINTER);
        track.addMouseOverHandler(new MouseOverHandler() {
            public void onMouseOver(MouseOverEvent event) {
                track.setWidth("20px");
                track.setHeight("52px");

            }
        });

        track.addMouseOutHandler(new MouseOutHandler() {
            public void onMouseOut(MouseOutEvent event) {
                track.setWidth("20px");
                track.setHeight("50px");
            }
        });


        return track;
    }

}
