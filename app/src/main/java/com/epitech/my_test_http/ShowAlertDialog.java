package com.epitech.my_test_http;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Une simple classe qui affiche une boite de dialogue d'alerte
 *
 * Created by zero on 28/04/15.
 */
public class    ShowAlertDialog {
    public      ShowAlertDialog(final Context context, String alertTitle, String alertMsg, final Boolean quit) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(alertTitle);
        alertDialog.setMessage(alertMsg);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (quit) {
                    alertDialog.dismiss();
                    System.exit(0);
                }
            }
        });
        alertDialog.setIcon(R.mipmap.sad_android);
        alertDialog.show();
    }
}
