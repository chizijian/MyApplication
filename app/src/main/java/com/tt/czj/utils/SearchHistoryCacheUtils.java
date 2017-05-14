package com.tt.czj.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 用户信息缓存工具类
 * Created by Explorer on 2016/3/1.
 */
public class SearchHistoryCacheUtils {

	private final static String PREF_NAME = "search_history";
	private final static String KEY_SEARCH_HISTORY = "history_record";

    /**
     * Gets string.
     *
     * @param ctx          the ctx
     * @param key          the key
     * @param defaultValue the default value
     * @return the string
     */
    public static String getString(Context ctx, String key, String defaultValue) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		return sp.getString(key, defaultValue);
	}

    /**
     * Sets string.
     *
     * @param ctx   the ctx
     * @param key   the key
     * @param value the value
     */
    public static void setString(Context ctx, String key, String value) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		sp.edit().putString(key, value).apply();
	}

    /**
     * Gets int.
     *
     * @param ctx          the ctx
     * @param key          the key
     * @param defaultValue the default value
     * @return the int
     */
    public static int getInt(Context ctx, String key, int defaultValue) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		return sp.getInt(key, defaultValue);
	}

    /**
     * Sets int.
     *
     * @param ctx   the ctx
     * @param key   the key
     * @param value the value
     */
    public static void setInt(Context ctx, String key, int value) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		sp.edit().putInt(key, value).apply();
	}

    /**
     * 设置缓存
     *
     * @param value the value
     * @param ctx   the ctx
     */
    public static void setCache(String value,Context ctx){
		setString(ctx, KEY_SEARCH_HISTORY, value);
	}

    /**
     * 获取缓存
     *
     * @param ctx the ctx
     * @return the string
     */
    public static String getCache(Context ctx){
		return getString(ctx,KEY_SEARCH_HISTORY,null);
	}

    /**
     * 清空指定缓存信息
     *
     * @param ctx the ctx
     */
    public static void ClearCache(Context ctx){
			setString(ctx,KEY_SEARCH_HISTORY,null);
	}

}
