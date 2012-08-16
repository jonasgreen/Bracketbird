package com.bracketbird.clientcore.model;

import com.bracketbird.clientcore.util.StringUtil;

import java.io.Serializable;

/**
 * Imutable - must be because its part of a Route and the Route has a listener pattern.
 */
public class Address implements Serializable {
    private static final long serialVersionUID = -4419266078089932277L;
    private String nationalCode = "";
    private String nation = "";
    private String street = "";
    private String city = "";
    private String cityCode;

    public Address() {

    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityCityCodeAndNationCode(){
        return StringUtil.commaSep(city, cityCode, nationalCode);
    }
   

    @Override
    public String toString() {
        return "Address{" +
                "nationalCode='" + nationalCode + '\'' +
                ", nation='" + nation + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", cityCode='" + cityCode + '\'' +
                '}';
    }
}
