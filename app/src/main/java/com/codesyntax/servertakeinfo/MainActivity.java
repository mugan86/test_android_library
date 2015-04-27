package com.codesyntax.servertakeinfo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


import com.codesyntax.mylibrary.APIRequestsConnection;
import com.codesyntax.mylibrary.CheckInternetConnection;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("***********************************");
        try {

            //Get Operation
            System.out.println("GET Server Request--->   " + APIRequestsConnection.getHttpGETAPI("http://mugan86.com/serviraces/api/v1/info/get/summary.php", "", false, "", ""));


            //POST operation
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("username", "servirace")
                    .appendQueryParameter("password", "");

            System.out.println("POST Server Request--->   " + APIRequestsConnection.getHttpPostAPI("http://mugan86.com/serviraces/api/v1/info/get/summary.php", builder));

            System.out.println("Internet Available? "+ CheckInternetConnection.isAvailable(getApplicationContext()));

            System.out.println("Connection Type:  "+ CheckInternetConnection.getNetworkConnectionInfo(getApplicationContext()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
