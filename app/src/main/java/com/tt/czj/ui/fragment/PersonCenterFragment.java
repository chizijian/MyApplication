package com.tt.czj.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.tt.czj.R;
import com.tt.czj.app.MyApp;
import com.tt.czj.di.component.AppComponent;
import com.tt.czj.di.component.DaggerMainActivityComponent;
import com.tt.czj.di.modules.MainActivityModule;
import com.tt.czj.mvp.models.User;
import com.tt.czj.mvp.presenter.PersonCenterFragmentPresenter;
import com.tt.czj.mvp.views.PersonCenterView;
import com.tt.czj.ui.activity.LoginActivity;
import com.tt.czj.ui.activity.MainActivity;
import com.tt.czj.ui.activity.own.MyFavorActivity;
import com.tt.czj.ui.activity.own.MyInfoActivity;
import com.tt.czj.ui.activity.own.MyOrderActivity;
import com.tt.czj.ui.activity.own.MyPublishActivity;
import com.tt.czj.ui.activity.own.MyQiuGouActivity;
import com.tt.czj.ui.activity.own.MysellActivity;
import com.tt.czj.utils.ChangePhotoUtil;
import com.tt.czj.utils.TakePhotoUtil;
import com.tt.czj.utils.UIUtils;
import com.tt.czj.widget.CircleImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobUser;

/**
 * Created by czj on 2016/4/6 0006.
 */
@SuppressLint("HandlerLeak")
public class PersonCenterFragment extends Fragment implements PersonCenterView,OnClickListener {

    /**
     * The Unbinder.
     */
    Unbinder unbinder;

    /**
     * The constant TAG.
     */
    public static final String TAG = "PersonCenterFragment";
    /**
     * The M person center photo.
     */
    @BindView(R.id.person_center_photo)
    CircleImageView mPersonCenterPhoto;
    /**
     * The M login right now textview.
     */
    @BindView(R.id.login_right_now_textview)
    TextView mLoginRightNowTextview;
    /**
     * The M login right now rl.
     */
    @BindView(R.id.login_right_now_rl)
    RelativeLayout mLoginRightNowRl;
    /**
     * The M user name.
     */
    @BindView(R.id.user_name)
    TextView mUserName;
    /**
     * The M user specified.
     */
    @BindView(R.id.user_specified)
    TextView mUserSpecified;
    /**
     * The Photo rl.
     */
    @BindView(R.id.photo_rl)
    RelativeLayout photoRl;
    /**
     * The M my post rl.
     */
    @BindView(R.id.my_post_rl)
    RelativeLayout mMyPostRl;
    /**
     * The M introduce to friend rl.
     */
    @BindView(R.id.introduce_to_friend_rl)
    RelativeLayout mIntroduceToFriendRl;
    /**
     * The Msellrl.
     */
    @BindView(R.id.my_sell_rl)
    RelativeLayout msellrl;
    /**
     * The M buyrl.
     */
    @BindView(R.id.my_buy_rl)
    RelativeLayout mBuyrl;
    /**
     * The M favorrl.
     */
    @BindView(R.id.my_favor_rl)
    RelativeLayout mFavorrl;
    /**
     * The Login out.
     */
    @BindView(R.id.login_out)
    TextView login_out;

    /**
     * The M setting rl.
     */
    @BindView(R.id.setting_rl)
    RelativeLayout mSettingRl;
    /**
     * The M logined rl.
     */
    @BindView(R.id.logined_rl)
    RelativeLayout mLoginedRl;
    private Context mContext;
    private View mRootView = null;
    /**
     * The Person center fragment presenter.
     */
    @Inject
    PersonCenterFragmentPresenter personCenterFragmentPresenter;

