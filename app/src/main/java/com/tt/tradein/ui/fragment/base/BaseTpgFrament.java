package com.tt.tradein.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tt.tradein.app.MyApp;
import com.tt.tradein.di.component.AppComponent;
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
    private boolean isFirstCreated=true;
    private Context mContext;
    /**
     * Is qiu gou boolean.
     *
     * @return the boolean
     */
    public abstract boolean IsQiuGou();//传递货物状态
    public abstract int getContentViewId();//获取布局文件
    public abstract void HiddenChange();//HIddenchanged时调用
    public abstract void initView();//初始化
    protected abstract void setupComponent(AppComponent appComponent);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext=context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mContentView==null){
            isFirstCreated=false;
            mContentView=View.inflate(getActivity(),getContentViewId(),null);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unbinder= ButterKnife.bind(this,mContentView);
        setupComponent(((MyApp) mContext.getApplicationContext()).getAppComponent());
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
