package com.tt.czj.ui.activity.own;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.tt.czj.R;
import com.tt.czj.mvp.models.User;
import com.tt.czj.ui.activity.base.BaseActivity;
import com.tt.czj.utils.ChangePhotoUtil;
import com.tt.czj.utils.TakePhotoUtil;
import com.tt.czj.utils.ToastUtil;
import com.tt.czj.utils.ToolsUtils;
import com.tt.czj.widget.CircleImageView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/4/12/0012.
 */
public class MyInfoActivity extends BaseActivity {

    /**
     * The M touxiang img.
     */
    @BindView(R.id.touxiang_img)
    ImageView mTouxiangImg;
    /**
     * The M person center photo.
     */
    @BindView(R.id.info_person_center_photo)
    CircleImageView mPersonCenterPhoto;
    /**
     * The M login right now rl.
     */
    @BindView(R.id.login_right_now_rl)
    RelativeLayout mLoginRightNowRl;
    /**
     * The M touxiang tv.
     */
    @BindView(R.id.touxiang_tv)
    TextView mTouxiangTv;
    /**
     * The M photo rl.
     */
    @BindView(R.id.photo_rl)
    RelativeLayout mPhotoRl;
    /**
     * The M tv name.
     */
    @BindView(R.id.tv_name)
    TextView mTvName;
    /**
     * The M edit name.
     */
    @BindView(R.id.edit_name)
    EditText mEditName;
    /**
     * The M name.
     */
    @BindView(R.id.name)
    RelativeLayout mName;
    /**
     * The M tv xiaoqu.
     */
    @BindView(R.id.tv_xiaoqu)
    TextView mTvXiaoqu;
    /**
     * The M edit xiaoqu.
     */
    @BindView(R.id.edit_xiaoqu)
    Spinner mEditXiaoqu;
    /**
     * The M xiaoqu.
     */
    @BindView(R.id.xiaoqu)
    RelativeLayout mXiaoqu;
    /**
     * The M tv sex.
     */
    @BindView(R.id.tv_sex)
    TextView mTvSex;
    /**
     * The M edit sex.
     */
    @BindView(R.id.edit_sex)
    Spinner mEditSex;
    /**
     * The M sex.
     */
    @BindView(R.id.sex)
    RelativeLayout mSex;
    /**
     * The M tv age.
     */
    @BindView(R.id.tv_age)
    TextView mTvAge;
    /**
     * The M edit age.
     */
    @BindView(R.id.edit_age)
    EditText mEditAge;
    /**
     * The M age.
     */
    @BindView(R.id.age)
    RelativeLayout mAge;
    /**
     * The M tv phone.
     */
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    /**
     * The M edit phone.
     */
    @BindView(R.id.edit_phone)
    EditText mEditPhone;
    /**
     * The M phone.
     */
    @BindView(R.id.phone)
    RelativeLayout mPhone;
    /**
     * The Info bu.
     */
    @BindView(R.id.info_bu)
    Button info_bu;

    /**
     * The Xiaoqu array.
     */
    @BindArray(R.array.xiaoqu)
    String[] xiaoquArray;

    /**
     * The Sex array.
     */
    @BindArray(R.array.sex)
    String[] sexArray;

    private ChangePhotoUtil changePhotoUtil;

    @Override
    public int getContentViewId() {
        return R.layout.my_info_acticity;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
    }

    @Override
    public void initData() {
        User user = BmobUser.getCurrentUser(this, User.class);
        mEditAge.setText(String.valueOf(user.getAge()));
        mEditName.setText(user.getUsername());
        mEditPhone.setText(user.getMobilePhoneNumber());
        mEditSex.setSelection(getSpinnerIndex(sexArray,user.getSex()));
        mEditXiaoqu.setSelection(getSpinnerIndex(xiaoquArray,user.getXiaoqu()));
        //Log.e(TAG, "initData: photo="+user.getPhoto().getUrl());
        if (user.getPhoto() != null) {
            mPersonCenterPhoto.setDefaultImageResId(R.mipmap.icon_photo);
            mPersonCenterPhoto.setImageUrl(user.getPhoto().getUrl(), TakePhotoUtil.getmImageLoader(this));
        } else {
            mPersonCenterPhoto.setDefaultImageResId(R.mipmap.icon_photo);
        }
    }

    /**
     * On click.
     *
     * @param v the v
     */
    @OnClick({ R.id.info_person_center_photo, R.id.info_bu})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_person_center_photo:
                changePhotoUtil=new ChangePhotoUtil(this,this,mPersonCenterPhoto);
                changePhotoUtil.showTypeDialog();
                break;
            case R.id.info_bu:
                User new_user = new User();
                if (ToolsUtils.isNullOrEmpty(mEditAge.getText().toString()) || ToolsUtils.isNullOrEmpty(mEditName.getText().toString()) || ToolsUtils.isNullOrEmpty(mEditPhone.getText().toString()) || ToolsUtils.isNullOrEmpty(sexArray[mEditSex.getSelectedItemPosition()]) || ToolsUtils.isNullOrEmpty(xiaoquArray[mEditXiaoqu.getSelectedItemPosition()])) {
                    ToastUtil.showToast(this, "请将信息填写完整");
                }
                else if (!ToolsUtils.cheakAge(mEditAge.getText().toString())){
                    ToastUtil.showToast(this, "请输入正确的年龄");
                }
                else {
                    new_user.setAge(Integer.parseInt(mEditAge.getText().toString()));
                    new_user.setSex(sexArray[mEditSex.getSelectedItemPosition()]);
                    new_user.setXiaoqu(xiaoquArray[mEditXiaoqu.getSelectedItemPosition()]);
                    new_user.setMobilePhoneNumber(mEditPhone.getText().toString());
                    new_user.update(this, BmobUser.getCurrentUser(this, User.class).getObjectId(), new UpdateListener() {
                        @Override
                        public void onSuccess() {
                            ToastUtil.showToast(MyInfoActivity.this, "修改成功");
                            finish();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Log.e(TAG, "onFailure: "+s );
                            ToastUtil.showToast(MyInfoActivity.this, "修改失败");
                        }
                    });
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        changePhotoUtil.onActivityResult(requestCode, resultCode, data);
    }

    private int getSpinnerIndex(String[] Array, String str){
        List<String> list= Arrays.asList(Array);
        return list.indexOf(str);
    }
}
