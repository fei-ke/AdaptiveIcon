package com.fei_ke.adaptiveicon;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.preference.PreferenceManager;

public class Util {
    public static void makeWorldReadable(final Context context, final PreferenceManager pm) {
        ApplicationInfo app_info = context.getApplicationInfo();
        final int target_sdk = app_info.targetSdkVersion;
        final int mode = pm.getSharedPreferencesMode();
        app_info.targetSdkVersion = Build.VERSION_CODES.M;
        pm.setSharedPreferencesMode(Context.MODE_WORLD_READABLE | mode);
        try {
            pm.getSharedPreferences();
        } catch (SecurityException e) {
            pm.setSharedPreferencesMode(mode);
        } finally {
            app_info.targetSdkVersion = target_sdk;
        }

    }
}
