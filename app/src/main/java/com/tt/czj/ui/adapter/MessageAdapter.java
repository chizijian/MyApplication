package com.tt.czj.ui.adapter;

import android.content.Context;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tt.czj.R;
import com.tt.czj.mvp.models.Message;
import com.tt.czj.mvp.models.User;
import com.tt.czj.utils.TakePhotoUtil;
import com.tt.czj.widget.CircleImageView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2017/4/05/0005.
 */
public class MessageAdapter extends BaseQuickAdapter<Message,BaseViewHolder>{

    /**
     * Gets context.
     *
     * @return the context
     */
    public Context getContext() {
        return context;
    }

    /**
     * Sets context.
     *
     * @param context the context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    private Context context;

    /**
     * Instantiates a new Message adapter.
     *
     * @param layoutResId the layout res id
     * @param messages    the messages
     * @param context     the context
     */
    public MessageAdapter(int layoutResId,List<Message> messages,Context context) {

        super(layoutResId, messages);
        this.context=context;
        Log.e(TAG, "MessageAdapter: start  "+messages.size() );
    }

    @Override
    protected void convert(final BaseViewHolder helper, final Message item) {
        Log.e(TAG, "convert: start" );
//        helper.setText(R.id.Message_specified,m.getMessage());
        Log.e(TAG, "convert"+ item.getMessage());
        BmobQuery<User> userBmobQuery= new BmobQuery<>();
        userBmobQuery.addWhereEqualTo("objectId",item.getSendMessage_id());
        userBmobQuery.findObjects(context, new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                for (User user:list
                        ) {
                    helper.setText(com.tt.czj.R.id.Message_user_name, user.getUsername());
                    helper.setText(com.tt.czj.R.id.Message_specified, item.getMessage());
                    if (user.getPhoto() != null) {
                        //ImageLoader.getInstance().displayImage(user.getPhoto().getFileUrl(context), (ImageView) helper.getView(R.id.person_photo));
                        CircleImageView circleImageView= helper.getView(R.id.person_photo);
                        circleImageView.setImageUrl(user.getPhoto()
                                .getUrl(), TakePhotoUtil.getmImageLoader(mContext));
                        circleImageView.setDefaultImageResId(R.mipmap.icon_photo);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
}
