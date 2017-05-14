package com.tt.czj.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/3/29 0029.
 */
public class MeasureUtils {

    /**
     * Get text view center x float.
     *
     * @param textView the text view
     * @return the float
     */
    public static float getTextViewCenterX(TextView textView){
        float x = (textView.getLeft()+textView.getRight())/2;
        return x;
    }

    /**
     * Get text view center y float.
     *
     * @param textView the text view
     * @return the float
     */
    public static float getTextViewCenterY(TextView textView){
        float y = (textView.getTop()+textView.getBottom())/2;
        return y;
    }

    /**
     * Get screen width int.
     *
     * @param context the context
     * @return the int
     */
    public static int getScreenWidth(Context context){
        DisplayMetrics dm =context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * Get screen height int.
     *
     * @param context the context
     * @return the int
     */
    public static int getScreenHeight(Context context){
        DisplayMetrics dm =context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * Get base cell width int.
     *
     * @param screenWidth the screen width
     * @return the int
     */
    public static int getBaseCellWidth(int screenWidth){
        return (screenWidth - 3*5)/4;
    }

    /**
     * Get base 3 width int.
     *
     * @param screenWidth the screen width
     * @return the int
     */
    public static int getBase3Width(int screenWidth){
        return (screenWidth - 2*5)/3;
    }

    /**
     * Get base 5 width int.
     *
     * @param screenWidth the screen width
     * @return the int
     */
    public static int getBase5Width(int screenWidth){
        return (screenWidth - 4*5)/5;
    }
}
