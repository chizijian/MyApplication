package com.tt.tradein.ui.fragment.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.tt.tradein.R;
import com.tt.tradein.di.component.AppComponent;
import com.tt.tradein.di.component.DaggerMainActivityComponent;
import com.tt.tradein.di.modules.MainActivityModule;
import com.tt.tradein.mvp.models.Goods;
import com.tt.tradein.mvp.models.User;
import com.tt.tradein.mvp.presenter.HomeViewPresenter;
import com.tt.tradein.mvp.views.HomeView;
import com.tt.tradein.ui.activity.GoodsDetailActivity;
import com.tt.tradein.ui.adapter.ExpandableListViewAdapter;
import com.tt.tradein.widget.CustomExpandableListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by czj on 2017/5/09/0009.
 */

public abstract class BaseHomeFragment extends BaseTpgFrament implements HomeView {
    @BindView(R.id.goods_list)
    CustomExpandableListView GoodsList;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.goods_empty_textview)
    TextView mGoodsEmptyTextview;

    @Inject
    HomeViewPresenter presenter;

    private final String TAG="BaseHomeFragment";
    @Override
    public void errorLoad(Throwable t) {

    }

    @Override
    public void showBannerData(List<ImageView> images) {

    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_goods_list;
    }

    @Override
    public void reloadDate(Object... args) {
        super.reloadDate(args);
        presenter.loadGoodsInfor(mContext,IsQiuGou());
    }

    @Override
    public void initView() {
        presenter.loadGoodsInfor(mContext, IsQiuGou());
    }

    @Override
    public void HiddenChange() {
        presenter.loadGoodsInfor(mContext, IsQiuGou());
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void parseUser(List<User> users, List<Goods> goodses) {
        this.goodese.get().clear();
        this.userses.get().clear();
        this.goodese.get().addAll(goodses);
        this.userses.get().addAll(users);
        initUI();
    }

    @Override
    public void onLoadGoodsError(String str) {

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
        if (goodses.isEmpty() || userses.isEmpty()) {
            mScrollView.setVisibility(View.GONE);
            mGoodsEmptyTextview.setVisibility(View.VISIBLE);
        } else {
            mScrollView.setVisibility(View.VISIBLE);
            mGoodsEmptyTextview.setVisibility(View.GONE);

            Log.e(TAG, "initHomeList: users.size="+userses.size() );
            adapter.set(new ExpandableListViewAdapter(mContext, goodses, userses));
            GoodsList.setAdapter(adapter.get());
            adapter.get().notifyDataSetChanged();
            for (int i = 0; i < adapter.get().getGroupCount(); i++) {
                GoodsList.collapseGroup(i);
                GoodsList.expandGroup(i);
            }
            GoodsList.setOnGroupClickListener(new MyGoodsListGroupListener(goodses, userses));
            GoodsList.setOnChildClickListener(new MyGoodsListChildListener(goodses, userses));
        }
    }
}
