package com.epitech.my_test_http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Classe qui va verifie si l'appareil est connecte à internet
 * quelque soit l'interface.
 *
 * Created by zero on 28/04/15.
 */
public class        ConnectionDetector {
    private Context _context;

    public          ConnectionDetector(Context context) {
        this._context = context;
    }

    public boolean  IsConnectedToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; ++i) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        return (true);
                }
            }
        }
        return (false);
    }
}
