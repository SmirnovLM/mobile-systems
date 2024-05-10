package com.example.lab6.ConfGroup;

import android.content.Context;
import android.preference.PreferenceManager;


public class AppSettings {
    private static final String TEXT_KEY = "text_key";
    private static final String CHECKED_KEY = "checked_key";

    public static void saveText(Context context, String text) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(TEXT_KEY, text)
                .apply();
    }

    public static String getText(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(TEXT_KEY, "");
    }

    public static void saveChecked(Context context, boolean checked) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(CHECKED_KEY, checked)
                .apply();
    }

    public static boolean getChecked(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(CHECKED_KEY, false);
    }
}
