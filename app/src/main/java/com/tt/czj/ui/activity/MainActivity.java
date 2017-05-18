package com.tt.czj.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.tt.czj.R;
import com.tt.czj.R2;
import com.tt.czj.mvp.models.User;
import com.tt.czj.ui.activity.base.BaseActivity;
import com.tt.czj.ui.fragment.home_fragment.HomeFragment;
import com.tt.czj.ui.fragment.KindSortFragment;
import com.tt.czj.ui.fragment.nearby_fragment.NearByFragment;
import com.tt.czj.ui.fragment.PersonCenterFragment;
import com.tt.czj.utils.GlobalDefineValues;
import com.tt.czj.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

/**
 * The type Main activity.
 */
public class MainActivity extends BaseActivity {
    /**
     * The M publish menu.
     */
    @BindView(R2.id.menu_add)
    TextView mPublishMenu;
    /**
     * The M home text view.
     */
    @BindView(R2.id.home)
    TextView mHomeTextView;
    /**
     * The M near by text view.
     */
    @BindView(R2.id.near_by)
    TextView mNearByTextView;
    /**
     * The M kind sort text view.
     */
    @BindView(R2.id.kind_sort)
    TextView mKindSortTextView;
    /**
     * The M person center text view.
     */
    @BindView(R2.id.person_center)
    TextView mPersonCenterTextView;
    /**
     * The M views.
     */
    @Nullable
    @BindViews({R2.id.home, R2.id.near_by, R2.id.kind_sort, R2.id.person_center})
    List<TextView> mViews;

    private final int HOME_VIEW = 0;
    private final int NEAR_BY_VIEW = 1;
    private final int KIND_SORT_VIEW = 2;
    private final int PERSON_VIEW = 3;


    private int mCurrentIndex = 0;
    private int mOldIndex = 0;

    private final boolean SECOND_HAND = false;
    private final boolean QIUGOU = true;

    private List<Fragment> mFragments;

    private FragmentTransaction ft;

    HomeFragment mHomeFragment;//首页
    NearByFragment mNearByFragment;//附近
    KindSortFragment mKindSortFragment;//分类
    PersonCenterFragment mPersonCenterFragment;//我的


    @Override
    public void initView(Bundle savedInstanceState) {
        initFragments();
    }

