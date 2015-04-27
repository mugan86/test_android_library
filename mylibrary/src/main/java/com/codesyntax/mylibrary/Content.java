package com.codesyntax.mylibrary;

import android.content.Context;
import android.content.Intent;

/*****************************************
 * Created by anartzmugika on 15-04-27.
 */
public class Content {
    public static void showShare (Context context, String topic, String sharing_title, String message)
    {

        //Create Share Intent
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);

        //Share text content type (text)
        sharingIntent.setType("text/plain");

        //share topic
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT,context.getResources().getString(R.string.app_name));

        //Share message
        sharingIntent.putExtra(Intent.EXTRA_TEXT, message);

        //Start social networks window
        context.startActivity(Intent.createChooser(sharingIntent,
                sharing_title));
    }
}
