package com.example.pedrolopes.internshapp; /**
 * Created by andrefrodrigues on 05-03-2016.
 */
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.Exception;import java.lang.Integer;import java.lang.String;import java.lang.StringBuilder;import java.lang.System;import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;


public class PostHandler {

    private String requestURL = "http://maps.googleapis.com/maps/api/geocode";
    private HttpURLConnection connection;
    private InputStreamReader is;
    private OutputStream os;
    private String urlParameters;

    public PostHandler(String address){
        try {
            urlParameters = "/json?address=" + URLEncoder.encode(address, "UTF-8")+"&components=country:PT";
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public JSONObject getResponse(){
        try {
            URL url = new URL(requestURL + urlParameters);
            System.out.println(url);
            //connection = (HttpURLConnection) (new URL(requestURL)).openConnection();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            // Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            // Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if not
            // Java 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            return new JSONObject(response.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
