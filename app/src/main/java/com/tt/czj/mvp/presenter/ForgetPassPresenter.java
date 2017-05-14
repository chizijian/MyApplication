package com.tt.czj.mvp.presenter;

import android.content.Context;

/**
 * The interface Forget pass presenter.
 */
public interface ForgetPassPresenter {
    /**
     * Request send sms code.
     *
     * @param context the context
     * @param phone   the phone
     */
    void requestSendSMSCode(Context context, String phone);

    /**
     * Check sms code.
     *
     * @param context the context
     * @param phone   the phone
     * @param check   the check
     */
    void checkSMSCode(Context context,String phone,String check);

    /**
     * Modify pass.
     *
     * @param context the context
     * @param phone   the phone
     * @param pass    the pass
     */
    void modifyPass(Context context,String phone,String pass);
}
