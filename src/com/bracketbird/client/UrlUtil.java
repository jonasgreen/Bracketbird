package com.bracketbird.client;

import com.google.gwt.dom.client.Document;

public class UrlUtil {


    private static String baseUrl;

    private static String DOMAIN_PROD = "www.bracketbird.com";
    private static String DOMAIN_LOCALHOST = "127.0.0.1:8888/Bracketbird.html?gwt.codesvr=127.0.0.1:9997";


    static {
        String domain = Document.get().getDomain();
        if (domain == null) {
            baseUrl = DOMAIN_PROD;
        }
        else if (domain.contains("bracketbird1.appspot")) {
            baseUrl = DOMAIN_PROD;
        }
        else if (domain.contains("bracketbird.com")) {
            baseUrl = DOMAIN_PROD;
        }
        else {
            baseUrl = DOMAIN_LOCALHOST;
        }
    }

    public static String getBaseUrl() {
        return baseUrl;
    }


}
