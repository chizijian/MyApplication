package com.tt.czj.mvp.presenter;

import android.content.Context;

/**
 * The interface Login activity presenter.
 */
public interface LoginActivityPresenter {
    /**
     * Login.
     *
     * @param username the username
     * @param pass     the pass
     * @param context  the context
     */
    void login(String username, String pass, Context context);
}
