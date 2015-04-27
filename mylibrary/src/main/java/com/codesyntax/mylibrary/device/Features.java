package com.codesyntax.mylibrary.device;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/******************************************************************
 * Created by anartzmugika on 15-04-27.
 *****************************************************************/
public class Features {

    private static DisplayMetrics getDisplayMetrics(Context context)
    {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    /*****************************************************************************************
     * 			Pantailaren zabalera lortzeko
     *****************************************************************************************/
    public static int getScreenWidth (Context context)
    {

        DisplayMetrics dm = getDisplayMetrics(context);

        return dm.widthPixels;
    }
    /*****************************************************************************************
     * 			Pantailaren ezaugarriak dentsitatea lortzeko
     *****************************************************************************************/
    public static String getWindowsDensity(Context context)
    {

        DisplayMetrics dm = getDisplayMetrics(context);

        int densityDpi = dm.densityDpi;

        /************************************************************************************
         PANTAILAREN DENTSITATEAREN ARABERA (densityDpi), irudi bat edo beste hartuko du
         ldpi: desde 100 hasta 150
         mdpi: desde 150 hasta 200
         hdpi: desde 200 hasta 250
         xhdpi: desde 250 hasta 300
         ************************************************************************************/
        //Toast.makeText(context, "Density: "+densityDpi, Toast.LENGTH_LONG).show();

        String irudiMota = "";
        if (densityDpi >= 100 && densityDpi < 150)
        {
            irudiMota = "ldpi";
        }
        else if (densityDpi >= 150 && densityDpi < 200)
        {
            irudiMota = "mdpi";
        }
        else if (densityDpi >= 200 && densityDpi < 250)
        {
            irudiMota = "hdpi";
        }
        else if (densityDpi >= 250 )
        {
            irudiMota = "xhdpi";
        }
        //Toast.makeText(context, "Irudi mota: "+ irudiMota, Toast.LENGTH_LONG).show();
        return irudiMota;
    }
}
