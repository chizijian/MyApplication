package com.tt.czj.mvp.views;

/**
 * The interface Forget pass view.
 */
public interface ForgetPassView {
    /**
     * Found success.
     */
    void foundSuccess();

    /**
     * Found failed.
     *
     * @param str the str
     */
    void foundFailed(String str);

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
