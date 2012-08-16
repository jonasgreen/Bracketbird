package com.bracketbird.clientcore.gui;

import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.validation.*;

/**
 *
 */
public class AddressPanel extends FlexTableComponent {

    private ListContainer<String> country = new ListContainer<String>("Country",
    new KeyValueList<String>(CountryData.getInstance().getMap())
, 55, false, true);
    private StringContainer street = new StringContainer("Street, housenumber", true);
    private StringContainer city = new StringContainer("City", true);
    private StringContainer cityCode = new StringContainer("Postal/zip code", true);


    public AddressPanel() {
        super();
        init();
    }

    private void init() {
        Layout17 ll = new TextLayout().sizeSmall().colorBase();

        TextLayout tbl = new TextLayout(2, 0, 2, 10, "26px", "300px", null, Vertical.MIDDLE).sizeH2().colorBaseDark().verticalAlignMiddel();

        setLabel(0, 0, country, ll);
        setWidget(0, 1, country, new TextLayout(0,0,0,10).sizeSmall());

        setLabel(1, 0, street, ll);
        setWidget(1, 1, street, tbl);

        setLabel(2, 0, cityCode, ll);
        setWidget(2, 1, cityCode, tbl);

        setLabel(3, 0, city, ll);
        setWidget(3, 1, city, tbl);
    }


    public String getNation() {
        return country.getKey();
    }

    public String getNationCode() {
        return country.getValue();
    }

    public ListContainer getCountry() {
        return country;
    }

    public StringContainer getStreet() {
        return street;
    }

    public StringContainer getCity() {
        return city;
    }

    public StringContainer getCityCode() {
        return cityCode;
    }

    public Address getValue(){
        Address a = new Address();
        a.setNation(country.getKey());
        a.setNationalCode(country.getValue());
        a.setCity(city.getValue());
        a.setCityCode(cityCode.getValue());
        a.setStreet(street.getValue());

        return a;
    }


    public boolean validate() {
        ValidateManager val = new ValidateManager();
        boolean hasErrors = val.validate(getDataContainerChildren());
        if (!hasErrors) {
            val.showErrors();
        }
        return hasErrors;
    }

}
