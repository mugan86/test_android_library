package com.codesyntax.mylibrary;

import android.net.Uri;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/***************************************************************************************************
 * Created by anartzmugika on 15-04-24.
 *
 * Instructions for use this Library:
 *
 * Add "Internet" permission in Manifest File to GET / POST Requests
 *
 * In POST operation Add:
 *
 * request_url = server request url (For example: http://mugan86.com/serviraces/api/v1/user/get/login_v2.php")
 * token = Authorization credentials (if empty value not set token value)
 * builder= POST operation parameters
 *
 *
 * In GET operation Add:
 *
 * request_url
 *
 ***************************************************************************************************/

public class APIRequestsConnection {

    //Example to set auth token to get request from server with GET
    public static String getHttpPostAPI(String request_url, Uri.Builder builder) throws IOException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url = new URL(request_url);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        //Encode Query parameters....
        String query = builder.build().getEncodedQuery();

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        conn.connect();

        System.out.println("Response code: " + conn.getResponseCode());

        // read the output from the server
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null)
        {
            stringBuilder.append(line + "\n");
        }
        return stringBuilder.toString();

    }


    //Example to set auth token to get request from server
    public static String getHttpGETAPI (String request_url, String tribe_id, boolean facebook_login, String quiz_id, String token) throws IOException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url;
        BufferedReader reader = null;
        StringBuilder stringBuilder;

        if (!tribe_id.equals("") && !tribe_id.equals("check"))
        {
            request_url = request_url + "&tribe_id="+tribe_id;
        }

        if (!quiz_id.equals(""))
        {
            request_url = request_url + "&quiz_id="+quiz_id;
        }

        System.out.println("Request URL: " + request_url);

        try
        {
            // create the HttpURLConnection
            url = new URL(request_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // just want to do an HTTP GET here
            connection.setRequestMethod("GET");
            if (!facebook_login && !tribe_id.equals("check"))
            {
                connection.setRequestProperty("Authorization", "Token " + token);
            }

            // uncomment this if you want to write output to this url
            //connection.setDoOutput(true);

            // give it 15 seconds to respond
            connection.setReadTimeout(15*1000);
            connection.connect();

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        }
    }

}
