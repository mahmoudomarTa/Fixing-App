package com.example.fixingapp.utils;

import android.app.Activity;
import android.content.Context;

import com.example.fixingapp.R;

import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;

public class Utils {
    public static String SHARED_PREFERENCES_NAME="mySharedPreferences";
    public static String SHARED_PREFERENCES_IS_FIRST_KEY="isFirst";
    public static String USER="user";
    public static String WORKER="worker";
    Activity activity;

    public Utils(Activity activity) {
        this.activity = activity;
    }

    public boolean isFirst(){
       return activity.getSharedPreferences(SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE).getBoolean(SHARED_PREFERENCES_IS_FIRST_KEY,true);
    }

    public void setIsFirst (boolean isFirst){
        activity.getSharedPreferences(SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE).edit().putBoolean(SHARED_PREFERENCES_IS_FIRST_KEY,isFirst).apply();
    }


    public String setUpMessageTime(Long time){

        String timeFormatted ="";
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int hour =calendar.get(Calendar.HOUR_OF_DAY);
        int minute =calendar.get(Calendar.MINUTE);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (hour>12){
            timeFormatted=(hour-12)+" : "+minute+activity.getString(R.string.pm);
        }else {
            timeFormatted=hour+" : "+minute+activity.getString(R.string.am);
        }
        return timeFormatted;
    }

    public String setUpJobTime(Long time){
        String timeFormatted ="";
        Calendar calendar = Calendar.getInstance();
        int cHour =calendar.get(Calendar.HOUR_OF_DAY);
        int cMinute =calendar.get(Calendar.MINUTE);
        int cDay = calendar.get(Calendar.DAY_OF_MONTH);


        calendar.setTimeInMillis(time);
        int hour =calendar.get(Calendar.HOUR_OF_DAY);
        int minute =calendar.get(Calendar.MINUTE);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        if (hour==cHour&&minute==cMinute&&day==cDay){
            timeFormatted=activity.getString(R.string.now);
        }else if (day==cDay){
            if (cHour - hour == 1) {
                timeFormatted=activity.getString(R.string.beforOneHour);
            } else if (cHour - hour == 2) {
                timeFormatted=activity.getString(R.string.beforTwoHour);
            } else if (cHour - hour == 3) {
                timeFormatted=activity.getString(R.string.befor3Hour);
            } else if (cHour - hour == 4) {
                timeFormatted=activity.getString(R.string.befor4Hour);
            } else if (cHour - hour == 5) {
                timeFormatted=activity.getString(R.string.befor5Hour);
            } else if (cHour - hour == 6) {
                timeFormatted=activity.getString(R.string.befor6Hour);
            }
        }else if (cDay-day==1){
            timeFormatted=activity.getString(R.string.yesterday)+" : "+hour+" : "+minute;
        }else if (cDay-day==2){
            timeFormatted=activity.getString(R.string.befor2days)+" : "+hour+" : "+minute;
        }else if (cDay-day==3){
            timeFormatted=activity.getString(R.string.befor3days)+" : "+hour+" : "+minute;
        }else {
            timeFormatted=day+" : "+hour+" : "+minute;

        }
        return timeFormatted;
    }

}
