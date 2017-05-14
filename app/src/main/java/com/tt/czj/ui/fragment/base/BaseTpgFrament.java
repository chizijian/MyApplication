package com.tt.czj.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.tt.czj.R;
import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.models.User;
import com.tt.czj.ui.activity.GoodsDetailActivity;
import com.tt.czj.ui.adapter.ExpandableListViewAdapter;
import com.tt.czj.utils.UIUtils;
import com.tt.czj.widget.CustomExpandableListView;
import com.yhy.tpg.handler.ResultHandler;
import com.yhy.tpg.pager.TpgFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by czj on 2017/5/09/0009.
 */

public abstract class BaseTpgFrament extends TpgFragment {

    private Unbinder unbinder;

    /**
     * The M near by goods list.
     */
    @BindView(R.id.goods_list)
    protected CustomExpandableListView mGoodsList;
    /**
     * The M goods empty textview.
     */
    @BindView(R.id.goods_empty_textview)
    protected TextView mGoodsEmptyTextview;
    /**
     * The M scroll view.
     */
    @BindView(R.id.scrollView)
    protected ScrollView mScrollView;

    @Override
    protected View getSuccessView() {
        return mContentView;
    }

    private Context mContext;//上下文
    private View mContentView;//布局
    private boolean isFirstCreated = true;

    private ResultHandler mHandler;
    private final ThreadLocal<List<Goods>> goodese = new ThreadLocal<>();
    private final ThreadLocal<List<User>> userses = new ThreadLocal<>();
    private final ThreadLocal<ExpandableListViewAdapter> adapter = new ThreadLocal<>();

    /**
     * Is qiu gou boolean.
     *
     * @return the boolean
     */
    public abstract boolean IsQiuGou();//传递货物状态

    public abstract void HiddenChange();//HIddenchanged时调用

    public abstract void initView();//初始化

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

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
        userses.set(new ArrayList<User>());
        goodese.set(new ArrayList<Goods>());
        adapter.set(new ExpandableListViewAdapter(mContext, goodese.get(), userses.get()));
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

    private void initUI() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.get().sendEmptyMessage(0);
            }
        }).start();
    }

    private final ThreadLocal<Handler> handler = new ThreadLocal<Handler>() {
        @Override
        protected Handler initialValue() {
            return new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message msg) {
                    adapter.set(null);
                    initHomeList(goodese.get(), userses.get());
                }
            };
        }
    };

    private class MyGoodsListGroupListener implements ExpandableListView.OnGroupClickListener {
        private List<Goods> goodses = new ArrayList<>();
        private List<User> userses = new ArrayList<>();

        /**
         * Instantiates a new My goods list group listener.
         *
         * @param goodses the goodses
         * @param userses the userses
         */
        MyGoodsListGroupListener(List<Goods> goodses, List<User> userses) {
            this.goodses = goodses;
            this.userses = userses;
        }

        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
            Toast.makeText(mContext, "" + goodses.get(groupPosition).getPrice(), Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            bundle.putSerializable("Goods", goodses.get(groupPosition));
            bundle.putSerializable("User", userses.get(groupPosition));
            UIUtils.nextPage(mContext, GoodsDetailActivity.class, bundle);
            return true;
        }
    }

    private class MyGoodsListChildListener implements ExpandableListView.OnChildClickListener {
        private List<Goods> goodses = new ArrayList<>();
        private List<User> userses = new ArrayList<>();

        /**
         * Instantiates a new My goods list child listener.
         *
         * @param goodses the goodses
         * @param userses the userses
         */
        MyGoodsListChildListener(List<Goods> goodses, List<User> userses) {
            this.goodses = goodses;
            this.userses = userses;
        }

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            Toast.makeText(mContext, "" + goodses.get(groupPosition).getPrice(), Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            bundle.putSerializable("Goods", goodses.get(groupPosition));
            bundle.putSerializable("User", userses.get(groupPosition));
            UIUtils.nextPage(mContext, GoodsDetailActivity.class, bundle);
            return true;
        }
    }

    private void initHomeList(List<Goods> goodses, List<User> userses) {
        mScrollView.setVisibility(View.VISIBLE);
        mGoodsEmptyTextview.setVisibility(View.GONE);
        adapter.set(new ExpandableListViewAdapter(mContext, goodses, userses));
        mGoodsList.setAdapter(adapter.get());
        adapter.get().notifyDataSetChanged();
        for (int i = 0; i < adapter.get().getGroupCount(); i++) {
            mGoodsList.collapseGroup(i);
            mGoodsList.expandGroup(i);
        }
        mGoodsList.setOnGroupClickListener(new MyGoodsListGroupListener(goodses, userses));
        mGoodsList.setOnChildClickListener(new MyGoodsListChildListener(goodses, userses));
    }

    public void parseUser(List<User> users, List<Goods> goodses) {
        if ((goodses.isEmpty() || users.isEmpty())) {//没有数据
            mScrollView.setVisibility(View.GONE);
            mGoodsEmptyTextview.setVisibility(View.VISIBLE);
        } else {
            this.userses.get().clear();
            this.goodese.get().clear();
            this.userses.get().addAll(users);
            this.goodese.get().addAll(goodses);
            initUI();//更新UI
        }
    }
}
