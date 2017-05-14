package com.tt.czj.utils;

import android.util.Log;

/**
 * The type Log utils.
 */
public class LogUtils {
    /**
     * The constant TAG.
     */
    public static String TAG = "message";
    /**
     * The constant isTest.
     */
    public static boolean isTest = true;

    /**
     * D.
     *
     * @param key   the key
     * @param value the value
     */
    public static void d(String key, String value) {
        if (isTest) {
            Log.d(key, value);
        }
    }

    /**
     * .
     *
     * @param key   the key
     * @param value the value
     */
    public static void i(String key, String value) {
        if (isTest) {
            Log.i(key, value);
        }
    }

    /**
     * E.
     *
     * @param key   the key
     * @param value the value
     */
    public static void e(String key, String value) {
        if (isTest) {
            Log.e(key, value);
        }
    }

    /**
     * Log.
     *
     * @param tag  the tag
     * @param info the info
     */
    public static void log(String tag, String info) {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        if (isTest) {
            StackTraceElement s = ste[1];
            Log.e(tag, String.format("======[%s][%s][%s]=====%s", new Object[]{s.getClassName(), Integer.valueOf(s.getLineNumber()), s.getMethodName(), info}));
        }
    }

    /**
     * W.
     *
     * @param key   the key
     * @param value the value
     */
    public static void w(String key, String value) {
        if (isTest) {
            Log.w(key, value);
        }
    }

    /**
     * W.
     *
     * @param key the key
     * @param tr  the tr
     */
    public static void w(String key, Throwable tr) {
        if (isTest) {
            Log.w(key, tr);
        }
    }

    /**
     * W.
     *
     * @param key   the key
     * @param value the value
     * @param tr    the tr
     */
    public static void w(String key, String value, Throwable tr) {
        if (isTest) {
            Log.w(key, value, tr);
        }
    }

    /**
     * D.
     *
     * @param value the value
     */
    public static void d(String value) {
        if (isTest) {
            Log.d(TAG, value);
        }
    }

    /**
     * .
     *
     * @param value the value
     */
    public static void i(String value) {
        if (isTest) {
            Log.i(TAG, value);
        }
    }

    /**
     * E.
     *
     * @param value the value
     */
    public static void e(String value) {
        if (isTest) {
            Log.e(TAG, value);
        }
    }

    /**
     * Log.
     *
     * @param info the info
     */
    public static void log(String info) {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        if (isTest) {
            StackTraceElement s = ste[1];
            Log.e(TAG, String.format("======[%s][%s][%s]=====%s", new Object[]{s.getClassName(), Integer.valueOf(s.getLineNumber()), s.getMethodName(), info}));
        }
    }

    /**
     * W.
     *
     * @param value the value
     */
    public static void w(String value) {
        if (isTest) {
            Log.w(TAG, value);
        }
    }

    /**
     * W.
     *
     * @param tr the tr
     */
    public static void w(Throwable tr) {
        if (isTest) {
            Log.w(TAG, tr);
        }
    }
}
