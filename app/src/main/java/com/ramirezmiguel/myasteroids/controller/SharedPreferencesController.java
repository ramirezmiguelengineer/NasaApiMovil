package com.ramirezmiguel.myasteroids.controller;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesController {
    private static final String SHARED_PREFS_NAME = "MyPreferences";

    private static SharedPreferences sharedPreferences;

    public static SharedPreferences getSharedPreferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }
}
