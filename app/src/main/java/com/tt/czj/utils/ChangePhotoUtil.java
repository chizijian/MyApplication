package com.tt.czj.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.tt.czj.R;
import com.tt.czj.mvp.models.User;
import com.tt.czj.widget.CircleImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2017/5/02/0002.
 */
public class ChangePhotoUtil {
    private User user;
    private  Context mContext;

    /**
     * Gets activity.
     *
     * @return the activity
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * Sets activity.
     *
     * @param activity the activity
     * @return the activity
     */
    public ChangePhotoUtil setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    private Activity activity=null;
    private Fragment fragment=null;
    private static final int TAKE_PICTURE = 1;
    private static final int TAKE_PHOTO = 2;
    private static final int CROP_PHOTO = 3;

    private CircleImageView  mPersonCenterPhoto;

    /**
     * Instantiates a new Change photo util.
     *
     * @param mContext           the m context
     * @param activity           the activity
     * @param mPersonCenterPhoto the m person center photo
     */
    public ChangePhotoUtil(Context mContext, Activity activity, CircleImageView mPersonCenterPhoto) {
        this.mContext = mContext;
        this.activity = activity;
        this.mPersonCenterPhoto = mPersonCenterPhoto;
    }

    public ChangePhotoUtil(Context mContext, Fragment fragment, CircleImageView mPersonCenterPhoto) {
        this.fragment=fragment;
        this.mContext = mContext;
        this.mPersonCenterPhoto = mPersonCenterPhoto;
    }
    /**
     * Gets context.
     *
     * @return the context
     */
    public Context getmContext() {
        return mContext;
    }

    /**
     * Sets context.
     *
     * @param mContext the m context
     * @return the context
     */
    public ChangePhotoUtil setmContext(Context mContext) {
        this.mContext = mContext;
        return this;
    }

    /**
     * Gets person center photo.
     *
     * @return the person center photo
     */
    public CircleImageView getmPersonCenterPhoto() {
        return mPersonCenterPhoto;
    }

    /**
     * Sets person center photo.
     *
     * @param mPersonCenterPhoto the m person center photo
     * @return the person center photo
     */
    public ChangePhotoUtil setmPersonCenterPhoto(CircleImageView mPersonCenterPhoto) {
        this.mPersonCenterPhoto = mPersonCenterPhoto;
        return this;
    }

    /**
     * Instantiates a new Change photo util.
     */
    public ChangePhotoUtil() {
    }

    /**
     * Show type dialog.
     */
/*设置头像*/
    public void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(mContext, R.layout.dialog_select_photo, null);

        dialog.setView(view);
        dialog.show();

        TextView tv_select_gallery = ButterKnife.findById(view,R.id.tv_select_gallery);
        TextView tv_select_camera =  ButterKnife.findById(view,R.id.tv_select_camera);
        TextView tv_select_cancle =  ButterKnife.findById(view,R.id.tv_cancle_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                if(activity!=null)
                    activity .startActivityForResult(intent1, TAKE_PICTURE);
                else if(fragment!=null)
                    fragment.startActivityForResult(intent1, TAKE_PICTURE);
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                if(activity!=null)
                    activity .startActivityForResult(intent2, TAKE_PHOTO);
                else if(fragment!=null)
                    fragment.startActivityForResult(intent2, TAKE_PHOTO);
                dialog.dismiss();
            }
        });
        tv_select_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    /**
     * On activity result.
     *
     * @param requestCode the request code
     * @param resultCode  the result code
     * @param data        the data
     */
//@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }
                break;
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }
                break;
            case CROP_PHOTO:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    Bitmap head = extras.getParcelable("data");
                    if (head != null) {
                        if(user==null)
                            user= BmobUser.getCurrentUser(mContext,User.class);
                        uploadPicture(mContext,head,user);
                        /// personCenterFragmentPresenter.loadPhoto(mContext, user.getPhoto().getUrl());
                        mPersonCenterPhoto.setImageBitmap(head);
                        //iv_photo.setImageBitmap(head);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        //super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri the uri
     */
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        if(activity!=null)
            activity.startActivityForResult(intent, CROP_PHOTO);
        else if (fragment!=null)
            fragment.startActivityForResult(intent, CROP_PHOTO);
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
        //如果没有SD卡
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
