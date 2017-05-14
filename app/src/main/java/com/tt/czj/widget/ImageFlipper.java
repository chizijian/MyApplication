package com.tt.czj.widget;

/**
 * Created by czj on 2016/5/6 0006.
 */
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.tt.czj.R;

/**
 * The type Image flipper.
 */
public class ImageFlipper extends ViewFlipper {
    private Animation previousInAnimation, previousOutAnimation; // 向前切换时，进出动画
    private Animation nextInAnimation, nextOutAnimation; // 前后切换时，进出动画
    private boolean animRunning;
    private AnimationListener mAnimationListener =  new AnimationListener() {

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub
            animRunning  = true;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            // TODO Auto-generated method stub
            animRunning = false;

            if(mOnPageChangeListener != null)
                mOnPageChangeListener.onPageSelected(indexOfChild(getCurrentView()));
        }
    };
    private float firstX, deltaX;
    private int flipSpacing = 100; // 触摸触发切换，生效距离
    private OnPageChangeListener mOnPageChangeListener; // 切换事件监听器

    /**
     * Instantiates a new Image flipper.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public ImageFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init();
    }

    /**
     * Instantiates a new Image flipper.
     *
     * @param context the context
     */
    public ImageFlipper(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    private void init(){
        setAnimation(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
        setFlipInterval(2000);
        setAutoStart(true);
    }

    @Override
    public void showPrevious() {
        setInAnimation(previousInAnimation);
        setOutAnimation(previousOutAnimation);
        super.showPrevious();
    }

    @Override
    public void showNext() {
        setInAnimation(nextInAnimation);
        setOutAnimation(nextOutAnimation);
        super.showNext();
    }

    /**
     * Set views.
     *
     * @param views the views
     */
    public void setViews(View[] views){
        for(View v:views)
            addView(v, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    /**
     * Set views.
     *
     * @param ids the ids
     */
    public void setViews(int[] ids){
        removeAllViews();
        for(int id:ids){
            addView(id);
        }
    }

    /**
     * Add view.
     *
     * @param id the id
     */
    public void addView(int id) {
        ImageView iv = new ImageView(getContext());
        iv.setImageResource(id);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(iv, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    /**
     * Add view.
     *
     * @param imageView the image view
     */
    public void addView(ImageView imageView) {
        addView(imageView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        // 动画过程中，不响应触摸操作
        if(animRunning)
            return true;

        /*switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN:

            // 暂停自动切换
            if(isFlipping())
                stopFlipping();

            firstX = ev.getX();
            break;
        case MotionEvent.ACTION_UP:

            deltaX = ev.getX() - firstX;
            if(Math.abs(deltaX) > flipSpacing){

                if(deltaX > 0)
                    this.showPrevious();
                else {
                    this.showNext();

                    // 启动自动切换
                    // 起落间隔距离不足时，之后不会再有自动切换
                    this.startFlipping();
                }
            }

            break;
        }*/

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 暂停自动切换
                if(isFlipping())
                    stopFlipping();
                firstX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if(firstX == -1)
                    break;
                deltaX = ev.getX() - firstX;
                if(Math.abs(deltaX) > flipSpacing){

                    if(deltaX > 0)
                        this.showPrevious();
                    else {
                        this.showNext();
                        this.startFlipping();
                    }
                    firstX = -1;
                }
                break;
            case MotionEvent.ACTION_UP:
//                this.startFlipping();
                break;
        }

        return true;
    }

    /**
     * Set animation.
     *
     * @param pin  the pin
     * @param pout the pout
     * @param nin  the nin
     * @param nout the nout
     */
    public void setAnimation(Animation pin, Animation pout, Animation nin, Animation nout){
        this.previousInAnimation = pin;
        this.previousOutAnimation = pout;
        this.nextInAnimation = nin;
        this.nextOutAnimation = nout;
        this.previousInAnimation.setAnimationListener(mAnimationListener);
        this.nextInAnimation.setAnimationListener(mAnimationListener);
    }

    /**
     * Set animation.
     *
     * @param pin  the pin
     * @param pout the pout
     * @param nin  the nin
     * @param nout the nout
     */
    public void setAnimation(int pin, int pout, int nin, int nout){
        this.setAnimation(
                AnimationUtils.loadAnimation(getContext(), pin),
                AnimationUtils.loadAnimation(getContext(), pout),
                AnimationUtils.loadAnimation(getContext(), nin),
                AnimationUtils.loadAnimation(getContext(), nout));
    }

    /**
     * Gets on page change listener.
     *
     * @return the on page change listener
     */
    public OnPageChangeListener getOnPageChangeListener() {
        return mOnPageChangeListener;
    }

    /**
     * Sets on page change listener.
     *
     * @param mOnPageChangeListener the m on page change listener
     */
    public void setOnPageChangeListener(OnPageChangeListener mOnPageChangeListener) {
        this.mOnPageChangeListener = mOnPageChangeListener;
    }

    /**
     * 页面切换监听器
     */
    public static interface OnPageChangeListener {
        /**
         * On page selected.
         *
         * @param index the index
         */
        public void onPageSelected(int index);
    }
}
