package com.codesyntax.mylibrary;


import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;


public class UserData {


	public static SharedPreferences getPreferencesFile(Context context) {
        // This sample app persists the registration ID in shared preferences, but
        // how you store the regID in your app is up to you.

        return PreferenceManager.getDefaultSharedPreferences(context);
    }
	public static String getPreference(Context context, String propertyName){
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		return sharedPreferences.getString(propertyName, "");
	}
	public static void notifikazioKudeaketa (Context context, String notifications_count)
	{

	    /**********************************************************************************
	    * Preferentzia fitxategian gorde guk sartutako erabiltzailea eta zerbitzariak
	    * bueltatu digun token-a, dena enkriptatua
	    *********************************************************************************/
	    String [] propertyNames = {ConstantValues.GCM_NOTIFICATION_COUNT};

	    String [] propertyValues = {notifications_count};
	        UserData.setPreference(context, propertyNames, propertyValues);

	}
	public static void setPreference(Context context, String [] propertyNames
													, String [] propertyValues){
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		//Existitzen den aldagai bat aldatuko luke??
		//ANARTZ: Pentsatzen dot baietz matxakatuko duela
		for (int i = 0; i< propertyNames.length; i ++)
		{
			editor.putString(propertyNames [i], propertyValues [i]);
		}

        editor.commit();
	}


	public static void clearNotification (Context context, int id)
	{
		NotificationManager myNotificationManager =
				(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		//emove the notification with the specific id
		myNotificationManager.cancel(id);
		UserData.notifikazioKudeaketa(context, "0");
	}



}
