package com.codesyntax.mylibrary.status;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/********************************************************
 * Created by anartzmugika on 15-04-27.
 *
 *
 * Add in Manifest File:
 *
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
 *******************************************************/
public class CheckInternetConnection {

    public static boolean isAvailable(Context context)
    {
        //Check Internet connection status

        ConnectivityManager conMgr = startConnectivityManager (context);

        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected())
        {
            return true;

        }
        else
        {
            //No connection
            return false;
        }
    }

    public static String getNetworkConnectionInfo(Context ctx)
    {
        final ConnectivityManager connectManager = startConnectivityManager (ctx);
        final NetworkInfo mobile = connectManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        final NetworkInfo wifi   = connectManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI  );

        if (mobile != null && mobile.getState() == NetworkInfo.State.CONNECTED)
        {
            //3G connection
            return "3g";
        }
        else if (wifi   != null && wifi.getState()   == NetworkInfo.State.CONNECTED)
        {
            //WIFI Connection
            return "wifi";
        }

        // Return true if connected, either in 3G or wi-fi
        return "";

    }

    private static ConnectivityManager startConnectivityManager (Context ctx)
    {
        return (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE); // ctx stands for the Context
    }
}
