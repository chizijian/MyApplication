package com.tt.czj.mvp.views;

/**
 * The interface Leave message activity view.
 */
public interface LeaveMessageActivityView {
    /**
     * Leave message success.
     */
    void leaveMessageSuccess();

    /**
     * Leave message error.
     *
     * @param str the str
     */
    void leaveMessageError(String str);
}
