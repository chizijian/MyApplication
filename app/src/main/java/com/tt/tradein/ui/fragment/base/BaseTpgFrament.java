package com.tt.tradein.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tt.tradein.app.MyApp;
import com.tt.tradein.di.component.AppComponent;
import com.tt.tradein.mvp.models.Goods;
import com.tt.tradein.mvp.models.User;
import com.tt.tradein.ui.adapter.ExpandableListViewAdapter;
import com.yhy.tpg.handler.ResultHandler;
import com.yhy.tpg.pager.TpgFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by czj on 2017/5/09/0009.
 */

public abstract class BaseTpgFrament extends TpgFragment {

    private Unbinder unbinder;

    private View mContentView;//布局
    private boolean isFirstCreated=true;

    protected Context mContext;//上下文
    protected  ResultHandler mHandler;
    protected final ThreadLocal<List<Goods>> goodese = new ThreadLocal<>();
    protected final ThreadLocal<List<User>> userses = new ThreadLocal<>();
    protected final ThreadLocal<ExpandableListViewAdapter> adapter = new ThreadLocal<>();
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
            mContentView=View.inflate(mContext,getContentViewId(),null);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unbinder= ButterKnife.bind(this,mContentView);
        setupComponent(((MyApp) mContext.getApplicationContext()).getAppComponent());
        goodese.set(new ArrayList<Goods>());
        userses.set(new ArrayList<User>());
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
    protected View getSuccessView() {
        return null;
    }

    @Override
    protected void initData(ResultHandler handler) {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden && !isFirstCreated) {
            HiddenChange();
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public boolean shouldLoadDataAtFirst() {
        return true;
    }
}
