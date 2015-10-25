package com.example.hoang.revproject;

import android.net.Uri;

/**
 * Created by hoang on 10/24/2015.
 */
public class AlarmModel {

    public static final int SUNDAY = 0;
    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THUSDAY = 4;
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;

    public int id;
    public int timeHour;
    public int timeMinute;
    private boolean repeatingDay[];
    public boolean repeatAllWeek;
    public Uri alarmTone;
    public String Vocabulary;
    public boolean isEnabled;

    public AlarmModel(){
        repeatingDay = new boolean[7];
    }

    public void setRepeatingDay(int dayOfWeek, boolean value){
        repeatingDay[dayOfWeek] = value;
    }

    public boolean getRepeatingDay(int dayOfWeek) {
        return repeatingDay[dayOfWeek];
    }
}
