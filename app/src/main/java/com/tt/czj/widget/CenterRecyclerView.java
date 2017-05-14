package com.tt.czj.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;

import com.tt.czj.ui.activity.GoodsDetailActivity;

/**
 * Created by Administrator on 2017/3/31/0031.
 */
public class CenterRecyclerView extends RecyclerView {
    private Context context;

    /**
     * Instantiates a new Center recycler view.
     *
     * @param context the context
     */
    public CenterRecyclerView(Context context) {
        super(context);
        this.context=context;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) {
            if (CenterRecyclerView.this.hasFocus()) {
                refreshFocusItemToCenter();
            }
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * Refresh focus item to center.
     */
    public void refreshFocusItemToCenter(){
        View tView =((GoodsDetailActivity)getContext()).getCurrentFocus();
        if (tView == null) {
            return;
        }
        int[] tPosition = new int[2];
        tView.getLocationInWindow(tPosition);
        int tDes = (int) ((this.getX() + this.getWidth()/2) - tView.getWidth() * tView.getScaleX()/2);
        if (tPosition != null && tPosition[0] != tDes) {
            this.smoothScrollBy(tPosition[0] - tDes, 0);
            postInvalidate();
        }
    }
}
