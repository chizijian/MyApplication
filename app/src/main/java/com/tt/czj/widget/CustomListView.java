package com.tt.czj.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by czj on 16-5-17.
 */
public class CustomListView extends ListView {
    /**
     * Instantiates a new Custom list view.
     *
     * @param context the context
     */
    public CustomListView(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Custom list view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Custom list view.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
