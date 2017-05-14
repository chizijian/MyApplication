package com.tt.czj.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.animation.Animation;

/**
 * The type Loading renderer.
 */
public abstract class LoadingRenderer {
  private static final long ANIMATION_DURATION = 1333;

  private static final float DEFAULT_SIZE = 56.0f;
  private static final float DEFAULT_CENTER_RADIUS = 12.5f;
  private static final float DEFAULT_STROKE_WIDTH = 2.5f;

    /**
     * The M width.
     */
    protected float mWidth;
    /**
     * The M height.
     */
    protected float mHeight;
    /**
     * The M stroke width.
     */
    protected float mStrokeWidth;
    /**
     * The M center radius.
     */
    protected float mCenterRadius;

  private long mDuration;
  private Drawable.Callback mCallback;
  private ValueAnimator mRenderAnimator;

    /**
     * Instantiates a new Loading renderer.
     *
     * @param context the context
     */
    public LoadingRenderer(Context context) {
    setupDefaultParams(context);
    setupAnimators();
  }

    /**
     * Draw.
     *
     * @param canvas the canvas
     * @param bounds the bounds
     */
    public abstract void draw(Canvas canvas, Rect bounds);

    /**
     * Compute render.
     *
     * @param renderProgress the render progress
     */
    public abstract void computeRender(float renderProgress);

    /**
     * Sets alpha.
     *
     * @param alpha the alpha
     */
    public abstract void setAlpha(int alpha);

    /**
     * Sets color filter.
     *
     * @param cf the cf
     */
    public abstract void setColorFilter(ColorFilter cf);

    /**
     * Reset.
     */
    public abstract void reset();

    /**
     * Start.
     */
    public void start() {
    reset();
    setDuration(mDuration);
    mRenderAnimator.start();
  }

    /**
     * Stop.
     */
    public void stop() {
    mRenderAnimator.cancel();
  }

    /**
     * Is running boolean.
     *
     * @return the boolean
     */
    public boolean isRunning() {
    return mRenderAnimator.isRunning();
  }

    /**
     * Sets callback.
     *
     * @param callback the callback
     */
    public void setCallback(Drawable.Callback callback) {
    this.mCallback = callback;
  }

    /**
     * Invalidate self.
     */
    protected void invalidateSelf() {
    mCallback.invalidateDrawable(null);
  }

  private void setupDefaultParams(Context context) {
    final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
    final float screenDensity = metrics.density;

    mWidth = DEFAULT_SIZE * screenDensity;
    mHeight = DEFAULT_SIZE * screenDensity;
    mStrokeWidth = DEFAULT_STROKE_WIDTH * screenDensity;
    mCenterRadius = DEFAULT_CENTER_RADIUS * screenDensity;

    mDuration = ANIMATION_DURATION;
  }

  private void setupAnimators() {
    mRenderAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
    mRenderAnimator.setRepeatCount(Animation.INFINITE);
    mRenderAnimator.setRepeatMode(ValueAnimator.RESTART);
    mRenderAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        computeRender((float) animation.getAnimatedValue());
        invalidateSelf();
      }
    });
  }

    /**
     * Add render listener.
     *
     * @param animatorListener the animator listener
     */
    protected void addRenderListener(Animator.AnimatorListener animatorListener) {
    mRenderAnimator.addListener(animatorListener);
  }

    /**
     * Sets center radius.
     *
     * @param centerRadius the center radius
     */
    public void setCenterRadius(float centerRadius) {
    mCenterRadius = centerRadius;
  }

    /**
     * Gets center radius.
     *
     * @return the center radius
     */
    public float getCenterRadius() {
    return mCenterRadius;
  }

    /**
     * Sets stroke width.
     *
     * @param strokeWidth the stroke width
     */
    public void setStrokeWidth(float strokeWidth) {
    mStrokeWidth = strokeWidth;
  }

    /**
     * Gets stroke width.
     *
     * @return the stroke width
     */
    public float getStrokeWidth() {
    return mStrokeWidth;
  }

    /**
     * Gets width.
     *
     * @return the width
     */
    public float getWidth() {
    return mWidth;
  }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(float width) {
    this.mWidth = width;
  }

    /**
     * Gets height.
     *
     * @return the height
     */
    public float getHeight() {
    return mHeight;
  }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(float height) {
    this.mHeight = height;
  }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public long getDuration() {
    return mDuration;
  }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(long duration) {
    this.mDuration = duration;
    mRenderAnimator.setDuration(mDuration);
  }
}
