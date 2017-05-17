package com.tt.czj.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.tt.czj.mvp.models.User;
import com.tt.czj.mvp.views.RegisterView;
import com.tt.czj.utils.GlobalDefineValues;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;

/**
 * The type Register activity presenter.
 */
public class RegisterActivityPresenterImpl implements RegisterActivityPresenter {
    private RegisterView homeView;
    User bu = new User();

    /**
     * Instantiates a new Register activity presenter.
     *
     * @param homeView the home view
     */
    public RegisterActivityPresenterImpl(RegisterView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void requestSendSMSCode(final Context context, final String phone) {
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("mobilePhoneNumber", phone);
        query.findObjects(context, new FindListener<User>() {
            @Override
            public void onSuccess(List<User> object) {
                // TODO Auto-generated method stub
                BmobSMS.requestSMSCode(context, phone, GlobalDefineValues.Register,new RequestSMSCodeListener() {
                    @Override
                    public void done(Integer smsId,BmobException ex) {
                        // TODO Auto-generated method stub
                        if(ex==null){
                            Log.i("bmob", "短信id："+smsId);
                            homeView.getSMSCodeSuccess();
                        }else{
                            homeView.getSMSCodeFailed(ex.getLocalizedMessage());
                        }
                    }
                });
            }
            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
                homeView.getSMSCodeFailed(msg);
            }
        });
    }

    @Override
    public void checkSMSCode(Context context,String phone, String check) {
        BmobSMS.verifySmsCode(context,phone, check, new VerifySMSCodeListener() {
            @Override
            public void done(BmobException ex) {
                // TODO Auto-generated method stub
                if(ex==null){
                    homeView.checkRight();
                }else{
                    homeView.checkError(ex.getLocalizedMessage());
                }
            }
        });
    }

    @Override
    public void register(Context context,String phone, String username, String pass) {
        final User bu = new User();
        bu.setUsername(username);
        bu.setPassword(pass);
        bu.setMobilePhoneNumber(phone);

        bu.signUp(context, new SaveListener() {
            @Override
            public void onSuccess() {
                homeView.registerSuccess(bu);
            }
            @Override
            public void onFailure(int code, String msg) {
                homeView.registerFailed(msg);
            }
        });
    }

    @Override
    public void register(Context context, String phone, String username, String pass, Integer age, String sex, String xiaoqu,String alipay) {
        //final User bu = new User();
        bu.setUsername(username);
        bu.setPassword(pass);
        bu.setMobilePhoneNumber(phone);
        bu.setAge(age);
        bu.setSex(sex);
        bu.setXiaoqu(xiaoqu);
        bu.setAlipay(alipay);
        bu.signUp(context, new SaveListener() {
            @Override
            public void onSuccess() {
                homeView.registerSuccess(bu);
            }
            @Override
            public void onFailure(int code, String msg) {
                homeView.registerFailed(msg);
            }
        });
        //uploadPicture(context, BitmapFactory.decodeResource(context.getResources(),R.mipmap.icon_photo),bu);
    }
}
