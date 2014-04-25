package com.bracketbird.client.gui.main;


import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class MainAppFrame extends VerticalComponent implements AppFrame {
    public static double LOGO_HEIGHT = 70;
    public static double MENU_HEIGHT = 45;
    public static double BOTTOM_HEIGHT = 10;
    public static double PAGE_WIDTH = 900;

    private HorizontalComponent logoFrame;
    private HorizontalComponent centerFrame;

    private static MainAppFrame instance;

    private GuiComponent centerPage;



    private MainAppFrame() {
        super();
        init();
    }

    public static MainAppFrame getInstance() {
        if (instance == null) {
            instance = new MainAppFrame();

        }
        return instance;
    }


    private void init() {
        add(getLogoFrame(), new Layout17(0, 0, 0, 0, LOGO_HEIGHT + "px", "100%"));

        add(getCenterFrame(), new TextLayout(0, 0, 10, 0, null, "100%"));
        //add(BottomPageController.getInstance().getPage(), new TextLayout(0, 0, 0, 0, "100%", "100%").backgroundBase());
    }


    public HorizontalComponent getLogoFrame() {
        if (logoFrame == null) {
            logoFrame = new HorizontalComponent();
            logoFrame.add(LogoPanel.getInstance().getPanel(), new TextLayout(LOGO_HEIGHT+"px", PAGE_WIDTH+"px", Horizontal.CENTER));

        }
        return logoFrame;
    }


    public HorizontalComponent getCenterFrame() {
        if (centerFrame == null) {
            centerFrame = new HorizontalComponent();
            centerFrame.setBackgroundColor(P.BACKGROUND_WHITE);
        }
        return centerFrame;
    }

    public void addCenterContent(GuiComponent gc) {
        if (centerPage != null) {
            centerPage.removeFromParent();
        }
        centerPage = gc;
        getCenterFrame().add(gc, new TextLayout("100%", "100%"));
    }



}
