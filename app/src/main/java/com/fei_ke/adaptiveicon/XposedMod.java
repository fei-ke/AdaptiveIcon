package com.fei_ke.adaptiveicon;

import android.content.res.XResources;
import android.text.TextUtils;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedMod implements IXposedHookLoadPackage, IXposedHookZygoteInit, IXposedHookInitPackageResources {
    private XSharedPreferences pref;

    @Override
    public void initZygote(IXposedHookZygoteInit.StartupParam startupParam) throws Throwable {
        pref = new XSharedPreferences(BuildConfig.APPLICATION_ID);
        pref.makeWorldReadable();

        String currentIconMask = pref.getString("current_icon_mask", null);

        if (!TextUtils.isEmpty(currentIconMask)) {
            XResources.setSystemWideReplacement("android", "string", "config_icon_mask", currentIconMask);
        }
    }

    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

    }


}
