package com.bracketbird.server.jdo;

import javax.jdo.annotations.*;

/**
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class AddressJDO {

    
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String countryCode;

    @Persistent
    private String city;

    @Persistent
    private String cityCode;

    @Persistent
    private String street;

    public AddressJDO() {
        
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
