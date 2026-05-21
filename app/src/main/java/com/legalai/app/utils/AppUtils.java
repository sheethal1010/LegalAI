package com.legalai.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppUtils {

    private static final String PREFS_NAME = "LegalAI_Prefs";
    private static final String KEY_DARK_MODE = "dark_mode";
    private static final String KEY_ONBOARDING_DONE = "onboarding_done";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_EMAIL = "user_email";

    public static SharedPreferences getPrefs(Context ctx) {
        return ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static boolean isDarkMode(Context ctx) {
        return getPrefs(ctx).getBoolean(KEY_DARK_MODE, false);
    }

    public static void setDarkMode(Context ctx, boolean enabled) {
        getPrefs(ctx).edit().putBoolean(KEY_DARK_MODE, enabled).apply();
    }

    public static boolean isOnboardingDone(Context ctx) {
        return getPrefs(ctx).getBoolean(KEY_ONBOARDING_DONE, false);
    }

    public static void setOnboardingDone(Context ctx) {
        getPrefs(ctx).edit().putBoolean(KEY_ONBOARDING_DONE, true).apply();
    }

    public static void cacheUserName(Context ctx, String name) {
        getPrefs(ctx).edit().putString(KEY_USER_NAME, name).apply();
    }

    public static void cacheUserEmail(Context ctx, String email) {
        getPrefs(ctx).edit().putString(KEY_USER_EMAIL, email).apply();
    }

    public static String getCachedUserName(Context ctx) {
        return getPrefs(ctx).getString(KEY_USER_NAME, "");
    }

    public static String getCachedUserEmail(Context ctx) {
        return getPrefs(ctx).getString(KEY_USER_EMAIL, "");
    }

    public static void clearCache(Context ctx) {
        getPrefs(ctx).edit()
                .remove(KEY_USER_NAME)
                .remove(KEY_USER_EMAIL)
                .apply();
    }

    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) return false;
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    public static String formatTimestamp(long timestamp) {
        long now = System.currentTimeMillis();
        long diff = now - timestamp;
        if (diff < 60000) return "Just now";
        if (diff < 3600000) return (diff / 60000) + " min ago";
        if (diff < 86400000) return (diff / 3600000) + " hr ago";
        if (diff < 604800000) return (diff / 86400000) + " days ago";
        return new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date(timestamp));
    }

    public static String getInitials(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) return "U";
        String[] parts = fullName.trim().split(" ");
        if (parts.length >= 2) {
            return String.valueOf(parts[0].charAt(0)).toUpperCase()
                    + String.valueOf(parts[1].charAt(0)).toUpperCase();
        }
        return String.valueOf(parts[0].charAt(0)).toUpperCase();
    }
}
