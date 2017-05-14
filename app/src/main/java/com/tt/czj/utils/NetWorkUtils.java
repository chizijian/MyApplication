package com.tt.czj.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.SmsManager;

import java.util.concurrent.atomic.AtomicBoolean;


/**
 * The type Net work utils.
 */
public class NetWorkUtils {
    /**
     * The constant IS_AVAILABLE_NETWORK.
     */
    public static AtomicBoolean IS_AVAILABLE_NETWORK = new AtomicBoolean(true);
    /**
     * The constant NETWORK_EXTRA_INFO.
     */
    public static int NETWORK_EXTRA_INFO = -1;
    /**
     * The constant NETWORK_EXTRA_INFO_BASE.
     */
    public static final int NETWORK_EXTRA_INFO_BASE = 1;
    /**
     * The constant NETWORK_EXTRA_INFO_NON.
     */
    public static final int NETWORK_EXTRA_INFO_NON = -1;
    /**
     * The constant NETWORK_EXTRA_INFO_WIFI.
     */
    public static final int NETWORK_EXTRA_INFO_WIFI = 0;

    /**
     * Is wifi network available boolean.
     *
     * @param context the context
     * @return the boolean
     */
    public static boolean isWifiNetworkAvailable(Context context) {
        return isAvailableNetWorkByType(context, 1);
    }

    /**
     * Is 3 g boolean.
     *
     * @param context the context
     * @return the boolean
     */
    public static boolean is3G(Context context) {
        return isAvailableNetWorkByType(context, 1);
    }

    /**
     * Is 2 g boolean.
     *
     * @param context the context
     * @return the boolean
     */
    public static boolean is2G(Context context) {
        NetworkInfo netInfo = getNetworkInfo(context);
        if (netInfo == null || (netInfo.getSubtype() != 2 && netInfo.getSubtype() != 1 && netInfo.getSubtype() != 4)) {
            return false;
        }
        return true;
    }

    /**
     * Sent message boolean.
     *
     * @param activity    the activity
     * @param phoneNumber the phone number
     * @param content     the content
     * @return the boolean
     */
    public static boolean sentMessage(Context activity, String phoneNumber, String content) {
        SmsManager smsManager = SmsManager.getDefault();
        PendingIntent sentIntent = PendingIntent.getBroadcast(activity, 0, new Intent(), 0);
        if (content.length() > 70) {
            for (String msg : smsManager.divideMessage(content)) {
                smsManager.sendTextMessage(phoneNumber, null, msg, sentIntent, null);
            }
        } else {
            smsManager.sendTextMessage(phoneNumber, null, content, sentIntent, null);
        }
        return true;
    }

//    public static void setNetworkState(Context context) {
//        if (networkIsAvailable(context)) {
//            IS_AVAILABLE_NETWORK.set(true);
//            NetworkInfo networkInfo = getNetworkInfo(context);
//            String extraInfo = networkInfo.getExtraInfo();
//            if ((extraInfo != null && "MOBILE".equalsIgnoreCase(networkInfo.getTypeName()) && ("cmwap".equals(extraInfo) || "3gwap".equals(extraInfo))) || "ctwap".equals(extraInfo) || "3gnet".equals(extraInfo)) {
//                if (NETWORK_EXTRA_INFO != 1) {
//                    EventBus.getDefault().post(Integer.valueOf(1));
//                }
//                NETWORK_EXTRA_INFO = 1;
//                if ("CTWAP".equalsIgnoreCase(extraInfo)) {
//                    HttpUtils.host = "10.0.0.200";
//                    return;
//                } else {
//                    HttpUtils.host = "10.0.0.172";
//                    return;
//                }
//            }
//            if (NETWORK_EXTRA_INFO != 0) {
//                EventBus.getDefault().post(Integer.valueOf(0));
//            }
//            NETWORK_EXTRA_INFO = 0;
//            return;
//        }
//        if (NETWORK_EXTRA_INFO != -1) {
//            EventBus.getDefault().post(Integer.valueOf(-1));
//        }
//        IS_AVAILABLE_NETWORK.set(false);
//        NETWORK_EXTRA_INFO = -1;
//    }

    /**
     * Network is available boolean.
     *
     * @param context the context
     * @return the boolean
     */
    public static boolean networkIsAvailable(Context context) {
        NetworkInfo network = getNetworkInfo(context);
        if (network == null) {
            return false;
        }
        return network.isAvailable();
    }

    /**
     * Is available net work by type boolean.
     *
     * @param context the context
     * @param type    the type
     * @return the boolean
     */
    public static boolean isAvailableNetWorkByType(Context context, int type) {
        NetworkInfo activeNetInfo = getNetworkInfo(context);
        return activeNetInfo != null && activeNetInfo.getType() == type;
    }

    /**
     * Gets network info.
     *
     * @param context the context
     * @return the network info
     */
    public static NetworkInfo getNetworkInfo(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    }
}
