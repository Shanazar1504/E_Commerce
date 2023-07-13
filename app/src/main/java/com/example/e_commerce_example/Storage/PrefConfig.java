package com.example.e_commerce_example.Storage;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefConfig {
    private static final String PREF_TIME_KEY = "pref_time_key";
    private static final String PREF_NOTIFICATION_KEY = "pref_notification_key";
    private static final String PREF_IMG_KEY = "pref_img_key";
    private static final String MY_PREFERENCE_NAME = "pref_e_commerse";
    private static final String PREF_USER_KEY = "pref_user_key";
    private static final String PREF_EMAIL_KEY = "pref_email_key";
    private static final String PREF_IP_KEY = "pref_ip_key";
    private static final String PREF_PORT_KEY = "pref_port_key";
    private static final String PREF_TOKEN_KEY = "pref_token_key";
    private static final String PREF_CATDET_KEY = "pref_catdet_key";


    public static void saveIpPref(Context context, String ip) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_IP_KEY, ip);
        editor.apply();
    }
    public static String loadIpPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_IP_KEY, "http://192.168.0.107");
    }

    // for PORT
    public static void savePORTPref(Context context, String port) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_PORT_KEY, port);
        editor.apply();
    }
    public static String loadPORTPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_PORT_KEY, ":8091/");
    }



    // for GetData from Raspberry Pi4
    public static void saveTime(Context context, int time) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_TIME_KEY, time);
        editor.apply();
    }
    public static int loadTime(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_TIME_KEY, 3000);
    }

    // for DeviceConfig (notification activator)
    public static void saveStateNote(Context context, int note) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_NOTIFICATION_KEY, note);
        editor.apply();
    }
    public static int loadStateNote(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_NOTIFICATION_KEY, 0);
    }

    // for Watt value
    public static void saveImg(Context context, int img) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_IMG_KEY, img);
        editor.apply();
    }
    public static int loadImg(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_IMG_KEY, 0);
    }

    // for socket setting time
    public static void saveUser(Context context, String user) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_USER_KEY, user);
        editor.apply();
    }

    public static String loadUser(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_USER_KEY, "");
    }

    // for socket setting time
    public static void saveEmail(Context context, String email) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_EMAIL_KEY, email);
        editor.apply();
    }

    public static String loadEmail(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_EMAIL_KEY, "");
    }

    public static void saveToken(Context context, String token) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_TOKEN_KEY, token);
        editor.apply();
    }

    public static String loadToken(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_TOKEN_KEY, "");
    }

    public static int saveId_catDet(Context context, int catDet) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_CATDET_KEY, String.valueOf(catDet));
        editor.apply();
        return catDet;
    }

    public static String loadCatDet(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_CATDET_KEY, "");
    }
}