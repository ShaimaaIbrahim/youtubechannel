package com.google.youtubechannel.Model.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;

import com.google.youtubechannel.R;

public class AlertDialogs {

    public static AlertDialog createListAlertDialog(Activity activity, String title, String[] lisItem, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);

        builder.setItems(lisItem, listener);

        return builder.create();
    }

    public static AlertDialog createAlarmAlertDialog(Activity activity, String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(content);
        builder.setPositiveButton(R.string.alert_dialog_button_ok, null);

        return builder.create();
    }

    public static AlertDialog createProgressBarAlertDialog(Context context) {
        LayoutInflater linearLayout=LayoutInflater.from(context);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setView(linearLayout.inflate(R.layout.dialog_progress_bar,null));

        return builder.create();
    }

}
