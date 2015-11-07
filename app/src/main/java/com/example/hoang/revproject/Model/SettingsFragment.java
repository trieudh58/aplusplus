package com.example.hoang.revproject.Model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.hoang.revproject.R;

/**
 * Created by hoang on 10/29/2015.
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{

    private static final String SERVICE_ENABLE_KEY = "checkBox";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(SERVICE_ENABLE_KEY.equals(key)){
            boolean enabled = sharedPreferences.getBoolean(key, true);
            if(enabled){
                startHeadService();
            }else {
                stopHeadService();
            }
        }
    }



    private void startHeadService(){
        AlarmManagerHelper.setNotification(getActivity().getApplicationContext());
    }

    private void stopHeadService(){
        AlarmManagerHelper.cancleNotification(getActivity().getApplicationContext());
    }
}
