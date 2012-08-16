package com.bracketbird.client.gui.main;

import com.bracketbird.client.browser.Browser;
import com.bracketbird.clientcore.gui.FlowComponent;
import com.bracketbird.clientcore.gui.LabelComponent;
import com.bracketbird.clientcore.gui.VerticalComponent;
import com.bracketbird.clientcore.style.Horizontal;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;

/**
 *
 */
public class SloganPage extends VerticalComponent {
    private FlowComponent slogans;

    public SloganPage() {
        super();
        init();
    }

    private void init() {
        setStyleName("sloganPage");
        add(getSlogans(), new TextLayout("100%", null));
    }


    public FlowComponent getSlogans() {
        if (slogans == null) {
            slogans = new FlowComponent();
            VerticalComponent vc = new VerticalComponent();

            if (Browser.isIEBrowser()) {
                FrontSlogan w = new FrontSlogan("Internet Explorer not supported", "This site has not been tested in Internet Explorer - Please run this site in Mozilla Firefox or Google Chrome. Sorry for the inconvenience.");
                StyleIt.add(w.getHeader(), new TextLayout().colorRed());
                StyleIt.add(w.getText(), new TextLayout().colorRed());

                vc.add(w);
            }

            vc.add(new FrontSlogan("Tired of running tournaments on paper, in Excel or likewise?", "I was, so i made Bracketbird - it's easy, fast and sharable"));
            vc.add(new FrontSlogan("No sign in, no installation, no fees", "Each tournament is uniquely identified by its url. Bookmark it and reopen it later. Or share it with others. It's 100% free."));
            vc.add(new FrontSlogan("Share tournaments", "Update or follow the same tournament from unlimited number of devices. You can enter results on one laptop while following the tournament on a projector from another laptop."));
            vc.add(new FrontSlogan("Anything on your mind", "Feel free to contact me at jonasgreen12345@gmail.com."));

            slogans.add(vc, new TextLayout("100%", "900px", Horizontal.CENTER).paddingTop(40));
        }
        return slogans;
    }

    /*
      private VerticalComponent getIENotSupportedMessage() {

        VerticalComponent vc = new VerticalComponent();

        LabelComponent header = new LabelComponent("Internet Explorer is not supported at the moment");
        LabelComponent t1a = new LabelComponent("Please run this site in Mozilla Firefox or Google Chrome.");
        LabelComponent t2 = new LabelComponent("Sorry for the inconvenience.");
        vc.add(header, new TextLayout().sizeH1().colorBlack().padding(20).paddingBottom(30));

        TextLayout tl = new TextLayout().sizeH3().colorBlack().padding(5).paddingLeft(20).paddingRight(20);
        vc.add(t1a, tl);
        tl.paddingBottom(20);
        vc.add(t2, tl);

        vc.getElement().getStyle().setBorderColor("black");
        vc.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        vc.getElement().getStyle().setBorderWidth(1, Style.Unit.PX);
        vc.getElement().getStyle().setBorderWidth(1, Style.Unit.PX);
        vc.getElement().getStyle().setBackgroundColor("Orange");
        return vc;
    }
     */

}
