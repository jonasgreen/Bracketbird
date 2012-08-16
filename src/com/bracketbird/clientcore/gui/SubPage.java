package com.bracketbird.clientcore.gui;

import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.style.*;


/**
 *
 */
public abstract class SubPage<C extends PageController> extends Page<C> {
    private VerticalComponent content;
    public static TextLayout HEADER_LAYOUT = new TextLayout().sizeH2().bold().colorBaseDark().paddingBottom(10);
    public static TextLayout PAGE_LAYOUT = new TextLayout("100%", "100%").border(2).borderColor(Color.backgroundBase()).padding(20);


    public SubPage() {
        super();
        initWidget(getContent());
    }

    //called generic from getPage - lazy load
    public final void init() {
        createHeader();

        doInit();
        setBackgroundColor(P.BACKGROUND_WHITE);
        getContent().setStyleName("subpage");

    }

    private void createHeader() {
        if (getHeader() != null) {
            SimplePanelComponent sp = new SimplePanelComponent();
            sp.add(new LabelComponent(getHeader()), HEADER_LAYOUT);
            getContent().add(sp, new TextLayout(null, "100%"));
        }
    }

    public abstract void doInit();

    public abstract String getHeader();

    public VerticalComponent getContent() {
        if (content == null) {
            content = new VerticalComponent();
        }
        return content;
    }

    public TextLayout getLayout() {
        return PAGE_LAYOUT;
    }


}