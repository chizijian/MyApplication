package com.tt.czj.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.tt.czj.mvp.models.Message;
import com.tt.czj.mvp.models.User;
import com.tt.czj.mvp.views.LeaveMessageActivityView;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * The type Leave message presenterlmpl.
 */
public class LeaveMessagePresenterlmpl implements LeaveMessagePresenter {
    private LeaveMessageActivityView view;

    /**
     * Instantiates a new Leave message presenterlmpl.
     *
     * @param view the view
     */
    public LeaveMessagePresenterlmpl(LeaveMessageActivityView view){
        this.view=view;
    }

    @Override
    public void leaveMessage(final Context context, String accepter, String message, String Message_Goodsid) {
        final Message mMessage=new Message();
        mMessage.setMessage(message);
        mMessage.setMessage_Goodsid(Message_Goodsid);
        mMessage.setSendMessage_id(BmobUser.getCurrentUser(context,User.class).getObjectId());
        mMessage.setAcceptMessage_id(accepter);

        mMessage.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                view.leaveMessageSuccess();
                Log.e("LeaveMessage", "onSuccess" );
            }

            @Override
            public void onFailure(int i, String s) {
                view.leaveMessageError(s);
            }
        });
    }
}
