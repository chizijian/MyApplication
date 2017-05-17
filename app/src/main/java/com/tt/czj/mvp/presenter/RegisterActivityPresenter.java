package com.tt.czj.mvp.presenter;

import android.content.Context;

/**
 * The interface Register activity presenter.
 */
public interface RegisterActivityPresenter {
    /**
     * Request send sms code.
     *
     * @param context the context
     * @param phone   the phone
     */
    void requestSendSMSCode(Context context,String phone);

    /**
     * Check sms code.
     *
     * @param context the context
     * @param phone   the phone
     * @param check   the check
     */
    void checkSMSCode(Context context,String phone,String check);

    /**
     * Register.
     *
     * @param context  the context
     * @param phone    the phone
     * @param username the username
     * @param pass     the pass
     */
    void register(Context context,String phone,String username,String pass);

    /**
     * Register.
     *
     * @param context  the context
     * @param phone    the phone
     * @param username the username
     * @param pass     the pass
     * @param age      the age
     * @param sex      the sex
     * @param xiaoqu   the xiaoqu
     */
    void register(Context context,String phone,String username,String pass,Integer age,String sex,String xiaoqu,String alipay);
}
