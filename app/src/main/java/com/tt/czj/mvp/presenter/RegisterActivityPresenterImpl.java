package com.tt.czj.mvp.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.tt.czj.R;
import com.tt.czj.mvp.models.User;
import com.tt.czj.mvp.views.RegisterView;
import com.tt.czj.utils.GlobalDefineValues;
import com.tt.czj.utils.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;

/**
 * The type Register activity presenter.
 */
public class RegisterActivityPresenterImpl implements RegisterActivityPresenter {
    private RegisterView homeView;

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
        BmobQuery<User> query = new BmobQuery<User>();
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
        User bu = new User();
        bu.setUsername(username);
        bu.setPassword(pass);
        bu.setMobilePhoneNumber(phone);

        bu.signUp(context, new SaveListener() {
            @Override
            public void onSuccess() {
                homeView.registerSuccess();
            }
            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                homeView.registerFailed(msg);
            }
        });
    }

    @Override
    public void register(Context context, String phone, String username, String pass, Integer age, String sex, String xiaoqu) {
        User bu = new User();
        bu.setUsername(username);
        bu.setPassword(pass);
        bu.setMobilePhoneNumber(phone);
        bu.setAge(age);
        bu.setSex(sex);
        bu.setXiaoqu(xiaoqu);
        bu.signUp(context, new SaveListener() {
            @Override
            public void onSuccess() {
                homeView.registerSuccess();
            }
            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                homeView.registerFailed(msg);
            }
        });
        uploadPicture(context, BitmapFactory.decodeResource(context.getResources(),R.mipmap.icon_photo),bu);
    }

    private void uploadPicture(final Context mContext, final Bitmap images, final User user) {
        final Uri uri=saveBitmap(images);
        final BmobFile file = new BmobFile(new File(uri.getPath()));
        file.uploadblock(mContext, new UploadFileListener() {
            @Override
            public void onSuccess() {
                user.setPhoto(file);
                user.update(mContext, new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        ToastUtil.showToast(mContext,"保存成功");
                    }

                    @Override
                    public void onFailure(int i, String s) {

                    }
                });
            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }

    private Uri saveBitmap(Bitmap bm) {
        File tmpDir;
        if (hasSD()) {
            tmpDir = new File(Environment.getExternalStorageDirectory() + "/tradein/");
        } else {
            tmpDir = new File(Environment.getDataDirectory() + "/tradein/");
        }

        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }
        File img = new File(tmpDir.getAbsolutePath() + System.currentTimeMillis() + ".png");
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 是否有SD卡
     */
    private boolean hasSD() {
        //如果有SD卡 则下载到SD卡中
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;

        } else {
            //如果没有SD卡
            return false;
        }
    }
}
