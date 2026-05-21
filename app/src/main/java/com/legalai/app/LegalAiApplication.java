package com.legalai.app;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.legalai.app.utils.AppUtils;

public class LegalAiApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Apply dark mode preference on app start
        if (AppUtils.isDarkMode(this)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
