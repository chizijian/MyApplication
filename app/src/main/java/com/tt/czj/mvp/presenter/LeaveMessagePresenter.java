package com.tt.czj.mvp.presenter;

import android.content.Context;

/**
 * The interface Leave message presenter.
 */
public interface LeaveMessagePresenter {
    /**
     * Leave message.
     *
     * @param context         the context
     * @param accepter        the accepter
     * @param message         the message
     * @param Message_Goodsid the message goodsid
     */
    void leaveMessage(Context context, String accepter, String message, String Message_Goodsid);
}
