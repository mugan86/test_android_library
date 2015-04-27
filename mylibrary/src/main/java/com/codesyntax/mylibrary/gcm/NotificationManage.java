package com.codesyntax.mylibrary.gcm;


import android.app.Activity;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;


public class NotificationManage {

    private Activity context;
    private GoogleCloudMessaging gcm;
    private String regid;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public NotificationManage(Activity context)
    {
        this.context = context;
    }

   /* public void pushNotifications()
    {
        //Aktibitatearen kontextua hartuko dugu

        //Google zerbitzuak instalaturik dauden aztertuko du
        if (checkPlayServices()){
            gcm = GoogleCloudMessaging.getInstance(context.getApplicationContext());
            regid = getRegistrationId(context.getApplicationContext());
            Log.i("", "RegId: " + regid);
            if (regid.equals("")) {
                System.out.println ("Hutsa regID");
                registerInBackground();
            }
        }
    }

    /*******************************************************************************
     *
     * Google Cloud Messaging zerbitzurako erabiliko diren metodoak
     *
     ********************************************************************************/
    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
   /* private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, context,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                System.out.println ("Konprobatu Google Zerbitzuak instalatuta dituzun");
                //context.finish();
            }
            return false;
        }
        return true;
    }

    private void storeRegistrationId(Context context, String regId)
    {

        final SharedPreferences prefs = UserData.getPreferencesFile(context);
        int appVersion = getAppVersion(context);

        Log.i("storeRegistrationId", "GORDETAKO ERREGISTRO ID BERTSIOA: " + appVersion);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ConstantValues.PROPERTY_REG_ID, regId);
        editor.putInt(ConstantValues.PROPERTY_APP_VERSION, appVersion);
        editor.commit();

        //Save register id in preferences file

        System.out.println("Register ID: "+regId);

    }

    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    int i = 0;
                    while (regid.equals(""))
                    {
                        i++;
                        System.out.println ("ERREGISTRO ID BILA: "+ i);
                        regid = gcm.register(ConstantValues.GCM_SENDER_ID);
                        System.out.println ("ERREGISTRO ID: "+regid);
                    }

                    msg = "ERREGISTRO ID: " + regid;

                    // You should send the registration ID to your server over HTTP, so it
                    // can use GCM/HTTP or CCS to send messages to your app.

                    // For this demo: we don't need to send it because the device will send
                    // upstream messages to a server that echo back the message using the
                    // 'from' address in the message.

                    // Persist the regID - no need to register again.
                    storeRegistrationId(context, regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }
        }.execute(null, null, null);
    }

    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Ezin izan da paketearen izena berreskuratu: " + e);
        }
    }

    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = UserData.getPreferencesFile(context);

        String registrationId = prefs.getString(ConstantValues.PROPERTY_REG_ID, "");
        System.out.println("ID GAILUA: "+ registrationId);
        if (registrationId == "") {
            Log.i("getRegistrationID", "Ez da aurkitzen erregistro ID");
            return "";
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
        int registeredVersion = prefs.getInt(ConstantValues.PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i("getRegistrationID", "Aldatua app bertsioa");
            return "";
        }
        return registrationId;
    }*/
}
