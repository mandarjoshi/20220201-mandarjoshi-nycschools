package com.mandarjoshi.nycschools.util;

import android.app.AlertDialog;
import android.content.Context;

public class DialogUtil {

    public static AlertDialog getSimpleErrorDialog(Context context){
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error")
                .setMessage("Couldn't complete request. try again later.")
                .setNeutralButton("Ok", (dialog, id) -> dialog.dismiss());
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public static AlertDialog getNodataDialog(Context context){
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error")
                .setMessage("No data found.")
                .setNeutralButton("Ok", (dialog, id) -> dialog.dismiss());
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
