package com.tt.czj.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;

/**
 * The type Shared prefs utils.
 */
public class SharedPrefsUtils {
    /**
     * The constant SETTING.
     */
    public static final String SETTING = "Setting";

    /**
     * Put value.
     *
     * @param context the context
     * @param key     the key
     * @param value   the value
     */
    public static void putValue(Context context, String key, int value) {
        Editor sp = context.getSharedPreferences(SETTING, 0).edit();
        sp.putInt(key, value);
        sp.commit();
    }

    /**
     * Put value.
     *
     * @param context the context
     * @param key     the key
     * @param value   the value
     */
    public static void putValue(Context context, String key, boolean value) {
        Editor sp = context.getSharedPreferences(SETTING, 0).edit();
        sp.putBoolean(key, value);
        sp.commit();
    }

    /**
     * Put value.
     *
     * @param context the context
     * @param key     the key
     * @param value   the value
     */
    public static void putValue(Context context, String key, String value) {
        Editor sp = context.getSharedPreferences(SETTING, 0).edit();
        sp.putString(key, value);
        sp.commit();
    }

    /**
     * Gets value.
     *
     * @param context  the context
     * @param key      the key
     * @param defValue the def value
     * @return the value
     */
    public static int getValue(Context context, String key, int defValue) {
        return context.getSharedPreferences(SETTING, 0).getInt(key, defValue);
    }

    /**
     * Gets value.
     *
     * @param context  the context
     * @param key      the key
     * @param defValue the def value
     * @return the value
     */
    public static boolean getValue(Context context, String key, boolean defValue) {
        return context.getSharedPreferences(SETTING, 0).getBoolean(key, defValue);
    }

    /**
     * Gets value.
     *
     * @param context  the context
     * @param key      the key
     * @param defValue the def value
     * @return the value
     */
    public static String getValue(Context context, String key, String defValue) {
        return context.getSharedPreferences(SETTING, 0).getString(key, defValue);
    }
}
