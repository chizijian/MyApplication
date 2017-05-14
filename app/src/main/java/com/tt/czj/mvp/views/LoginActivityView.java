package com.tt.czj.mvp.views;

import com.tt.czj.mvp.models.User;

/**
 * The interface Login activity view.
 */
public interface LoginActivityView {
    /**
     * Login success.
     *
     * @param user the user
     */
    void loginSuccess(User user);

    /**
     * Login failed.
     *
     * @param str the str
     */
    void loginFailed(String str);
}
