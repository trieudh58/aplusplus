package com.example.hoang.revproject.Model;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

/**
 * Created by hoang on 10/26/2015.
 */
public class AlarmManagerHelper extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

    }

    public static void setAlarm(Context mContext){
        cancelAlarm(mContext);
        AlarmDBHelper dbHelper = new AlarmDBHelper(mContext);
        List<AlarmModel> list = dbHelper.getListAlarms();
        boolean alarmSet = false;

        for (AlarmModel alarmModel : list) {
            if (alarmModel.isEnabled) {
                PendingIntent pendingIntent = createPendingIntent(mContext, alarmModel);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, alarmModel.timeHour);
                calendar.set(Calendar.MINUTE, alarmModel.timeMinute);
                calendar.set(Calendar.SECOND, 00);

                final int nowDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                final int nowHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                final int nowMinute = Calendar.getInstance().get(Calendar.MINUTE);

                if (alarmModel.isRepeatingDay() == false) {
                    if (alarmModel.timeHour > nowHour || (alarmModel.timeHour == nowHour && alarmModel.timeMinute > nowMinute)) {
                        setAlarm(mContext, calendar, pendingIntent);
//                        Toast.makeText(mContext, calendar.get(Calendar.DATE) + "-" + calendar.get(Calendar.HOUR_OF_DAY) + "-" + calendar.get(Calendar.MINUTE) + " alarm.hour > nowhour", Toast.LENGTH_LONG).show();
                    } else {
                        calendar.add(Calendar.DATE, 1);
                        setAlarm(mContext, calendar, pendingIntent);
//                        Toast.makeText(mContext, calendar.get(Calendar.DATE) + "-" + calendar.get(Calendar.HOUR_OF_DAY) + "-" + calendar.get(Calendar.MINUTE) + "", Toast.LENGTH_LONG).show();
                    }
                } else {
                    for (int dayOfWeek = Calendar.SUNDAY; dayOfWeek <= Calendar.SATURDAY; dayOfWeek++) {
                        if (alarmModel.getRepeatingDay(dayOfWeek - 1) == true && dayOfWeek >= nowDay
                                && !(dayOfWeek == nowDay && alarmModel.timeHour < nowHour)
                                && !(dayOfWeek == nowDay && alarmModel.timeHour == nowHour && alarmModel.timeMinute <= nowMinute)) {
                            calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
                            setAlarm(mContext, calendar, pendingIntent);
//                            Toast.makeText(mContext, calendar.get(Calendar.DAY_OF_WEEK) + " - " + calendar.get(Calendar.MONTH) + " - " + calendar.get(Calendar.YEAR), Toast.LENGTH_LONG).show();
                            alarmSet = true;
                            break;
                        }
                    }

                    if (!alarmSet) {
                        for (int dayOfWeek = Calendar.SUNDAY; dayOfWeek <= Calendar.SATURDAY; ++dayOfWeek) {
                            if (alarmModel.getRepeatingDay(dayOfWeek - 1) && dayOfWeek <= nowDay) {
                                calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
                                calendar.add(Calendar.WEEK_OF_YEAR, 1);
                                setAlarm(mContext, calendar, pendingIntent);
//                                Toast.makeText(mContext, "  I'm Not Here", Toast.LENGTH_LONG).show();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private static void setAlarm(Context context, Calendar calendar, PendingIntent pIntent) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
        }
    }

    public static void cancelAlarm(Context mContext){
        AlarmDBHelper dbHelper = new AlarmDBHelper(mContext);
        List<AlarmModel> list = dbHelper.getListAlarms();
        if (list != null){
            for (AlarmModel alarmModel : list){
                PendingIntent pendingIntent = createPendingIntent(mContext, alarmModel);
                AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);
            }
        }
    }

    public static void setNotification(Context mContext){
        long repeatTime = 1000*60*30;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String str = sharedPreferences.getString("repeating_time", "1800000");
        int strWord = Integer.parseInt(sharedPreferences.getString("repeating_word", "2"));
        switch (str){
            case "3600000": repeatTime = 1000*60*60;
                break;
            case "7200000": repeatTime = 1000*60*120;
                break;
            case "10800000": repeatTime = 1000*60*180;
                break;
            case "14400000": repeatTime = 1000*60*240;
                break;
            case "18000000": repeatTime = 1000*60*300;
                break;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 00);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MINUTE, 1);
        final int nowHour = calendar1.get(Calendar.HOUR_OF_DAY);
        Intent intent = new Intent(mContext, SettingsService.class);
        Bundle bundle = new Bundle();
        bundle.putInt("NumberOfWord", strWord);
        intent.putExtra("DATA", bundle);
        PendingIntent pendingIntent = PendingIntent.getService(mContext, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        if (calendar.get(Calendar.HOUR_OF_DAY) < nowHour && nowHour < 22 ){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(),repeatTime, pendingIntent);
            } else {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), 1000*60*5, pendingIntent);
            }
//            Toast.makeText(mContext, "Dang o day", Toast.LENGTH_SHORT).show();
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(),1000*60*5, pendingIntent);
            } else {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), 1000 * 60 * 5, pendingIntent);
//                Toast.makeText(mContext, "Dang o kia", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void cancleNotification(Context mContext){
        Intent intent = new Intent(mContext, SettingsService.class);
        PendingIntent pendingIntent = PendingIntent.getService(mContext, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    private static PendingIntent createPendingIntent(Context context, AlarmModel model){
        Intent intent = new Intent(context, AlarmService.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", model.id);
        bundle.putString("vocabulary", model.Vocabulary);
        bundle.putInt("timeHour", model.timeHour);
        bundle.putInt("timeMinute", model.timeMinute);
        bundle.putInt("volume", model.volume);
        bundle.putString("image", model.image);
        bundle.putString("tone", String.valueOf(model.alarmTone));
        intent.putExtra("DATA", bundle);
        return PendingIntent.getService(context, model.id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
