package com.tt.czj.mvp.views;

/**
 * The interface Register view.
 */
public interface RegisterView {
    /**
     * Check sms code statuse.
     *
     * @param str the str
     */
    void checkSmsCodeStatuse(String str);

    /**
     * Register success.
     */
    void registerSuccess();

    /**
     * Register failed.
     *
     * @param str the str
     */
    void registerFailed(String str);

    /**
     * Check right.
     */
    void checkRight();

    /**
     * Check error.
     *
     * @param str the str
     */
    void checkError(String str);

    /**
     * Gets sms code success.
     */
    void getSMSCodeSuccess();

    /**
     * Gets sms code failed.
     *
     * @param str the str
     */
    void getSMSCodeFailed(String str);
}
