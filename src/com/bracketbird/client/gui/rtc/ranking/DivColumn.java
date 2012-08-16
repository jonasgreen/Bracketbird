package com.bracketbird.client.gui.rtc.ranking;

import com.bracketbird.clientcore.gui.FlowComponent;
import com.bracketbird.clientcore.gui.GuiComponent;
import com.bracketbird.clientcore.gui.LabelComponent;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Element;

import java.util.List;

/**
 *
 */
public class DivColumn extends FlowComponent{

    private List<? extends FlowComponent> divs;
    private LabelComponent title;

    public DivColumn(List<? extends FlowComponent> divs, String header) {
        super();
        getHeader().setText(header);
        this.divs = divs;
        init();
        getStyleElement().getStyle().setFloat(Style.Float.LEFT);
    }

    private void init() {
        add(getHeader(), new TextLayout("30px", null).sizeH3().colorBaseDark().alignCenter().paddingTop(20));
        for (FlowComponent div : divs) {
            add(div);
        }
    }

    public LabelComponent getHeader() {
        if (title == null) {
            title = new LabelComponent("");
        }
        return title;
    }

    public void relayout(int height){
        int height1 = height == 0 ? getElement().getOffsetHeight() : height;
        this.setHeight(height1+"px");
        int divSpace = calculateDivSpace(height1);
        int count = 0;
        for (FlowComponent div : divs) {
            if(count++ == 0){
                div.getElement().getStyle().setMarginTop(divSpace, Style.Unit.PX);
            }
            else{
            div.getElement().getStyle().setMarginTop(2 * divSpace, Style.Unit.PX);
            }
        }

    }

    private int calculateDivSpace(int height) {
        int totalDivHeight = height/ divs.size();
        int divHeight = 0;
        for (FlowComponent div : divs) {
            divHeight = div.getElement().getOffsetHeight();
        }
        return (totalDivHeight-divHeight) / 2;
    }




}
