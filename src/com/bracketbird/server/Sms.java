package com.bracketbird.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 */
public class Sms {
    private static String MSG = "MSG";

    private static String url_template = "http://api.clickatell.com/http/sendmsg?user=Megajoe&password=Asserbo73&api_id=3219386&to=4521497961&text=MSG";


    public static void writeSms(String msg){
        BufferedReader in = null;

       
        try{
            String urlString = url_template.replaceAll(MSG, msg);

            URL yahoo = new URL(urlString);
                    URLConnection yc = yahoo.openConnection();
                    in = new BufferedReader(
                                            new InputStreamReader(
                                            yc.getInputStream()));
                    String inputLine;

                    while ((inputLine = in.readLine()) != null)
                    in.close();
        }
        catch(Throwable t){
            Logger.log("SMS-EXCEPTION: "+t.getClass().getName() +" "+ t.getMessage());
        }
        finally {
            try{
                //noinspection ConstantConditions
                in.close();
            }
            catch(Throwable t){
                //ignore
            }
        }


    }





}





/*

http://api.clickatell.com/http/sendmsg?user=Megajoe&password=Asserbo73&api_id=3219386&to=4521497961&text=Meet+me+outside

*/