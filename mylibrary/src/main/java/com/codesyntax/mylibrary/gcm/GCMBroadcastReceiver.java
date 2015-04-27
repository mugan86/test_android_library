package com.codesyntax.mylibrary.gcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;


import com.codesyntax.mylibrary.MainActivity;
import com.codesyntax.mylibrary.R;

/***********************************************************
 *
 * @author 		CodeSyntax
 * @description Class to receive push notifications fron Google Play Services.
 *
 ***********************************************************/

public class GCMBroadcastReceiver extends BroadcastReceiver {


	private int numMessagesOne = 0;

	@Override
	public void onReceive(Context context, Intent intent) {

        //Get Default language from preferences file

        //String default_language = Language.loadLocaleLanguage(context);

        System.out.println("Load locale language: "+ "es");

        String action = intent.getAction();
	    Log.w("C2DM", "Message Receiver called");
	    if ("com.google.android.c2dm.intent.RECEIVE".equals(action))
        {
	        Log.w("C2DM", "Received message");
	        final String payload = intent.getStringExtra("payload");
            final String type = intent.getStringExtra("type");
            String title, text, ticker;
            System.out.println("Type: "+ type);

            if (type.equals("1"))
            {
                //tStringExtra("data");
                Log.i("C2DM", "dmControl: payload = " + payload);
                String notifications_count = "1";

                System.out.println ("COUNT: "+ notifications_count);

                if (notifications_count.equals("") || notifications_count.equals("0"))
                {
                    notifications_count = "1";
                    //UserData.notifikazioKudeaketa(context, notifications_count);
                }
                else
                {
                    numMessagesOne = Integer.parseInt(notifications_count)+1;
                    notifications_count = String.valueOf(numMessagesOne);
                    //UserData.notifikazioKudeaketa(context, notifications_count);
                }
                System.out.println("COUNT: "+ notifications_count);

                text = ""; title = ""; ticker = "";
            }
            else //Dinamic Events from Tribual Server
            {
                //UserData.notifikazioKudeaketa(context, "0");
                title = intent.getStringExtra("title");
                ticker = intent.getStringExtra("ticker");
                text = intent.getStringExtra("text");
            }

            displayNotificationOne(context, payload, type, title, text, ticker);

	    }
	}


	protected void displayNotificationOne(Context context, String payload, String type, String title , String text , String ticker) {

		//Check if device have active vibrate mode
	    AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
	    if (am.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE) {

	    	//Vibrate Active
			Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

			// Vibrate 1 second
			v.vibrate(1000);
	    }
	    else
	    {
	    	System.out.println("Vibrate disabled");
	    }

	    // Call to default notification system
	    NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(context);
        int notifyId;



        if (type.equals("1"))
        {
            notifyId = 2;
            if (numMessagesOne<2)
            {
                //One Game Results
                title = "Partida bat";
                text = "Partida bat text";
                ticker = "Partida emaitza jaso dira";
            }
            else
            {
                //Many Games results
                title = "Partida batzuk";
                text = "Partida asko text";
                ticker = "Partida emaitza asko jaso dira";
            }
        }
        else
        {
            /*title = "Informazioa";
            text = "Test informazioa dinamikoa";
            ticker = "Aspaldi ez duzula jokatzen";*/
            notifyId = 12;
        }

        Bundle bundle = new Bundle();
        bundle.putString("payload", payload);
        bundle.putString("from", "game_notification");
        bundle.putInt("notificationId", notifyId);



	    mBuilder.setContentTitle(title);
	    mBuilder.setContentText(text);
	    mBuilder.setTicker(ticker);
	    mBuilder.setSmallIcon(R.drawable.ic_plusone_medium_off_client);
	    mBuilder.setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND);

	    //Count unread notifications
	    mBuilder.setNumber(numMessagesOne);



        Intent resultIntent = new Intent(context, MainActivity.class);

        //resultIntent.addFlags(Intent.Fl);
	    resultIntent.putExtras(bundle);

	    System.out.println("ANDROID SDK: "+ android.os.Build.VERSION.SDK_INT);

	    //Go to next Intent
	    TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

	    // Add Intent to start activity when user click notification item
	    stackBuilder.addNextIntent(resultIntent);
	    PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
	            0,
	            PendingIntent.FLAG_UPDATE_CURRENT //update current layout
	         );

	    // start activity when user click notification item
	    mBuilder.setContentIntent(resultPendingIntent);
        //mBuilder.setAutoCancel(true);

        NotificationManager myNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

	    //pass notification object to system
	    myNotificationManager.notify(notifyId, mBuilder.build());
	}


}
