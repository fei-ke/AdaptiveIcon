package com.fei_ke.adaptiveicon;

import android.content.res.XResources;
import android.text.TextUtils;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedMod implements IXposedHookLoadPackage, IXposedHookZygoteInit, IXposedHookInitPackageResources {
    public static final String CIRCLE_MASK = "M50 0A50 50,0,1,1,50 100A50 50,0,1,1,50 0";

    private XSharedPreferences pref;

    @Override
    public void initZygote(IXposedHookZygoteInit.StartupParam startupParam) throws Throwable {
        pref = new XSharedPreferences(BuildConfig.APPLICATION_ID);
        pref.makeWorldReadable();

        String currentIconMask = pref.getString("current_icon_mask", null);

        if (!TextUtils.isEmpty(currentIconMask)) {
            XResources.setSystemWideReplacement("android", "string", "config_icon_mask", currentIconMask);

            if (currentIconMask.equals(CIRCLE_MASK)) {
                XResources.setSystemWideReplacement("android", "bool", "config_useRoundIcon", true);
            }
        }
    }

    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

    }


}
