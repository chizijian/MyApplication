package com.tt.czj.mvp.presenter;

import android.content.Context;

/**
 * The interface Person center fragment presenter.
 */
public interface PersonCenterFragmentPresenter {
    /**
     * Load photo.
     *
     * @param context the context
     * @param url     the url
     */
    void loadPhoto(Context context,String url);
}
