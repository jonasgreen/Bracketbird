package com.bracketbird.clientcore.gui;

import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.validation.*;

import java.util.*;


/**
 *
 */
public class ValidationComponent extends VerticalComponent {

    private ValidateManager vm;
    private LabelComponent header;

    public ValidationComponent(ValidateManager valManager) {
        super();
        this.vm = valManager;
        intit();
    }

    private void intit() {
        Layout17 lHeader = new TextLayout(Horizontal.LEFT, Vertical.MIDDLE).sizeNormal().bold().colorWhite().paddingLeft(4).add(P.BACKGROUND_DARK_RED);

        List<String> problems = getProblemList();


        getHeader().setText(problems.size() + (problems.size() == 1 ? " problem" : " problems"));// + " med udfyldning af felter");

        VerticalComponent content = new VerticalComponent();
        SimplePanelComponent sp = new SimplePanelComponent();
        sp.add(getHeader(), lHeader);

        add(sp, new Layout17(null, "100%").add(P.BACKGROUND_DARK_RED));

        Layout17 lProblem = new TextLayout(2,0,2,0).sizeSmall();
        for (String p : problems) {
            content.add(new LabelComponent("· "+ p), lProblem);
        }
       
        add(content, new Layout17(null, "100%").
                add(P.BORDER_STYLE_SOLID).
                add(Name.BORDER_COLOR, P.COLOR_DARK_RED.getValue()).
                add(P.BORDER_WIDTH_1px).
                add(Name.PADDING, "6px"));

    }

    private List<String> getProblemList() {
        List<DataContainer> guis = vm.getErrorDCs();
        List<String> errors = vm.getErrorMsgs();

        List<String> problemList = new ArrayList<String>();
        int i = 0;
        for (DataContainer c : guis) {
            String erMsg = c.getName() + " " + errors.get(i++);
            problemList.add(erMsg);
        }
        return problemList;
    }

    public LabelComponent getHeader() {
        if (header == null) {
            header = new LabelComponent("");
        }
        return header;
    }
}
