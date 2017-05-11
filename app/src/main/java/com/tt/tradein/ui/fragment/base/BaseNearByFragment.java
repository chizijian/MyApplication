package com.tt.tradein.ui.fragment.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.tt.tradein.R;
import com.tt.tradein.app.MyApp;
import com.tt.tradein.di.component.AppComponent;
import com.tt.tradein.di.component.DaggerMainActivityComponent;
import com.tt.tradein.di.modules.MainActivityModule;
import com.tt.tradein.mvp.models.Goods;
import com.tt.tradein.mvp.models.User;
import com.tt.tradein.mvp.presenter.NearByPresenter;
import com.tt.tradein.mvp.views.NearByView;
import com.tt.tradein.ui.activity.GoodsDetailActivity;
import com.tt.tradein.ui.adapter.ExpandableListViewAdapter;
import com.tt.tradein.widget.CustomExpandableListView;
import com.yhy.tpg.handler.ResultHandler;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/5/03/0003.
 */
public abstract class BaseNearByFragment extends BaseTpgFrament implements NearByView {

    private final String TAG = "BaseNearByFragment";

    /**
     * The M near by goods list.
     */
    @BindView(R.id.goods_list)
    CustomExpandableListView mNearByGoodsList;
    /**
     * The M goods empty textview.
     */
    @BindView(R.id.goods_empty_textview)
    TextView mGoodsEmptyTextview;
    /**
     * The M scroll view.
     */
    @BindView(R.id.scrollView)
    ScrollView mScrollView;

    /**
     * The Presenter.
     */
    @Inject
    NearByPresenter presenter;

    private Context mContext;//上下文
    private ResultHandler mHandler;
    private final ThreadLocal<List<Goods>> goodese = new ThreadLocal<>();
    private final ThreadLocal<List<User>> userses = new ThreadLocal<>();
    private final ThreadLocal<ExpandableListViewAdapter> adapter = new ThreadLocal<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    /**
     * Gets xiqoqu.
     *
     * @return the xiqoqu
     */
    public abstract String getXiqoqu();

    @Override
    public void initView() {
        setupComponent(((MyApp) mContext.getApplicationContext()).getAppComponent());
        userses.set(new ArrayList<User>());
        goodese.set(new ArrayList<Goods>());
        adapter.set(new ExpandableListViewAdapter(mContext, goodese.get(), userses.get()));
/*        if(presenter!=null)
            presenter.loadGoodsInfor(mContext, getXiqoqu(), IsQiuGou());*/
    }

    @Override
    public void HiddenChange() {
        /*presenter.loadGoodsInfor(mContext, getXiqoqu(), IsQiuGou());*/
    }

    @Override
    public void reloadDate(Object... args) {
        super.reloadDate(args);
        if (presenter != null) {
            presenter.loadGoodsInfor(mContext, String.valueOf(args[0]), IsQiuGou());
        }
    }

    @Override
    protected void initData(ResultHandler handler) {
        mHandler = handler;
        if (presenter != null)
            presenter.loadGoodsInfor(mContext, getXiqoqu(), IsQiuGou());
    }


    @Override
    public void onLoadGoodsInforSuccess(List<Goods> goods) {
        mHandler.sendSuccessHandler();
    }

    @Override
    public void onLoadGoodsError(String str) {
        if (mHandler != null)
            mHandler.sendErrorHandler();
        String TAG = "BaseFrament";
        Log.e(TAG, "onLoadGoodsError: " + str);
    }

    @Override
    public void parseUser(List<User> users, List<Goods> goodses) {
        if ((goodses.isEmpty() || users.isEmpty())) {//没有数据
            mScrollView.setVisibility(View.GONE);
            mGoodsEmptyTextview.setVisibility(View.VISIBLE);
            ;
        } else {
            this.userses.get().clear();
            this.goodese.get().clear();
            this.userses.get().addAll(users);
            this.goodese.get().addAll(goodses);
            initUI();//更新UI
        }
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
            Intent intent = new Intent(mContext, GoodsDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Goods", goodses.get(groupPosition));
            bundle.putSerializable("User", userses.get(groupPosition));
            intent.putExtras(bundle);
            startActivity(intent);
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
            Intent intent = new Intent(mContext, GoodsDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Goods", goodses.get(groupPosition));
            bundle.putSerializable("User", userses.get(groupPosition));
            intent.putExtras(bundle);
            startActivity(intent);

            return true;
        }
    }

    private void initHomeList(List<Goods> goodses, List<User> userses) {

        mScrollView.setVisibility(View.VISIBLE);
        mGoodsEmptyTextview.setVisibility(View.GONE);

        Log.e(TAG, "initHomeList: userses.size=" + userses.size());
        Log.e(TAG, "initHomeList: goodses.size=" + goodses.size());


        adapter.set(new ExpandableListViewAdapter(mContext, goodses, userses));
        mNearByGoodsList.setAdapter(adapter.get());
        adapter.get().notifyDataSetChanged();
        for (int i = 0; i < adapter.get().getGroupCount(); i++) {
            mNearByGoodsList.collapseGroup(i);
            mNearByGoodsList.expandGroup(i);
        }
        mNearByGoodsList.setOnGroupClickListener(new MyGoodsListGroupListener(goodses, userses));
        mNearByGoodsList.setOnChildClickListener(new MyGoodsListChildListener(goodses, userses));
    }
}
