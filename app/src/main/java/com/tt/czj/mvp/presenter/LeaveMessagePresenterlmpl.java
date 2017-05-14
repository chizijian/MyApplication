package com.tt.czj.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.models.Message;
import com.tt.czj.mvp.models.User;
import com.tt.czj.mvp.views.LeaveMessageActivityView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
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

        User sender=BmobUser.getCurrentUser(context,User.class);

        mMessage.setSendMessage_id(sender.getObjectId());

        BmobQuery<Goods> bmobQuery=new BmobQuery<Goods>();
        bmobQuery.addWhereEqualTo("objectId",Message_Goodsid);
        bmobQuery.findObjects(context, new FindListener<Goods>() {
            @Override
            public void onSuccess(List<Goods> list) {
                for (Goods g:list
                     ) {
                    BmobQuery<User> UserQuery=new BmobQuery<User>();
                    UserQuery.addWhereEqualTo("objectId",g.getUserid());
                    UserQuery.findObjects(context, new FindListener<User>() {
                        @Override
                        public void onSuccess(List<User> list) {
                            for (User user:list
                                 ) {
                                mMessage.setAcceptMessage_id(user.getObjectId());
                            }
                        }

                        @Override
                        public void onError(int i, String s) {

                        }
                    });
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });

        mMessage.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                Log.e("LeaveMessage", "onSuccess" );
            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }
}
