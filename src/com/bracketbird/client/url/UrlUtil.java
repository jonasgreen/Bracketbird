package com.bracketbird.client.url;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.History;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO - make generel
 */
public class UrlUtil {

    public static String TOKEN = "token";

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

    public static Map<String, String> getParameters() {
        String token = History.getToken();

        HashMap<String, String> pMap = new HashMap<String, String>();

        pMap.put(TOKEN, token);

        //if parameters exist
        if (token.contains("?")) {
            String parameters = token.split("\\?")[1];

            if (parameters.contains("&")) {
                String[] ss = parameters.split("&");
                for (String s : ss) {
                    String[] kv = s.split("=");
                    pMap.put(kv[0], kv[1]);
                }
            }
            //only one parameter
            else {
                String[] kv = parameters.split("=");
                pMap.put(kv[0], kv[1]);
            }

        }

        //TODO - catch error and show errorpage with option for goin to onlineclubmaster

        return pMap;
    }


}
