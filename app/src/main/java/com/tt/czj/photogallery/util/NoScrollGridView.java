package com.tt.czj.photogallery.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * The type No scroll grid view.
 */
public class NoScrollGridView extends GridView {
    /**
     * Instantiates a new No scroll grid view.
     *
     * @param context the context
     */
    public NoScrollGridView(Context context) {
		super(context);
	}

    /**
     * Instantiates a new No scroll grid view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public NoScrollGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
