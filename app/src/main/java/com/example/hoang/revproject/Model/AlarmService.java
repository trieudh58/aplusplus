package com.example.hoang.revproject.Model;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.hoang.revproject.Activity.AlarmScreenActivity;

import java.util.Calendar;

/**
 * Created by hoang on 10/26/2015.
 */
public class AlarmService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AlarmDBHelper dbHelper = new AlarmDBHelper(this.getApplicationContext());
        Intent alarmIntent = new Intent(getBaseContext(), AlarmScreenActivity.class);
        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        alarmIntent.putExtras(intent);
        getApplication().startActivity(alarmIntent);

        Bundle bundle = intent.getBundleExtra("DATA");
        AlarmModel model = dbHelper.getAlarm(bundle.getInt("id"));
        Calendar calendar = Calendar.getInstance();
        int position = -1;
        for (int i = 0; i < 7; i++){
            if ( i == (calendar.get(Calendar.DAY_OF_WEEK) - 1)){
                position = i;
                break;
            }
        }

        if (!model.isRepeatingDay()){
            model.isEnabled = false;
        }

        if (position == -1){
            model.isEnabled = false;
        }else {
            model.setRepeatingDay(position, false);
        }
        dbHelper.updateAlarm(model);
        AlarmManagerHelper.setAlarm(this);
        return super.onStartCommand(intent, flags, startId);
    }
}
