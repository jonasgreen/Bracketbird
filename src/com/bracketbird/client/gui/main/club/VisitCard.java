package com.bracketbird.client.gui.main.club;


import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class VisitCard extends VerticalComponent {

    private User user;

    public VisitCard(User user) {
        super();
        this.user = user;
        init();
    }

    private void init() {
        HorizontalComponent content = new HorizontalComponent();

        SimplePanelComponent pic = new SimplePanelComponent();
        content.add(pic, new TextLayout("180px", "130px").border(1).backgroundGrey());
        pic.add(new LabelComponent("Photo"), new TextLayout(80, 0, 0, 40));

        VerticalComponent info = new VerticalComponent();
        content.add(info, new TextLayout().padding(0,10,0,10));

        HorizontalComponent name = new HorizontalComponent();
        name.add(new LabelComponent(user.getFirstName() + " "+ user.getLastName()), new TextLayout().sizeH1().colorBaseDark());
        info.add(name);

        info.add(new LabelComponent(user.getEmail()), new TextLayout().paddingTop(4).sizeH3().colorBase());
        String phone = user.getPhone();
        TextLayout lphone = new TextLayout().paddingTop(4).sizeH3().colorBase();

        if(phone == null || phone.equals("")){
            info.add(new LabelComponent("No phone"), lphone);
        }
        else{
            info.add(new LabelComponent(phone), lphone);
        }
        TextLayout lAdr = new TextLayout(30,0,0,0).sizeSmall().colorBaseDark();
        info.add(new LabelComponent("Birkegade 17, 3.th"), lAdr);

        HorizontalComponent city = new HorizontalComponent();
        city.add(new LabelComponent("2200, "+ "Kbh.N."), new TextLayout().sizeSmall().colorBaseDark().paddingTop(4));
        info.add(city);

        add(content);

    }
}
