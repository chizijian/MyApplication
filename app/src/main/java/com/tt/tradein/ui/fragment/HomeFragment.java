package com.tt.tradein.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.tt.tradein.R;
import com.tt.tradein.app.MyApp;
import com.tt.tradein.di.component.AppComponent;
import com.tt.tradein.di.component.DaggerMainActivityComponent;
import com.tt.tradein.di.modules.MainActivityModule;
import com.tt.tradein.domain.service.WeatherApiService;
import com.tt.tradein.mvp.models.Goods;
import com.tt.tradein.mvp.models.QiugouGoods;
import com.tt.tradein.mvp.models.User;
import com.tt.tradein.mvp.models.WeatherResultBean;
import com.tt.tradein.mvp.presenter.HomeViewPresenter;
import com.tt.tradein.mvp.views.HomeView;
import com.tt.tradein.ui.activity.GoodsDetailActivity;
import com.tt.tradein.ui.activity.KindActivity;
import com.tt.tradein.ui.activity.SearchActivity;
import com.tt.tradein.ui.adapter.ExpandableListViewAdapter;
import com.tt.tradein.ui.adapter.HomeHorizentalListViewAdapter;
import com.tt.tradein.utils.GlobalDefineValues;
import com.tt.tradein.utils.UIUtils;
import com.tt.tradein.widget.CustomExpandableListView;
import com.tt.tradein.widget.ImageFlipper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by czj
 */
public class HomeFragment extends Fragment implements HomeView {
    /**
     * The constant TAG.
     */
    public static final String TAG = "HomeFragment";
    /**
     * The M image fliper.
     */
    @BindView(R.id.imageFliper)
    ImageFlipper mImageFliper;
    /**
     * The M dot one.
     */
    @BindView(R.id.dot_one)
    View mDotOne;
    /**
     * The M dot two.
     */
    @BindView(R.id.dot_two)
    View mDotTwo;
    /**
     * The M dot three.
     */
    @BindView(R.id.dot_three)
    View mDotThree;
    /**
     * The M dot four.
     */
    @BindView(R.id.dot_four)
    View mDotFour;
    /**
     * The M icon home item.
     */
//@BindView(R.id.home_horizontal_list_view)
    //HorizontalListView mHomeHorizontalListView;
    @BindView(R.id.icon_home_item)
    ImageView mIconHomeItem;
    /**
     * The M icon search imageview.
     */
    @BindView(R.id.icon_search_imageview)
    ImageView mIconSearchImageview;
    /**
     * The M home goods list.
     */
    @BindView(R.id.home_goods_list)
    CustomExpandableListView mHomeGoodsList;
    /**
     * The Dots ll.
     */
    @BindView(R.id.dots_ll)
    LinearLayout dotsLl;
    /**
     * The M home scroll view.
     */
    @BindView(R.id.home_scroll_view)
    ScrollView mHomeScrollView;
    /**
     * The Secondhand text.
     */
    @BindView(R.id.secondhand_text)
    TextView secondhand_text;
    /**
     * The Qiugou text.
     */
    @BindView(R.id.qiugou_text)
    TextView qiugou_text;

    @BindViews({R.id.dot_one,R.id.dot_two,R.id.dot_three,R.id.dot_four})
    List<View> DotList;


    private Context mContext;
    private View mRootView;
    private final ThreadLocal<List<Goods>> goodses = new ThreadLocal<>();

    private final ThreadLocal<List<Goods>> second_goodses = new ThreadLocal<>();
    private final ThreadLocal<List<User>> second_userses = new ThreadLocal<>();

    private final ThreadLocal<List<Goods>> qiugou_goodses = new ThreadLocal<>();
    private final ThreadLocal<List<User>> qiugou_userses = new ThreadLocal<>();

    private final ThreadLocal<ExpandableListViewAdapter> adapter = new ThreadLocal<>();

    private boolean qiugou;

    private final int SECOND_HAND = 0;
    private final int QIUGOU = 1;

    /**
     * The Unbinder.
     */
    Unbinder unbinder;

    /**
     * The M get weather text view.
     */
    @BindView(R.id.text3)
    TextView mGetWeatherTextView;
    /**
     * The Presenter.
     */
    @Inject
    HomeViewPresenter presenter;
    /**
     * The Weather api service.
     */
    @Inject
    WeatherApiService weatherApiService;
    private Boolean isFirstCreated;

