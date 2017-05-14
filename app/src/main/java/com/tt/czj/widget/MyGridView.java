package com.tt.czj.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by czj on 16-6-14.
 */
public class MyGridView extends GridView {
    /**
     * Instantiates a new My grid view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new My grid view.
     *
     * @param context the context
     */
    public MyGridView(Context context) {
        super(context);
    }

    /**
     * Instantiates a new My grid view.
     *
     * @param context  the context
     * @param attrs    the attrs
     * @param defStyle the def style
     */
    public MyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}