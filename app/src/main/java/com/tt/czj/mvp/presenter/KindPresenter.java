package com.tt.czj.mvp.presenter;

import android.content.Context;

import com.tt.czj.mvp.models.Kind;

/**
 * The interface Kind presenter.
 */
public interface KindPresenter {
    /**
     * The constant TAG.
     */
    public static final String TAG = "KindPresenter";

    /**
     * Load kind.
     *
     * @param context the context
     */
    void loadKind(Context context);

    /**
     * Load second kind.
     *
     * @param context the context
     * @param kind    the kind
     */
    void loadSecondKind(Context context,Kind kind);
}
