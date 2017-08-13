package com.rezkyatinnov.kyandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rezkyatinnov on 08/08/2017.
 */

public class SharedPrefUtils {
    public static final String PREFERENCES_NAME = "Rezkotfit";
    public static final String PREF_INNER_TOKEN = "access_token";
    public static final String PREF_REFRESH_TOKEN = "refresh_token";
    public static final String TAG = SharedPrefUtils.class.getName();
    private static SharedPrefUtils instance = new SharedPrefUtils();
    private static SharedPreferences sharedPreferences;

    public static SharedPrefUtils getInstance() {
        return instance;
    }

    public void init(Context context, String name, int mode) {
        this.sharedPreferences = context.getSharedPreferences(name, mode);
    }

    public void init(Context context){
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public String getInnerToken() {
        return sharedPreferences.getString(PREF_INNER_TOKEN, "");
    }

    public boolean setInnerToken(String value) {
        return sharedPreferences.edit().putString(PREF_INNER_TOKEN, value).commit();
    }

    public String getByName(String name) {
        return sharedPreferences.getString(name, "");

    }

    public boolean setByName(String name, String value) {
        return sharedPreferences.edit().putString(name, value).commit();
    }

    public String getRefreshToken() {
        return sharedPreferences.getString(PREF_REFRESH_TOKEN, "");
    }

    public boolean setRefreshToken(String value) {
        return sharedPreferences.edit().putString(PREF_REFRESH_TOKEN, value).commit();
    }

    public void clear() {
        setInnerToken("");
        setRefreshToken("");
    }
}
