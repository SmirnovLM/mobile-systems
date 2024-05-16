package com.example.lab6.ConfGroup;

import android.content.Context;
import android.preference.PreferenceManager;


public class AppSettings {
    private static final String CHECKED_KEY = "checked_key";
    private static final String LOGIN_KEY = "login_key";
    private static final String PASSWORD_KEY = "password_key";


    public static void saveLogin(Context context, String login) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(LOGIN_KEY, login)
                .apply();
    }

    public static String getLogin(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(LOGIN_KEY, "");
    }

    public static void savePassword(Context context, String password) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PASSWORD_KEY, password)
                .apply();
    }

    public static String getPassword(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PASSWORD_KEY, "");
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
