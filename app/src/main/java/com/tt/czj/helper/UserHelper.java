package com.tt.czj.helper;

import android.content.Context;

import com.tt.czj.mvp.models.User;

import cn.bmob.v3.BmobUser;

/**
 * Created by Tczj on 2016/5/16 0016.
 */
public class UserHelper {
    /**
     * The constant TAG.
     */
    public static final String TAG = "UserHelper";

    /**
     * Get current user user.
     *
     * @param context the context
     * @return the user
     */
    public User getCurrentUser(Context context){
        return BmobUser.getCurrentUser(context,User.class);
    }
}
