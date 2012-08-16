package com.bracketbird.clientcore.model;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 */
public class CountryData {


    private static java.util.SortedMap<String, String> mapCountriesCodes = new TreeMap<String, String>();
    private static java.util.SortedMap<String, String> mapCodesCountries = new TreeMap<String, String>();
    private static CountryData instance;


    public static CountryData getInstance() {
        if (instance == null) {
            instance = new CountryData();
            initMaps();
        }
        return instance;
    }

    public SortedMap<String, String> getMap(){
        return mapCountriesCodes;
    }

    public List<String> getCountries(){
        return new ArrayList<String>(mapCodesCountries.values());
    }

    public List<String> getCountryCodes(){
        return new ArrayList<String>(mapCountriesCodes.values());
    }

    public String getCountry(String countryCode){
        return mapCodesCountries.get(countryCode);
    }

    public String getCountryCode(String country){
        return mapCountriesCodes.get(country);
    }

    private static void initMaps() {
        putInMap("AL", "Albania");
        putInMap("DZ", "Algeria");
        putInMap("AS", "American Samoa");
        putInMap("AD", "Andorra");
        putInMap("AO", "Angola");
        putInMap("AI", "Anguilla");
        putInMap("AQ", "Antarctica");
        putInMap("AG", "Antigua and Barbuda");
        putInMap("AR", "Argentina");
        putInMap("AM", "Armenia");
        putInMap("AW", "Aruba");
        putInMap("AU", "Australia");
        putInMap("AT", "Austria");
        putInMap("AZ", "Azerbaijan");

        putInMap("BS", "Bahamas");
        putInMap("BH", "Bahrain");
        putInMap("BD", "Bangladesh");
        putInMap("BB", "Barbados");
        putInMap("BY", "Belarus");
        putInMap("BE", "Belgium");
        putInMap("BZ", "Belize");
        putInMap("BJ", "Benin");
        putInMap("BM", "Bermuda");
        putInMap("BT", "Bhutan");
        putInMap("BO", "Bolivia");
        putInMap("BA", "Bosnia and Herzegowina");
        putInMap("BW", "Botswana");
        putInMap("BV", "Bouvet Island");
        putInMap("BR", "Brazil");
        putInMap("IO", "British Indian Ocean Territory");
        putInMap("BN", "Brunei Darussalam");
        putInMap("BG", "Bulgaria");
        putInMap("BF", "Burkina Faso");
        putInMap("BI", "Burundi");

        putInMap("KH", "Cambodia");
        putInMap("CM", "Cameroon");
        putInMap("CA", "Canada");
        putInMap("CV", "Cape Verde");
        putInMap("KY", "Cayman Islands");
        putInMap("CF", "Central African Republic");
        putInMap("TD", "Chad");
        putInMap("CL", "Chile");
        putInMap("CN", "China");
        putInMap("CX", "Christmas Island");
        putInMap("CC", "Cocos (Keeling) Islands");
        putInMap("CO", "Colombia");
        putInMap("KM", "Comoros");
        putInMap("CG", "Congo");
        putInMap("CK", "Cook Islands");
        putInMap("CR", "Costa Rica");
        putInMap("CI", "Cote D'ivoire");
        putInMap("HR", "Croatia");
        putInMap("CU", "Cuba");
        putInMap("CY", "Cyprus");
        putInMap("CZ", "Czech Republic");

        putInMap("DK", "Denmark");
        putInMap("DJ", "Djibouti");
        putInMap("DM", "Dominica");
        putInMap("DO", "Dominican Republic");

        putInMap("TP", "East Timor");
        putInMap("EC", "Ecuador");
        putInMap("EG", "Egypt");
        putInMap("SV", "El Salvador");
        putInMap("GQ", "Equatorial Guinea");
        putInMap("R", "Eritrea");
        putInMap("EE", "Estonia");
        putInMap("ET", "Ethiopia");

        putInMap("FK", "Falkland Islands (Malvinas)");
        putInMap("FO", "Faroe Islands");
        putInMap("FJ", "Fiji");
        putInMap("FI", "Finland");
        putInMap("FR", "France");
        putInMap("GF", "French Guiana");
        putInMap("PF", "French Polynesia");
        putInMap("TF", "French Southern Territories");

        putInMap("GA", "Gabon");
        putInMap("GM", "Gambia");
        putInMap("GE", "Georgia");
        putInMap("DE", "Germany");
        putInMap("GH", "Ghana");
        putInMap("GI", "Gibraltar");
        putInMap("GR", "Greece");
        putInMap("GL", "Greenland");
        putInMap("GD", "Grenada");
        putInMap("GP", "Guadeloupe");
        putInMap("GU", "Guam");
        putInMap("GT", "Guatemala");
        putInMap("GN", "Guinea");
        putInMap("GW", "Guinea-bissau");
        putInMap("GY", "Guyana");

        putInMap("HT", "Haiti");
        putInMap("HM", "Heard and Mc Donald Islands");
        putInMap("HN", "Honduras");
        putInMap("HK", "Hong Kong");
        putInMap("HU", "Hungary");

        putInMap("IS", "Iceland");
        putInMap("IN", "India");
        putInMap("ID", "Indonesia");
        putInMap("IR", "Iran");
        putInMap("IQ", "Iraq");
        putInMap("IE", "Ireland");
        putInMap("IL", "Israel");
        putInMap("IT", "Italy");

        putInMap("JM", "Jamaica");
        putInMap("JP", "Japan");
        putInMap("JO", "Jordan");

        putInMap("KZ", "Kazakhstan");
        putInMap("KE", "Kenya");
        putInMap("KI", "Kiribati");
        putInMap("KW", "Kuwait");
        putInMap("KG", "Kyrgyzstan");

        putInMap("LA", "Lao");
        putInMap("LV", "Latvia");
        putInMap("LB", "Lebanon");
        putInMap("LS", "Lesotho");
        putInMap("LR", "Liberia");
        putInMap("LY", "Libya");
        putInMap("LI", "Liechtenstein");
        putInMap("LT", "Lithuania");
        putInMap("LU", "Luxembourg");

        putInMap("MO", "Macau");
        putInMap("MK", "Macedonia");
        putInMap("MG", "Madagascar");
        putInMap("MW", "Malawi");
        putInMap("MY", "Malaysia");
        putInMap("MV", "Maldives");
        putInMap("ML", "Mali");
        putInMap("MT", "Malta");
        putInMap("MH", "Marshall Islands");
        putInMap("MQ", "Martinique");
        putInMap("MR", "Mauritania");
        putInMap("MU", "Mauritius");
        putInMap("YT", "Mayotte");
        putInMap("MX", "Mexico");
        putInMap("FM", "Micronesia");
        putInMap("MD", "Moldova");
        putInMap("MC", "Monaco");
        putInMap("MN", "Mongolia");
        putInMap("MS", "Montserrat");
        putInMap("MA", "Morocco");
        putInMap("MZ", "Mozambique");
        putInMap("MM", "Myanmar");

        putInMap("NA", "Namibia");
        putInMap("NR", "Nauru");
        putInMap("NP", "Nepal");
        putInMap("NL", "Netherlands");
        putInMap("AN", "Netherlands Antilles");
        putInMap("NC", "New Caledonia");
        putInMap("NZ", "New Zealand");
        putInMap("NI", "Nicaragua");
        putInMap("NE", "Niger");
        putInMap("NG", "Nigeria");
        putInMap("NU", "Niue");
        putInMap("NF", "Norfolk Island");
        putInMap("MP", "Northern Mariana Islands");
        putInMap("NO", "Norway");

        putInMap("OM", "Oman");

        putInMap("PK", "Pakistan");
        putInMap("PW", "Palau");
        putInMap("PA", "Panama");
        putInMap("PG", "Papua New Guinea");
        putInMap("PY", "Paraguay");
        putInMap("PE", "Peru");
        putInMap("PH", "Philippines");
        putInMap("PN", "Pitcairn");
        putInMap("PL", "Poland");
        putInMap("PT", "Portugal");
        putInMap("PR", "Puerto Rico");

        putInMap("QA", "Qatar");

        putInMap("KR", "Republic Of Korea");
        putInMap("RE", "Reunion");
        putInMap("RO", "Romania");
        putInMap("RU", "Russian Federation");
        putInMap("RW", "Rwanda");

        putInMap("KN", "Saint Kitts and Nevis");
        putInMap("LC", "Saint Lucia");
        putInMap("VC", "Saint Vincent and The Grenadines");
        putInMap("WS", "Samoa");
        putInMap("SM", "San Marino");
        putInMap("ST", "Sao Tome and Principe");
        putInMap("SA", "Saudi Arabia");
        putInMap("SN", "Senegal");
        putInMap("RS", "Serbia");
        putInMap("SC", "Seychelles");
        putInMap("SL", "Sierra Leone");
        putInMap("SG", "Singapore");
        putInMap("SK", "Slovakia");
        putInMap("SI", "Slovenia");
        putInMap("SB", "Solomon Islands");
        putInMap("SO", "Somalia");
        putInMap("ZA", "South Africa");
        putInMap("ES", "Spain");
        putInMap("LK", "Sri Lanka");
        putInMap("SH", "St. Helena");
        putInMap("PM", "St. Pierre and Miquelon");
        putInMap("SD", "Sudan");
        putInMap("SR", "Suriname");
        putInMap("SJ", "Svalbard and Jan Mayen Islands");
        putInMap("SZ", "Swaziland");
        putInMap("SE", "Sweden");
        putInMap("CH", "Switzerland");
        putInMap("SY", "Syrian Arab Republic");

        putInMap("TW", "Taiwan");
        putInMap("TJ", "Tajikistan");
        putInMap("TZ", "Tanzania");
        putInMap("TH", "Thailand");
        putInMap("TG", "Togo");
        putInMap("TK", "Tokelau");
        putInMap("TO", "Tonga");
        putInMap("TT", "Trinidad and Tobago");
        putInMap("TN", "Tunisia");
        putInMap("TR", "Turkey");
        putInMap("TM", "Turkmenistan");
        putInMap("TC", "Turks and Caicos Islands");
        putInMap("TV", "Tuvalu");

        putInMap("UG", "Uganda");
        putInMap("UA", "Ukraine");
        putInMap("AE", "United Arab Emirates");
        putInMap("UK", "United Kingdom");
        putInMap("US", "United States");
        putInMap("UY", "Uruguay");
        putInMap("UZ", "Uzbekistan");

        putInMap("VU", "Vanuatu");
        putInMap("VA", "Vatican City State");
        putInMap("VE", "Venezuela");
        putInMap("VN", "Viet Nam");
        putInMap("VG", "Virgin Islands (British)");
        putInMap("VI", "Virgin Islands (U.S.)");

        putInMap("WF", "Wallis and Futuna Islands");
        putInMap("EH", "Western Sahara");

        putInMap("YE", "Yemen");
        putInMap("YU", "Yugoslavia");

        putInMap("ZR", "Zaire");
        putInMap("ZM", "Zambia");
        putInMap("ZW", "Zimbabwe");
    }

    private static void putInMap(String code, String country) {
        mapCodesCountries.put(code, country);
        mapCountriesCodes.put(country, code);
    }

}
