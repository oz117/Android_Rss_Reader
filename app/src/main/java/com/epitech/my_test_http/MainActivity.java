package com.epitech.my_test_http;

import android.app.Activity;
import android.os.Bundle;

import static com.epitech.my_test_http.parser.ChangeRssFeed.changeFeed;


public class        MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Boolean isConnected = false;
        ConnectionDetector connection = new ConnectionDetector(this.getApplicationContext());

        isConnected = connection.IsConnectedToInternet();
        if (!isConnected) {
            new ShowAlertDialog(MainActivity.this, "No Internet Connection",
                    "You don't have internet connection.", true);
        }
        else {
            changeFeed(false, this);
        }

    }
}
