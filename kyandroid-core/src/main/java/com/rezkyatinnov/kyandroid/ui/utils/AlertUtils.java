package com.rezkyatinnov.kyandroid.ui.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by rezkyatinnov on 09/08/2017.
 */

public class AlertUtils {
    public static void showDialogDualActions(Context context, String title, String message, String positive, String negative, DialogInterface.OnClickListener positiveCallback, DialogInterface.OnClickListener negativeCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive, positiveCallback)
                .setNegativeButton(negative, negativeCallback);
        builder.create().show();
    }

    public static void showDialogDualActions(Context context, String title, String message, DialogInterface.OnClickListener positiveCallback, DialogInterface.OnClickListener negativeCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", positiveCallback)
                .setNegativeButton("Cancel", negativeCallback);
        builder.create().show();
    }

    public static void showDialogDualActions(Context context, String message, DialogInterface.OnClickListener positiveCallback, DialogInterface.OnClickListener negativeCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton("OK", positiveCallback)
                .setNegativeButton("Cancel", negativeCallback);
        builder.create().show();
    }

    public static void showDialogSingleAction(Context context, String title, String message, String positive, DialogInterface.OnClickListener positiveCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive, positiveCallback);
        builder.create().show();
    }

    public static void showDialogSingleAction(Context context, String title, String message, DialogInterface.OnClickListener positiveCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", positiveCallback);
        builder.create().show();
    }

    public static void showDialogSingleAction(Context context, String message, DialogInterface.OnClickListener positiveCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton("OK", positiveCallback);
        builder.create().show();
    }
}
