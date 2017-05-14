package com.tt.czj.photogallery.zoom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * The type View pager fixed.
 */
public class ViewPagerFixed extends android.support.v4.view.ViewPager {

    /**
     * Instantiates a new View pager fixed.
     *
     * @param context the context
     */
    public ViewPagerFixed(Context context) {
        super(context);
    }

    /**
     * Instantiates a new View pager fixed.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public ViewPagerFixed(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
