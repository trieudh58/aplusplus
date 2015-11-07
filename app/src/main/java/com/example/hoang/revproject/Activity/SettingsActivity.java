package com.example.hoang.revproject.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hoang.revproject.Model.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SettingsFragment settingsFragment = new SettingsFragment();
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, settingsFragment)
                .commit();
    }
}
