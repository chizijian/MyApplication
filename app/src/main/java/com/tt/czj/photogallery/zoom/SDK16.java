package com.tt.czj.photogallery.zoom;

import android.annotation.TargetApi;
import android.view.View;

/**
 * The type Sdk 16.
 */
@TargetApi(16)
public class SDK16 {

    /**
     * Post on animation.
     *
     * @param view the view
     * @param r    the r
     */
    public static void postOnAnimation(View view, Runnable r) {
		view.postOnAnimation(r);
	}
	
}
