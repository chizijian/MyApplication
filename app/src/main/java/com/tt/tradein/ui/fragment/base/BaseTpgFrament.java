package com.tt.tradein.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tt.tradein.R;
import com.yhy.tpg.pager.TpgFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by czj on 2017/5/09/0009.
 */

public abstract class BaseTpgFrament extends TpgFragment {

    private Unbinder unbinder;

    @Override
    protected View getSuccessView() {
        return mContentView;
    }

    private View mContentView;//布局
    private boolean isFirstCreated = true;

    /**
     * Is qiu gou boolean.
     *
     * @return the boolean
     */
    public abstract boolean IsQiuGou();//传递货物状态

    public abstract void HiddenChange();//HIddenchanged时调用

    public abstract void initView();//初始化

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mContentView == null) {
            isFirstCreated = false;
            mContentView = View.inflate(getActivity(), R.layout.goods_list_fragment, null);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, mContentView);
        initView();
        return mContentView;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden && !isFirstCreated) {
            HiddenChange();
        }
        super.onHiddenChanged(hidden);
    }

}
