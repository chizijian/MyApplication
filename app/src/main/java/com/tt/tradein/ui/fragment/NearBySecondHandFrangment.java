package com.tt.tradein.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.tt.tradein.mvp.models.Goods;
import com.tt.tradein.mvp.models.User;
import com.tt.tradein.mvp.presenter.NearByPresenter;
import com.tt.tradein.ui.adapter.ExpandableListViewAdapter;
import com.tt.tradein.ui.fragment.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/03/0003.
 */

public class NearBySecondHandFrangment extends BaseFragment{

    private final String TAG = "NearBySecondFrangment";

    private static final String NEARBY_SECOND_TYPE = "NEARBY_SECOND_TYPE";

    private String mCurrentXiaoqu;

    private View view;
    private Unbinder unbinder;

    private Context mContext;

    private final ThreadLocal<List<Goods>> second_goodses = new ThreadLocal<>();
    private final ThreadLocal<List<User>> second_userses = new ThreadLocal<>();

    private final ThreadLocal<ExpandableListViewAdapter> adapter = new ThreadLocal<>();

    private boolean isFirstCreated = true;
    @Inject
    NearByPresenter presenter;

    private static final String BASE_FRAGMENT_TYPE="BASE_FRAGMENT_TYPE";

    @Override
    public boolean shouldLoadDataAtFirst() {
    return super.shouldLoadDataAtFirst();
    }

    public static NearBySecondHandFrangment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(BASE_FRAGMENT_TYPE, type);
        NearBySecondHandFrangment fragment = new NearBySecondHandFrangment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public boolean IsQiuGou() {
        return false;
    }

    @Override
    public String getXiqoqu() {
        return getArguments().getString(BASE_FRAGMENT_TYPE);
    }

}
