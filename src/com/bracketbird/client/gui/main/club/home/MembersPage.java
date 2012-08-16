package com.bracketbird.client.gui.main.club.home;


import com.bracketbird.client.*;
import com.bracketbird.client.gui.main.club.*;
import com.bracketbird.client.model.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class MembersPage extends Page<MembersPageController> {

    private SimplePanelComponent membersTable;
    private SimplePanelComponent suggestBoxHolder;
    private SimplePanelComponent memberInfo;
    private VerticalComponent leftSide;
    private LabelComponent memberCount;
    private SuggestBoxComponent suggestBox;
    private VerticalComponent content;
    private LabelComponent createMember;

    public MembersPage() {
        super();
        content = new VerticalComponent();
        initWidget(content);
    }



    public void init() {

        content.add(new LabelComponent("Search"), new TextLayout().sizeEkstraSmall().paddingBottom(2).paddingTop(10).colorBaseDark());

        HorizontalComponent hc = new HorizontalComponent();
        hc.add(getSuggestBoxHolder(), new TextLayout(Horizontal.LEFT, Vertical.MIDDLE));
        hc.add(getCreateMember(), new TextLayout(0, 0, 0, 30, Horizontal.RIGHT, Vertical.MIDDLE).colorBaseDark());


        content.add(hc, new TextLayout(null, "100%").padding(0, 30, 0, 0));

        content.add(getMemberCount(), new TextLayout(Horizontal.LEFT).sizeEkstraSmall().padding(10, 0, 2, 0).colorBaseDark());

        HorizontalComponent mainHc = new HorizontalComponent();

        mainHc.add(getLeftSide(), new TextLayout().paddingRight(10));

        mainHc.add(getMemberInfo());

        content.add(mainHc, new TextLayout().padding(0, 0, 10, 0));

        content.add(new SimplePanelComponent(), new TextLayout("100%", null));
        setBackgroundColor(P.BACKGROUND_WHITE);
    }

    public String getHeader() {
        return "Members of " + UserManager.getInstance().getClub().getName();
    }

    public LabelComponent getCreateMember() {
        if (createMember == null) {
            createMember = new LabelComponent("Create new member");
            createMember.setStyleName("colorbutton");
            createMember.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    //PopUpDataPageController.showCreateMember();
                }
            });
        }
        return createMember;
    }

    public LabelComponent getMemberCount() {
        if (memberCount == null) {
            memberCount = new LabelComponent("All members");
        }
        return memberCount;
    }

    public VerticalComponent getLeftSide() {
        if (leftSide == null) {
            leftSide = new VerticalComponent();
            ScrollPanelComponent w = new ScrollPanelComponent(getMembersTable());
            leftSide.add(w, new TextLayout("400px", "150px").border(1).borderColor(Color.backgroundBase()));
        }
        return leftSide;
    }

    public void updateMemberInfo(User user, Member m) {
        VerticalComponent vc = new VerticalComponent();
        vc.add(new VisitCard(user), new TextLayout());

        TextLayout tl = new TextLayout().sizeNormal().colorBaseDark();
        TextLayout td = new TextLayout().sizeNormal().colorBaseDark().paddingLeft(10);

        FlexTableComponent fl = new FlexTableComponent();
        fl.setWidget(0, 0, new LabelComponent("Last tournament played:"), tl);
        fl.setWidget(0, 1, new LabelComponent("21-12-2009"), td);

        vc.add(fl, new TextLayout(Horizontal.LEFT).padding(10, 0, 0, 0));


        vc.add(new LabelComponent("Membership payment:"), new TextLayout(20, 0, 0, 0).sizeNormal().colorBaseDark());

        vc.add(new PaymentTrack(), new TextLayout(10, 0, 0, 0));
        getMemberInfo().add(vc);
        setFocus();
    }


    public void updateMembersTable(Set<String> memberNames) {
        VerticalComponent vc = new VerticalComponent();
        TextLayout tl = new TextLayout(null, "100%").sizeEkstraSmall().padding(2).noWrap();

        MultiWordSuggestOracle or = new MultiWordSuggestOracle();


        for (final String name : memberNames) {
            final HyperlinkLabelComponent hl = new HyperlinkLabelComponent(name);
            or.add(name);
            hl.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    getController().updateMembersInfo(name);
                }
            });
            hl.addMouseOver(new MouseOverHandler() {
                public void onMouseOver(MouseOverEvent event) {
                    hl.setBackgroundColor(Color.backgroundComplLight());
                }
            });
            hl.addMouseOut(new MouseOutHandler() {
                public void onMouseOut(MouseOutEvent event) {
                    hl.setBackgroundColor(P.BACKGROUND_WHITE);
                }
            });

            vc.add(hl, tl);
        }
        getMembersTable().add(vc, new TextLayout(null, "100%"));
        getMemberCount().setText(memberNames.size() + " members");
        suggestBox = new SuggestBoxComponent(or);

        suggestBox.getSuggestBox().addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
            public void onSelection(SelectionEvent<SuggestOracle.Suggestion> event) {
                String mString = event.getSelectedItem().getReplacementString();
                getController().updateMembersInfo(mString);
                suggestBox.getSuggestBox().setText("");
            }
        });


        suggestBox.getSuggestBox().setLimit(15);
        getSuggestBoxHolder().add(suggestBox, new TextLayout("46px", "100%", null, Vertical.MIDDLE).sizeH1().colorBaseDark().padding(2).add(P.VERTICAL_ALIGN_MIDDLE));
        setFocus();
    }

    public void setFocus() {
        if (suggestBox != null) {
            suggestBox.getSuggestBox().setFocus(true);
        }
    }

    public SimplePanelComponent getMembersTable() {
        if (membersTable == null) {
            membersTable = new SimplePanelComponent();
        }
        return membersTable;
    }

    public SimplePanelComponent getMemberInfo() {
        if (memberInfo == null) {
            memberInfo = new SimplePanelComponent();
        }
        return memberInfo;
    }

    public SimplePanelComponent getSuggestBoxHolder() {
        if (suggestBoxHolder == null) {
            suggestBoxHolder = new SimplePanelComponent();
            suggestBox = new SuggestBoxComponent(new MultiWordSuggestOracle(" "));
        }
        return suggestBoxHolder;
    }


    protected void setSubPageHolder(Page subPage) {
        //ignore
    }
}