    @Override
    public void initData() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            String mWorkMode = getIntent().getExtras().getString(GlobalDefineValues.MainActivityWorkMode);
            if (mWorkMode != null && mWorkMode.equals(GlobalDefineValues.PersonCenterWorkMode)) {
                showCurrentFragment(3);
            } else {
                mViews.get(HOME_VIEW).setSelected(true);
            }
        } else {
            mViews.get(HOME_VIEW).setSelected(true);
        }

    }

    /*选择是二手还是求购*/
    private void showTypeDialog() {

        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        View view = View.inflate(this, R.layout.dialog_select_publish_type, null);

        TextView tv_select_seconghand = ButterKnife.findById(view, R.id.tv_select_secondhand);
        TextView tv_select_qiugou = ButterKnife.findById(view, R.id.tv_select_qiugou);
        TextView tv_select_cancle = ButterKnife.findById(view, R.id.tv_cancle_publish);

        tv_select_seconghand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processPublish(SECOND_HAND);
                dialog.dismiss();
            }
        });

        tv_select_qiugou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processPublish(QIUGOU);
                dialog.dismiss();
            }
        });

        tv_select_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }


    /**
     * 初始化用到的Fragment
     */
    private void initFragments() {
        mHomeFragment = new HomeFragment();
        mNearByFragment = new NearByFragment();
        mKindSortFragment = new KindSortFragment();
        mPersonCenterFragment = new PersonCenterFragment();

        if (mFragments == null) {
            mFragments = new ArrayList<>();
            mFragments.add(mHomeFragment);
            mFragments.add(mNearByFragment);
            mFragments.add(mKindSortFragment);
            mFragments.add(mPersonCenterFragment);
        }
        else {
            mFragments.set(HOME_VIEW, mHomeFragment);
            mFragments.set(NEAR_BY_VIEW, mNearByFragment);
            mFragments.set(KIND_SORT_VIEW, mKindSortFragment);
            mFragments.set(PERSON_VIEW, mPersonCenterFragment);
        }
        //默认加载前两个Fragment，其中第一个展示，第二个隐藏
        getSupportFragmentManager().beginTransaction().add(R.id.content, mHomeFragment).add(R.id.content, mNearByFragment).hide(mNearByFragment).show(mHomeFragment).commit();
    }

    /**
     * 展示当前选中的Fragment
     *
     * @param currentIndex
     */
    private void showCurrentFragment(int currentIndex) {
        if (currentIndex != mOldIndex) {
            ft = getSupportFragmentManager().beginTransaction();
            mPublishMenu.setSelected(false);
            mViews.get(mOldIndex).setSelected(false);
            mViews.get(currentIndex).setSelected(true);
            ft.hide(mFragments.get(mOldIndex));
            if (!mFragments.get(currentIndex).isAdded()) {
                ft.add(R.id.content, mFragments.get(currentIndex)).show(mFragments.get(currentIndex)).commit();
            } else {
                ft.show(mFragments.get(currentIndex)).commit();
            }
            mOldIndex = currentIndex;
        }
    }


    /**
     * Process publish.
     *
     * @param type the type
     */
    public void processPublish(boolean type) {
        User user = BmobUser.getCurrentUser(this, User.class);
        if (user == null) {
            UIUtils.nextPage(this, LoginActivity.class);
        } else {
            mCurrentIndex = 4;
            mViews.get(mOldIndex).setSelected(false);
            mPublishMenu.setSelected(true);
            mOldIndex = HOME_VIEW;
            Bundle bundle = new Bundle();
            bundle.putBoolean("type", type);
            UIUtils.nextPage(MainActivity.this, PublishGoodsActivity.class, bundle);
        }
    }

    @Override
    protected void onResume() {
        if (mCurrentIndex == 4) {
            ft = getSupportFragmentManager().beginTransaction();
            mPublishMenu.setSelected(false);
            mViews.get(HOME_VIEW).setSelected(true);
            mCurrentIndex = HOME_VIEW;
            if (!mFragments.get(HOME_VIEW).isAdded()) {
                ft.add(R.id.content, mFragments.get(HOME_VIEW)).hide(mFragments.get(mOldIndex)).show(mFragments.get(PERSON_VIEW)).commit();
            } else {
                ft.hide(mFragments.get(mOldIndex)).show(mFragments.get(HOME_VIEW)).commit();
            }
            mOldIndex = mCurrentIndex;
        }
        super.onResume();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showExitAppDialog(this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showExitAppDialog(final Activity activity) {
        if (activity == null) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(getResources().getString(R.string.str_notice));
        builder.setMessage(getResources().getString(R.string.exit_notice_msg));

        builder.setPositiveButton(getResources().getString(R.string.str_yes), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(getResources().getString(R.string.str_no), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    /**
     * On click.
     *
     * @param v the v
     */
    @OnClick({R2.id.content, R2.id.menu_add, R2.id.home, R2.id.near_by, R2.id.kind_sort, R2.id.person_center})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home:
                mCurrentIndex = HOME_VIEW;
                break;
            case R.id.near_by:
                mCurrentIndex = NEAR_BY_VIEW;
                break;
            case R.id.kind_sort:
                mCurrentIndex = KIND_SORT_VIEW;
                break;
            case R.id.person_center:
                mCurrentIndex = PERSON_VIEW;
                break;
            case R.id.menu_add: {
                showTypeDialog();
                //processPublish();
                break;
            }
        }
        if (mCurrentIndex != 4) {
            showCurrentFragment(mCurrentIndex);
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    /*防止fragment重叠*/
    @Override
    public void onAttachFragment(Fragment fragment) {
        if (mHomeFragment == null && fragment instanceof HomeFragment)
            mHomeFragment = (HomeFragment) fragment;
        else if (mNearByFragment == null && fragment instanceof NearByFragment)
            mNearByFragment = (NearByFragment) fragment;
        else if (mKindSortFragment == null && fragment instanceof KindSortFragment)
            mKindSortFragment = (KindSortFragment) fragment;
        else if (mPersonCenterFragment == null && fragment instanceof PersonCenterFragment)
            mPersonCenterFragment = (PersonCenterFragment) fragment;

        if (mFragments == null) {
            mFragments = new ArrayList<>();
            mFragments.add(mHomeFragment);
            mFragments.add(mNearByFragment);
            mFragments.add(mKindSortFragment);
            mFragments.add(mPersonCenterFragment);
        }
        else {
            mFragments.set(HOME_VIEW, mHomeFragment);
            mFragments.set(NEAR_BY_VIEW, mNearByFragment);
            mFragments.set(KIND_SORT_VIEW, mKindSortFragment);
            mFragments.set(PERSON_VIEW, mPersonCenterFragment);
        }
    }

    /*防止fragment重叠*/
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }
}
