package com.tt.czj.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by czj on 16-5-16.
 */
public class CustomExpandableListView extends ExpandableListView {
    /**
     * Instantiates a new Custom expandable list view.
     *
     * @param context the context
     */
    public CustomExpandableListView(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Custom expandable list view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public CustomExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Custom expandable list view.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public CustomExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
