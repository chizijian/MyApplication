package com.tt.czj.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The type Ui utils.
 */
public class UIUtils {

    /**
     * Show msg.
     *
     * @param context     the context
     * @param showContent the show content
     */
    public static void showMsg(Context context, String showContent) {
        Toast.makeText(context, showContent, Toast.LENGTH_SHORT).show();
    }

    /**
     * Show msg long.
     *
     * @param context     the context
     * @param showContent the show content
     */
    public static void showMsgLong(Context context, String showContent) {
        Toast.makeText(context, showContent, Toast.LENGTH_LONG).show();
    }

    /**
     * Gets display metrics.
     *
     * @param context the context
     * @return the display metrics
     */
    public static DisplayMetrics getDisplayMetrics(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    /**
     * Start animation.
     *
     * @param activity    the activity
     * @param animationId the animation id
     * @param imageView   the image view
     */
    public static void startAnimation(Activity activity, int animationId, ImageView imageView) {
        Animation loading = AnimationUtils.loadAnimation(activity, animationId);
        loading.setInterpolator(new LinearInterpolator());
        imageView.startAnimation(loading);
    }

    /**
     * Calc text size by screen size float.
     *
     * @param activity    the activity
     * @param length      the length
     * @param reducePixes the reduce pixes
     * @param maxTextSize the max text size
     * @return the float
     */
    public static float calcTextSizeByScreenSize(Activity activity, int length, int reducePixes, int maxTextSize) {
        DisplayMetrics dm = getDisplayMetrics(activity);
        float textSize = ((float) ((dm.widthPixels - reducePixes) / length)) / dm.density;
        if (textSize > ((float) maxTextSize)) {
            return (float) DisplayUtil.px2sp((float) maxTextSize, dm.density);
        }
        return textSize;
    }

    /**
     * Drawable to bitmap bitmap.
     *
     * @param drawable the drawable
     * @return the bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * Load progress progress dialog.
     *
     * @param activity the activity
     * @return the progress dialog
     */
    public static ProgressDialog loadProgress(Context activity) {
        ProgressDialog mydialog = new ProgressDialog(activity);
        mydialog.setProgressStyle(0);
        mydialog.setTitle("数据加载提示");
        mydialog.setMessage("Loading ...");
        mydialog.setIndeterminate(false);
        mydialog.show();
        return mydialog;
    }

    /**
     * Gets view from layout.
     *
     * @param activity the activity
     * @param layoutID the layout id
     * @return the view from layout
     */
    public static View getViewFromLayout(Context activity, int layoutID) {
        return LayoutInflater.from(activity).inflate(layoutID, null);
    }

    /**
     * Send message 2 handler.
     *
     * @param handler the handler
     * @param what    the what
     */
    public static void sendMessage2Handler(Handler handler, int what) {
        handler.sendMessage(handler.obtainMessage(what));
    }

    /**
     * Send message 2 handler.
     *
     * @param handler the handler
     * @param what    the what
     * @param arg1    the arg 1
     */
    public static void sendMessage2Handler(Handler handler, int what, int arg1) {
        handler.sendMessage(handler.obtainMessage(what, arg1, arg1));
    }

    /**
     * Send message 2 handler.
     *
     * @param handler the handler
     * @param what    the what
     * @param arg1    the arg 1
     * @param object  the object
     */
    public static void sendMessage2Handler(Handler handler, int what, int arg1, Object object) {
        handler.sendMessage(handler.obtainMessage(what, arg1, arg1, object));
    }

    /**
     * Send message 2 handler.
     *
     * @param handler the handler
     * @param what    the what
     * @param object  the object
     */
    public static void sendMessage2Handler(Handler handler, int what, Object object) {
        handler.sendMessage(handler.obtainMessage(what, object));
    }

    /**
     * Sets window keep screen on.
     *
     * @param context the context
     */
    public static void setWindowKeepScreenOn(Activity context) {
        context.requestWindowFeature(1);
        context.getWindow().addFlags(128);
    }

    /**
     * Sets window full screen.
     *
     * @param context the context
     */
    public static void setWindowFullScreen(Activity context) {
        context.requestWindowFeature(1);
        context.getWindow().addFlags(1024);
    }

    /**
     * Next page.
     *
     * @param context the context
     * @param target  the target
     */
    public static void nextPage(Context context, Class<? extends Activity> target) {
        Intent intent = new Intent();
        intent.setClass(context, target);
        context.startActivity(intent);
    }

    /**
     * Next page.
     *
     * @param context the context
     * @param target  the target
     * @param entry   the entry
     */
    public static void nextPage(Context context, Class<? extends Activity> target, Entry<String, String> entry) {
        Intent intent = new Intent();
        intent.setClass(context, target);
        intent.putExtra((String) entry.getKey(), (String) entry.getValue());
        context.startActivity(intent);
    }

    /**
     * Next page.
     *
     * @param context the context
     * @param target  the target
     * @param entry   the entry
     * @param flag    the flag
     */
    public static void nextPage(Context context, Class<? extends Activity> target, Entry<String, String> entry, int flag) {
        Intent intent = new Intent();
        intent.addFlags(flag);
        intent.setClass(context, target);
        intent.putExtra((String) entry.getKey(), (String) entry.getValue());
        context.startActivity(intent);
    }

    /**
     * Next page.
     *
     * @param context the context
     * @param target  the target
     * @param map     the map
     */
    public static void nextPage(Context context, Class<? extends Activity> target, Map<String, String> map) {
        Intent intent = new Intent();
        intent.setClass(context, target);
        for (String key : map.keySet()) {
            intent.putExtra(key, (String) map.get(key));
        }
        context.startActivity(intent);
    }

    /**
     * Next page.
     *
     * @param context the context
     * @param target  the target
     * @param bundle  the bundle
     */
    public static void nextPage(Context context, Class<? extends Activity> target, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * Sets text to text view.
     *
     * @param context the context
     * @param id      the id
     * @param content the content
     * @return the text to text view
     */
    public static TextView setTextToTextView(Activity context, int id, String content) {
        TextView textView = (TextView) context.findViewById(id);
        textView.setText(content);
        return textView;
    }

    /**
     * Sets size to text view.
     *
     * @param context  the context
     * @param id       the id
     * @param textSize the text size
     * @return the size to text view
     */
    public static TextView setSizeToTextView(Activity context, int id, float textSize) {
        TextView textView = (TextView) context.findViewById(id);
        textView.setTextSize(textSize);
        return textView;
    }

    /**
     * Hidden view view.
     *
     * @param context the context
     * @param id      the id
     * @return the view
     */
    public static View hiddenView(Activity context, int id) {
        View view = context.findViewById(id);
        view.setVisibility(View.INVISIBLE);
        return view;
    }
}