    /**
     * Instantiates a new Home fragment.
     */
    public HomeFragment() {
        goodses.set(new ArrayList<Goods>());
        second_goodses.set(new ArrayList<Goods>());
        second_userses.set(new ArrayList<User>());
        qiugou_goodses.set(new ArrayList<Goods>());
        qiugou_userses.set(new ArrayList<User>());
        qiugou = false;
        isFirstCreated = true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.home_fragment, container, false);
            isFirstCreated = false;
        }
        unbinder=ButterKnife.bind(this, mRootView);
        setupComponent(((MyApp) mContext.getApplicationContext()).getAppComponent());
        initData();
        return mRootView;
    }

    /**
     * Init data.
     */
    public void initData() {
        mHomeGoodsList.setFocusable(false);
        adapter.set(new ExpandableListViewAdapter(mContext, second_goodses.get(), second_userses.get()));
        List<ImageView> imageViews = new ArrayList<>();
        presenter.loadHorizentalListViewData(mContext);
        presenter.loadBannerData(mContext, imageViews);
        //presenter.loadGoodsInfor(mContext);
        if (!qiugou)
            secondhand_click();
        else
            qiugou_click();
    }

    /**
     * Init dots.
     *
     * @param i the
     */
    public void initDots(final int i) {
        ButterKnife.apply(DotList, new ButterKnife.Action<View>() {
            @Override
            public void apply(@NonNull View view, int index) {
                view .setBackgroundResource(R.drawable.indictor_shape_normal);
                if(index==i)
                    view.setBackgroundResource(R.drawable.indicator_shape_selected) ;
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden && !isFirstCreated) {
            // presenter.loadGoodsInfor(mContext);
            if (!qiugou)
                secondhand_click();
            else
                qiugou_click();
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
     * On click.
     *
     * @param view the view
     */
    @OnClick({R.id.text3, R.id.icon_home_item, R.id.icon_search_imageview, R.id.qiugou_text, R.id.secondhand_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text3:
                presenter.load(GlobalDefineValues.baiduKey, "武汉");
                break;
            case R.id.icon_home_item:
                UIUtils.nextPage(mContext, KindActivity.class);
                break;
            case R.id.icon_search_imageview:
                UIUtils.nextPage(mContext, SearchActivity.class);
                break;
            case R.id.secondhand_text:
                this.secondhand_click();
                break;
            case R.id.qiugou_text:
                this.qiugou_click();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showBannerData(List<ImageView> images) {
        for (int i = 0; i < images.size(); i++) {
            mImageFliper.addView(images.get(i));
        }
        mImageFliper.setOnPageChangeListener(new ImageFlipper.OnPageChangeListener() {
            @Override
            public void onPageSelected(int index) {
                initDots(index);
            }
        });
    }

    @Override
    public void showweatherInfor(WeatherResultBean weatherResultBean) {
        mGetWeatherTextView.setText(weatherResultBean.getRetData().getCity() + "" + weatherResultBean.getRetData().getH_tmp());
        mGetWeatherTextView.invalidate();
    }

    @Override
    public void errorLoad(Throwable t) {

    }

    @Override
    public void setHirizentalListViewData(HomeHorizentalListViewAdapter adapter) {
        //setAdapter(adapter);
    }

    @Override
    public void parseUser(final List<User> users) {
       /* Log.e("User", users.size() + "");
        Log.e("Goods", goodses.size() + "");
        adapter=null;
        adapter = new ExpandableListViewAdapter(mContext, goodses, users);
        mHomeGoodsList.setAdapter(adapter);
        adapter.notifyDataSetChanged();*/
        adapter.get().notifyDataSetChanged();
        Log.e(TAG, "parseUser: " + (qiugou ? "求购" : "二手"));
        if (!qiugou) {
            second_userses.get().clear();
            second_goodses.get().clear();
            second_userses.get().addAll(users);
            second_goodses.get().addAll(goodses.get());
            adapter.get().notifyDataSetChanged();
            initUI(SECOND_HAND);
        } else {
            qiugou_goodses.get().clear();
            qiugou_userses.get().clear();
            qiugou_goodses.get().addAll(goodses.get());
            qiugou_userses.get().addAll(users);
            adapter.get().notifyDataSetChanged();
            initUI(QIUGOU);
        }
    }

    @Override
    public void parseUser(List<User> users, List<Goods> goodses) {
        this.goodses.set(goodses);
        parseUser(users);
    }

    @Override
    public void parseQiugouUser(final List<User> users) {

    }

    @Override
    public void onLoadGoodsInforSuccess(List<Goods> goods) {
        Log.e(TAG, "onLoadGoodsInforSuccess: " + (qiugou ? "求购" : "二手"));
        this.goodses.set(goods);
        presenter.parseGoodsUser(mContext, goods);
    }

    @Override
    public void onLoadGoodsError(String str) {

    }

    @Override
    public void onLoadQiugouGoodsInforSuccess(List<QiugouGoods> goods) {

    }

    @Override
    public void onLoadQiugoGoodsError(String str) {

    }

    private void secondhand_click() {
        mHomeGoodsList.clearAnimation();
        qiugou_text.getPaint().setFlags(0);
        qiugou_text.setTextColor(Color.BLACK);
        secondhand_text.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        secondhand_text.setTextColor(Color.RED);
        qiugou = false;
        presenter.loadGoodsInfor(mContext, false);
    }

    private void qiugou_click() {
        mHomeGoodsList.clearAnimation();
        secondhand_text.getPaint().setFlags(0);
        secondhand_text.setTextColor(Color.BLACK);
        qiugou_text.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        qiugou_text.setTextColor(Color.RED);
        qiugou = true;
        presenter.loadGoodsInfor(mContext, true);
    }

    private void initUI(final int type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.get().sendEmptyMessage(type);
            }
        }).start();
    }

    private final  ThreadLocal<Handler> handler = new ThreadLocal<Handler>() {
        @Override
        protected Handler initialValue() {
            return  new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message msg) {
                    adapter.set(null);
                    switch (msg.what) {
                        case SECOND_HAND:
                            Log.e(TAG, "handleMessage: second_goods" + second_goodses.get().size());
                            initHomeList(second_goodses.get(), second_userses.get());
                            break;
                        case QIUGOU:
                            Log.e(TAG, "handleMessage: qiugou_goods" + qiugou_goodses.get().size());
                            initHomeList(qiugou_goodses.get(), qiugou_userses.get());
                            break;
                    }
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
        adapter.set(new ExpandableListViewAdapter(mContext, goodses, userses));
        mHomeGoodsList.setAdapter(adapter.get());
        adapter.get().notifyDataSetChanged();
        for (int i = 0; i < adapter.get().getGroupCount(); i++) {
            mHomeGoodsList.collapseGroup(i);
            mHomeGoodsList.expandGroup(i);
        }
        mHomeGoodsList.setOnGroupClickListener(new MyGoodsListGroupListener(goodses, userses));
        mHomeGoodsList.setOnChildClickListener(new MyGoodsListChildListener(goodses, userses));
    }
}