    private ChangePhotoUtil changePhotoUtil;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.person_center_fragment, container, false);
        }
        unbinder = ButterKnife.bind(this, mRootView);
        setupComponent(((MyApp) mContext.getApplicationContext()).getAppComponent());
        initData();
        return mRootView;
    }

    /**
     * Sets component.
     *
     * @param appComponent the app component
     */
    protected void setupComponent(AppComponent appComponent) {
        DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }

    /**
     * Init data.
     */
    public void initData() {
        User user = BmobUser.getCurrentUser(mContext, User.class);
        if (user != null) {
            //已登录
            mLoginRightNowRl.setVisibility(View.GONE);
            mLoginedRl.setVisibility(View.VISIBLE);
            if (user.getPhoto() != null) {
                mPersonCenterPhoto.setImageUrl(user.getPhoto().getUrl(), TakePhotoUtil.getmImageLoader(this.getContext()));
            } else {
                mPersonCenterPhoto.setImageResource(R.mipmap.icon_photo);
            }
            mUserName.setText(user.getUsername());
            if (user.getSignature() == null || user.getSignature().equals("")) {
                mUserSpecified.setText("您的签名空空如也，立即设置？");
            } else {
                mUserSpecified.setText(user.getSignature());
            }
        } else {
            mPersonCenterPhoto.setDefaultImageResId(R.mipmap.icon_photo);
            mLoginedRl.setVisibility(View.GONE);
            mLoginRightNowRl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.person_center_photo, R.id.login_right_now_textview, R.id.login_right_now_rl, R.id.my_post_rl, R.id.setting_rl, R.id.logined_rl, R.id.my_sell_rl, R.id.my_qiugou_rl, R.id.my_favor_rl, R.id.my_buy_rl, R.id.login_out})
    public void onClick(View view) {
        User user = BmobUser.getCurrentUser(mContext, User.class);
        switch (view.getId()) {
            case R.id.person_center_photo:
                changePhotoUtil = new ChangePhotoUtil(mContext,this, mPersonCenterPhoto);
                changePhotoUtil.showTypeDialog();
                break;
            case R.id.my_qiugou_rl:
                //UIUtils.nextPage(mContext, .class);
                if (user == null) {
                    UIUtils.nextPage(mContext, LoginActivity.class);
                    ((MainActivity) mContext).finish();
                } else {
                    UIUtils.nextPage(mContext, MyQiuGouActivity.class);
                }
                break;
            case R.id.login_right_now_textview:
                UIUtils.nextPage(mContext, LoginActivity.class);
                ((MainActivity) mContext).finish();
                break;
            case R.id.my_post_rl:
                if (user == null) {
                    UIUtils.nextPage(mContext, LoginActivity.class);
                    ((MainActivity) mContext).finish();
                } else {
                    UIUtils.nextPage(mContext, MyPublishActivity.class);
                }
                break;
            case R.id.my_sell_rl:
                if (user == null) {
                    UIUtils.nextPage(mContext, LoginActivity.class);
                    ((MainActivity) mContext).finish();
                } else {
                    UIUtils.nextPage(mContext, MysellActivity.class);
                }
                break;
            case R.id.my_favor_rl:
                if (user == null) {
                    UIUtils.nextPage(mContext, LoginActivity.class);
                    ((MainActivity) mContext).finish();
                } else {
                    UIUtils.nextPage(mContext, MyFavorActivity.class);
                }
                break;
            case R.id.introduce_to_friend_rl:
                if (user == null) {
                    UIUtils.nextPage(mContext, LoginActivity.class);
                    ((MainActivity) mContext).finish();
                } else {
                    processIntroductionTofriend();
                }
                break;
            case R.id.setting_rl:
                if (user == null) {
                    UIUtils.nextPage(mContext, LoginActivity.class);
                    ((MainActivity) mContext).finish();
                } else
                    UIUtils.nextPage(mContext, MyInfoActivity.class);
                break;
            case R.id.my_buy_rl:
                if (user == null) {
                    UIUtils.nextPage(mContext, LoginActivity.class);
                    ((MainActivity) mContext).finish();
                } else
                    UIUtils.nextPage(mContext, MyOrderActivity.class);
                break;
            case R.id.login_out:
                BmobUser.logOut(mContext);
                UIUtils.nextPage(mContext, MainActivity.class);
                break;
        }
    }

    /**
     * Process introduction tofriend.
     */
    public void processIntroductionTofriend() {

    }

    @Override
    public void showPhoto(GlideDrawable drawable) {
        mPersonCenterPhoto.setImageDrawable(drawable);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        changePhotoUtil.onActivityResult(requestCode, resultCode, data);
        User user=BmobUser.getCurrentUser(mContext,User.class);
        mPersonCenterPhoto.setImageUrl(user.getPhoto().getUrl(), TakePhotoUtil.getmImageLoader(this.getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}


