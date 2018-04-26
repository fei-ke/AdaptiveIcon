package com.fei_ke.adaptiveicon;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class MainActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Util.makeWorldReadable(this, getPreferenceManager());

        PreferenceManager.setDefaultValues(this, R.xml.prefrences, true);
        addPreferencesFromResource(R.xml.prefrences);

        final SharedPreferences preferences = getPreferenceManager().getSharedPreferences();

        ListPreference listPreference = (ListPreference) findPreference("current_icon_mask");
        String currentMask = preferences.getString("current_icon_mask", "");
        MASK[] masks = MASK.values();
        String entries[] = new String[masks.length];
        String values[] = new String[masks.length];
        int valueIndex = 0;
        for (int i = 0; i < masks.length; i++) {
            entries[i] = masks[i].name;
            values[i] = masks[i].path;
            if (masks[i].path.equals(currentMask)) {
                valueIndex = i;
            }
        }
        listPreference.setEntries(entries);
        listPreference.setEntryValues(values);
        listPreference.setValueIndex(valueIndex);
    }

    enum MASK {
        DEFAULT("DEFAULT", ""),
        CIRCLE("CIRCLE", XposedMod.CIRCLE_MASK),
        SQUIRCLE("SQUIRCLE", "M50,0 C10,0 0,10 0,50 0,90 10,100 50,100 90,100 100,90 100,50 100,10 90,0 50,0 Z"),
        ROUNDED_SQUARE("ROUNDED_SQUARE", "M50,0L70,0 A30,30,0,0 1 100,30 L100,70 A30,30,0,0 1 70,100 L30,100 A30,30,0,0 1 0,70 L 0,30 A30,30,0,0 1 30,0z"),
        SQUARE("SQUARE", "M50,0L100,0 100,100 0,100 0,0z"),
        TEARDROP("TEARDROP", "M50,0A50,50,0,0 1 100,50 L100,85 A15,15,0,0 1 85,100 L50,100 A50,50,0,0 1 50,0z"),
        SAMSUNG_SQUIRCLE("SAMSUNG_SQUIRCLE", "M59,0 C83.8689829,0 100,16.1310171 100,41 L100,59 C100,83.8689829 83.8689829,100 59,100 L41,100 C16.1310171,100 0,83.8689829 0,59 L0,41 C0,16.1310171 16.1310171,0 41,0 L59,0 Z"),
        NEXUS_SQUARE("NEXUS_SQUARE", "M50,0L92,0C96.42,0 100,4.58 100 8L100,92C100, 96.42 96.42 100 92 100L8 100C4.58, 100 0 96.42 0 92L0 8 C 0 4.42 4.42 0 8 0L50 0Z");

        String name;
        String path;

        MASK(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }
}


